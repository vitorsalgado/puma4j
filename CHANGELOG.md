# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [3.0.2](https://github.com/vitorsalgado/puma4j/compare/v3.0.1...v3.0.2) (2022-02-24)


### Refactor

* remove object mapper kotlin module from core ([0a7886e](https://github.com/vitorsalgado/puma4j/commit/0a7886e8765917e42463963d4fa42dc2b2d0b7c6))


### Build

* **deps:** bump actions/setup-node from 2 to 3.0.0 ([#31](https://github.com/vitorsalgado/puma4j/issues/31)) ([80b233a](https://github.com/vitorsalgado/puma4j/commit/80b233adb39a7cb3e42d1f710fa2468fcebfb234))

### [3.0.1](https://github.com/vitorsalgado/puma4j/compare/v3.0.0...v3.0.1) (2022-02-23)


### Build

* **deps:** bump gson from 2.8.9 to 2.9.0 ([#30](https://github.com/vitorsalgado/puma4j/issues/30)) ([69ddcb1](https://github.com/vitorsalgado/puma4j/commit/69ddcb1866ef25f77c20ea4933e8baabc4038796))


### Docs

* fix installing scope ([174c71b](https://github.com/vitorsalgado/puma4j/commit/174c71bc2b37ac90e3b9df7cbc7fa609254a3fff))
* update version ([48cb491](https://github.com/vitorsalgado/puma4j/commit/48cb4919e3b657a8d120f7a30cdd7a8b30e0bcf5))

## [3.0.0](https://github.com/vitorsalgado/puma4j/compare/v2.0.1...v3.0.0) (2022-02-01)


### ⚠ BREAKING CHANGES

* changed min java version to 8

### Refactor

* move to java 8 ([c9db608](https://github.com/vitorsalgado/puma4j/commit/c9db608bea748c6575c55580bede22ee6fda6a29))


### Docs

* fix typos and java version ref ([a49ea9d](https://github.com/vitorsalgado/puma4j/commit/a49ea9d431d40a4c57cf03eced92a72c0194e775))


### Build

* add commitlint + husky + makefile ([9f057c9](https://github.com/vitorsalgado/puma4j/commit/9f057c99ee23c8980545f1c771b272469a3e0307))
* add owasp dependency check ([9ee6c75](https://github.com/vitorsalgado/puma4j/commit/9ee6c753a2c0a18a98ceda3de5638f219cd09ee5))
* add package.json for release scripts + update gitignore + update standard-version config ([ce6791c](https://github.com/vitorsalgado/puma4j/commit/ce6791c001e7b6149f7ef4c7820dab16b82c337f))
* add release command to makefile ([e2c64c1](https://github.com/vitorsalgado/puma4j/commit/e2c64c1dfed94eebd68d3df350298ac8166f31ab))
* add script to get last changelog entry ([dc2ee3a](https://github.com/vitorsalgado/puma4j/commit/dc2ee3a669f5b460aa30ae1cd4d8f89a07244dc9))
* **ci:** change ci to use java 8 ([3b055aa](https://github.com/vitorsalgado/puma4j/commit/3b055aa6e6da87a32ba2d93c636cf8a731d56049))
* **ci:** java 11 ([bc84a04](https://github.com/vitorsalgado/puma4j/commit/bc84a04be8645ed8236d8309c7999aaa6c9c81ab))
* **ci:** java 11 ([5eba87a](https://github.com/vitorsalgado/puma4j/commit/5eba87a88f4d935a0f4dd4f8597daa868d6c055b))
* **ci:** remove win ([5f62e89](https://github.com/vitorsalgado/puma4j/commit/5f62e891ffb4e79b3466450f93a675433472af00))
* **ci:** trying to run on win also ([f9612fa](https://github.com/vitorsalgado/puma4j/commit/f9612fa3ff16d7f5c19b350cf6c3d1c4d9c30c64))
* **deps:** bump versions.jackson from 2.13.0 to 2.13.1 ([#29](https://github.com/vitorsalgado/puma4j/issues/29)) ([138d4d2](https://github.com/vitorsalgado/puma4j/commit/138d4d23f7e42ba1990aa615c0428b953fb380fd))
* remove unused gradle util ([cb67787](https://github.com/vitorsalgado/puma4j/commit/cb67787ece1c3d41862ee226e4df490b613138c4))
* upgrade gradle to 7.3.3 ([eb18e88](https://github.com/vitorsalgado/puma4j/commit/eb18e88d1e67d0268b55f8369af605e5aaf290c5))

### [2.0.1](https://github.com/vitorsalgado/puma4j/compare/v2.0.0...v2.0.1) (2021-12-20)

## [2.0.0](https://github.com/vitorsalgado/puma4j/compare/v1.0.0...v2.0.0) (2021-12-03)


### ⚠ BREAKING CHANGES

* most components where renamed and refactored and the public interface to extend the lib was changed

### Features

* add kotlin support ([722326f](https://github.com/vitorsalgado/puma4j/commit/722326f31c6056e145419e669d1c911aa52d1a21))
* change lib structure and public interface + add kotlin delegate res ([936fde7](https://github.com/vitorsalgado/puma4j/commit/936fde7502ba55083869ff769cb252aab550785c))

## 1.0.0 (2021-11-26)


### Features

* add example project ([1fb0997](https://github.com/vitorsalgado/puma4j/commit/1fb09975fd5d46f9ec880e7632327e298f4f034c))
* add reference implementation ([5db2e19](https://github.com/vitorsalgado/puma4j/commit/5db2e19327833cad6897501eceee26c3235e2a2f))
* prepare gradle scripts to lib publication ([64cd8c0](https://github.com/vitorsalgado/puma4j/commit/64cd8c0c451a12676354b292da7a863a2ba86253))
* project to multi project ([2d315df](https://github.com/vitorsalgado/puma4j/commit/2d315df120cdf1f51dc9a39f742b32eb5563d813))


### Bug Fixes

* add missing triggers to ci pipeline ([7a8cfe5](https://github.com/vitorsalgado/puma4j/commit/7a8cfe5f49cb533af86da378e978ef4f63e57f4d))
* codeql build ([bcd3836](https://github.com/vitorsalgado/puma4j/commit/bcd3836ae408c7c2158e45dbc4d4a1b4367453ab))
* remove example from core projects list ([e5f3d01](https://github.com/vitorsalgado/puma4j/commit/e5f3d01cbf711f3d4be65e68329efc114e0220fb))
