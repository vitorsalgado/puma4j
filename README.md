<h1 align="center">Puma4j</h1>

<p align="center">
  <img src="docs/assets/logo.png" alt="Repository Logo" width='200px' height='200px' />
  <br />
  <i>Easy and convenient way to load file resources into your <strong>tests</strong> using only <strong>annotations</strong>.</i>
</p>

<p align="center">
  <a href="https://github.com/vitorsalgado/puma4j/actions/workflows/ci.yml">
    <img src="https://github.com/vitorsalgado/puma4j/actions/workflows/ci.yml/badge.svg" alt="GitHub Action Status" />
  </a>
  <a href="https://codecov.io/gh/vitorsalgado/puma4j">
    <img src="https://codecov.io/gh/vitorsalgado/puma4j/branch/main/graph/badge.svg?token=EFC2SD81AV" alt="Codecov Badge"/>
  </a>
  <a href="https://conventionalcommits.org">
    <img src="https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg" alt="Conventional Commits"/>
  </a>
</p>

## Overview

Puma4j provides a convenient and much easier way to load and parse file resources in your tests
using just a couple of **@annotations** :)

## Download

Using Gradle:

```groovy
implementation "io.github.vitorsalgado.puma4j:puma4j-junit5-extension:<VERSION>"
```

or Maven:

```xml

<dependency>
  <groupId>io.github.vitorsalgado.puma4j</groupId>
  <artifactId>puma4j-junit5-extension</artifactId>
  <version>VERSION</version>
</dependency>
```

## Usage

```java

@ExtendWith(TestResourceParameterResolver.class)
public class JUnit5ExtensionUsageExample {

  @Test
  void itShouldInjectAnObjectInstanceWhenParameterTypeIsObject(
      final @Res("fixtureTest.json") TestData testData) {
    assertEquals(123, testData.getId());
    assertEquals("hello world", testData.getTest());
  }
}

```

---

## Contributing

See [CONTRIBUTING](CONTRIBUTING.md) for more details.

## Contributors

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

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
