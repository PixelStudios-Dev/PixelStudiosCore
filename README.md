# Pixel Studios Core [![Maven Central](https://img.shields.io/maven-central/v/io.github.pixelstudios-dev/PixelStudiosCore.svg?color=orange)](https://central.sonatype.com/artifact/io.github.pixelstudios-dev/PixelStudiosCore) [![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/PixelStudios-Dev/PixelStudiosCore)

Una libreria de utilidades para Fabric, orientada a la versión de Minecraft 1.21.1.

---

## Añade la libreria a tu proyecto

### Gradle

Si usas gradle con dls kotlin, debes añadir lo siguiente a tu `build.gradle`:

```gradle
implementation("io.github.pixelstudios-dev:PixelStudiosCore:VERSION")
```

### Maven

Si en cambio prefieres maven, debes añadir lo siguiente a tu `pom.xml`:

```xml
<dependency>
    <groupId>io.github.pixelstudios-dev</groupId>
    <artifactId>PixelStudiosCore</artifactId>
    <version>VERSION</version>
</dependency>
```

### Otros build systems

Si usas cualquier otro build system, puedes encontrar su correspondiente snippet en la [Maven Central](https://central.sonatype.com/artifact/io.github.pixelstudios-dev/PixelStudiosCore)

*Puedes encontrar la última version en la pestaña [releases](https://github.com/PixelStudios-Dev/PixelStudiosCore/releases)*

## Usando la libreria

La libreria pretende como objetivo principal dar una capa más de abstracción a los desarrolladores de mods.

Esta permite hacer funciones complejas en unas cuantas lineas. Veamos un ejemplo:

Para crear y registrar un bloque, usaremos la clase ``BlockFactory``, una clase que abstrae a ``BlockRegistry``

```java
 BlockFactory.create("test")
             .setTranslatedName(Map.of(
                     "es", "Bloque de prueba",
                     "en", "Test block"
             ))
             .setBlockCategory(ItemGroups.COMBAT)
             .build();
```

*Esto nos devuelve un objeto del tipo creado, en este caso, nuestro bloque registrado en la categoria de Combate.*

Acto seguido, ejecutamos ``:runDatagen``y tendremos un bloque funcional con id "test", y su nombre traducido tanto a Español como Inglés.

Esto se aplica a todo lo demás que implique trauducción o creación de modelos, esto es, **siempre que usemos registremos un objeto Factory, debemos generar los datagens**

¿Y si quiero crear un item?

Pues mas o menos menos igual, aplicando todo lo anterior podemos emplear:

```java
ItemFactory.create("test")
             .setTranslatedName(Map.of(
                     "es", "Bloque de prueba",
                     "en", "Test block"
             ))
             .setBlockCategory(ItemGroups.COMBAT)
             .build();
```

Como vemos, es exactamente igual solo que esta vez es un item en vez de un bloque.

Refierase al codigo para ver los otros tipos de parametros que acepta ``#create``

Por ahora esto es una pequeña introducción de como la libreria te facilita el desarrollo, en el futuro habrán más ejemplos y de mejor calidad.

Gracias por leer.

## Licencia

Esta libreria está proporcionada en base a una licencia de software libre, para mas detalles de lo que puedes y no hacer diríjase al archivo [LICENSE](https://github.com/PixelStudios-Dev/PixelStudiosCore/blob/main/LICENSE)
