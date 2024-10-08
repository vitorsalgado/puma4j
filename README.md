<h1 align="center">Puma4j</h1>

<p align="center">
  <img src="docs/assets/logo.png" alt="Repository Logo" width='200px' height='200px' />
  <br />
  <i>Easy and convenient way to load file resources into your <strong>tests</strong> using only <strong>annotations</strong>.</i>
</p>

<p align="center">
  <a href="https://github.com/vitorsalgado/puma4j/actions/workflows/ci.yml">
    <img src="https://github.com/vitorsalgado/puma4j/actions/workflows/ci.yml/badge.svg?branch=main" alt="GitHub Action Status" />
  </a>
  <a href="https://codecov.io/gh/vitorsalgado/puma4j">
    <img src="https://codecov.io/gh/vitorsalgado/puma4j/branch/main/graph/badge.svg?token=EFC2SD81AV" alt="Codecov Badge"/>
  </a>
  <a href="https://conventionalcommits.org">
    <img src="https://img.shields.io/badge/Conventional%20Commits-4.0.4-yellow.svg" alt="Conventional Commits"/>
  </a>
  <a href="https://sonarcloud.io/project/overview?id=vitorsalgado_puma4j">
    <img src="https://sonarcloud.io/api/project_badges/measure?project=vitorsalgado_puma4j&metric=alert_status" alt="Sonar Quality Gate Status"/>
  </a>
  <a href="https://search.maven.org/search?q=io.github.vitorsalgado.puma4j">
    <img alt="Sonatype Nexus (Releases)" src="https://img.shields.io/nexus/r/io.github.vitorsalgado.puma4j/puma4j-junit5-extension?server=https%3A%2F%2Fs01.oss.sonatype.org%2F">  
  </a>
</p>

## Overview

Puma4j provides a convenient and easier way to load and parse file resources in your **JUnit 5** tests using just a couple of **annotations** :)

## Installation 

![Java 8](https://img.shields.io/badge/java-8-yellow)

Using Gradle:

```gradle
testImplementation "io.github.vitorsalgado.puma4j:puma4j-junit5-extension:4.0.4"
```

or Maven:

```xml
<dependency>
  <groupId>io.github.vitorsalgado.puma4j</groupId>
  <artifactId>puma4j-junit5-extension</artifactId>
  <version>4.0.4</version>
  <scope>test</scope>
</dependency>
```

## How It Works

**Puma4j**, when enabled, detects fields or parameters annotated with `@Res` and inject resource values based on annotated element type, resource extension and
any custom unmarshaller added.  
You can use `String` and `byte[]` for all types of resources.  
Puma4j is also able to convert some resource types to object type representations:

- Json: Object types based on the json structure. It uses **Jackson ObjectMapper** and **Gson**. Defaults to **Jackson ObjectMapper**
- Yaml: Object types based on yaml structure. It uses **Jackson ObjectMapper**.
- Properties: Converts `.properties` files to java `Properties` class.

For custom **object conversions** use a custom **unmarshaller** using the annotation `@Use`, passing the class type of your custom unmarshaller implementation.

## Usage

First, annotate your JUnit 5 test classes with `@UsePuma4j`. Now you can use the annotation `@Res`
to inject resources on class fields and/or method parameters.  
Take a look on the complete example below:

```java
@UsePuma4j
class UsageWithJUnit5 {

  @Res("data.txt")
  private static String staticTextData;

  @Res("complex.json")
  private List<ComplexModel> complexModelList;

  @Res("complex-yml.yaml")
  private List<ComplexModel> complexModelListFromYaml;

  @Res("test.properties")
  private Properties testProperties;

  @Res("simple.json")
  @Use(JsonTreeUnmarshaller.class)
  private JsonNode simpleJsonTree;

  @Test
  void simpleTest(final @Res("simple.json") SimpleModel model) {
    assertEquals(50, model.getAge());
    assertEquals("no-one", model.getName());
  }

  public static class JsonTreeUnmarshaller implements Unmarshaller<JsonNode> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public JsonNode unmarshal(final Args args) throws IOException {
      return OBJECT_MAPPER.readTree(args.getInput());
    }
  }
}
```

## Features

### Forcing Jackson or Gson

For **json** files, the default parser is **Jackson ObjectMapper**. If you want to use **Gson** for a specific type use: `@UseGson`. If applied in the class
level, all json conversions will use Gson. You can use `@UseJackson` to keep using Object Mapper on specific resources.

### Custom Unmarshaller

You can use your custom **unmarshaller** implementation.  
First, create a new class implementing the interface `Unmarshaller<O>`.  
Then, use the annotation `@Use(YourCustomUnmarshaller.class)` on the class, field or method level to use your new custom unmarshaller.

### Kotlin Delegate

You can load resources using a Kotlin delegate also. Look the example below:

```kotlin
private val simpleModel: SimpleModel by res("simple.json")
```

To use the delegate `res`, add the library below to your project:

```gradle
testImplementation "io.github.vitorsalgado.puma4j:puma4j-kotlin:4.0.4"
```

---

## Contributing

See [CONTRIBUTING](CONTRIBUTING.md) for more details.

## Contributors

Thanks goes to these wonderful people ✨:

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table border="0">
  <tr>
    <td style='border-style: hidden;' align="center"><a href="https://github.com/smunoz2"><img src="https://avatars.githubusercontent.com/u/61516534?v=4" width="50px;" alt=""/><br /><sub><b>Sebastián Muñoz Eyquem</b></sub></a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors)
specification. Contributions of any kind welcome!

## License

This project is [MIT Licensed](LICENSE).
