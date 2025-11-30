import org.gradle.kotlin.dsl.compileClasspath
import org.gradle.kotlin.dsl.runtimeClasspath

plugins {
    id("fabric-loom") version "1.13-SNAPSHOT"
    `maven-publish`
    signing
    id("com.gradleup.nmcp") version "0.0.9"
}

version = project.property("mod_version") as String
group = "io.github.pixelstudios-dev"

base {
    archivesName.set(project.property("archives_base_name") as String)
}

val testmod by sourceSets.creating {
    java.srcDir("src/testmod/java")
    resources.srcDir("src/testmod/resources")
    compileClasspath += sourceSets["main"].compileClasspath
    runtimeClasspath += sourceSets["main"].runtimeClasspath
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks {
    named("compileTestmodJava") {
        dependsOn("compileJava")
    }

    named("processTestmodResources") {
        dependsOn("processResources")
    }
}

loom {

    mods {

        register("testmod") {
            sourceSet(testmod)
        }
    }

    runs {
        register("testmodClient") {
            client()
            name("Testmod Client")
            source(sourceSets["testmod"])
        }

        register("testmodDatagen") {
            client()
            name("Testmod Datagen")
            source(sourceSets["testmod"])
            runDir("run")

            programArgs("--mod", "testmod", "--all", "--output", file("src/testmod/generated").absolutePath)

            property("fabric-api.datagen")
            property("fabric-api.datagen.output-dir", file("src/testmod/generated").absolutePath)
            property("fabric-api.datagen.modid", "testmod")
        }

    }
}


fabricApi {
    configureDataGeneration {

        modId = "testmod"
        client = true

        createSourceSet = false

    }
}

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_version")}")

    "testmodImplementation"(sourceSets["main"].output)
}

tasks.processResources {
    inputs.property("version", project.version)
    inputs.property("minecraft_version", project.property("minecraft_version"))
    inputs.property("loader_version", project.property("loader_version"))
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand(
            "version" to project.version,
            "minecraft_version" to project.property("minecraft_version"),
            "loader_version" to project.property("loader_version")
        )
    }
}

val targetJavaVersion = 21

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible) {
        options.release.set(targetJavaVersion)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${base.archivesName.get()}" }
    }
}

val devJar by tasks.registering(Jar::class) {
    archiveClassifier.set("")
    from(sourceSets["main"].output)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    dependsOn("classes")

    from("LICENSE") {
        rename { "${it}_${base.archivesName.get()}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.property("archives_base_name") as String

            artifact(devJar.get())

            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set(project.property("archives_base_name") as String)
                description.set("A Fabric library made by PixelStudios")
                url.set("https://github.com/pixelstudios-dev/${project.property("archives_base_name")}")

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("pixelstudios")
                        name.set("PixelStudios Dev Team")
                        url.set("https://github.com/pixelstudios-dev")
                    }
                }

                scm {
                    url.set("https://github.com/pixelstudios-dev/${project.property("archives_base_name")}")
                    connection.set("scm:git:git://github.com/pixelstudios-dev/${project.property("archives_base_name")}.git")
                    developerConnection.set("scm:git:ssh://github.com:pixelstudios-dev/${project.property("archives_base_name")}.git")
                }
            }
        }
    }
}

signing {
    val keyId = System.getenv("SIGNING_KEY_ID")
    val key = System.getenv("SIGNING_KEY")
    val password = System.getenv("SIGNING_PASSWORD")

    if (keyId != null && key != null && password != null) {
        useInMemoryPgpKeys(keyId, key, password)
        sign(publishing.publications["mavenJava"])
    } else {
        println("Firma PGP desactivada (faltan claves)")
    }
}

nmcp {
    publishAllPublications {
        username.set(System.getenv("SONATYPE_USERNAME") ?: "token")
        password.set(System.getenv("SONATYPE_TOKEN"))
        publicationType.set("AUTOMATIC")
    }
}
