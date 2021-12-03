# puma4j-junit5-extension

Puma4j JUnit5 Extensions.

## Usage

Annotate your test class with: `@UsePuma4j`  
This will enable you to use the annotation `@Res` on method parameters and class fields. The
filename to the `@Res` annotation value.

## Features

- `@Use` - sets a custom resource unmarshaller. Can be applied on method parameters and fields,
  together with the `@Res` annotation, or on class level to set a custom unmarshaller to all
  resources load. If set on class level, it can be overridden by another `@Use` annotation on the
  field or parameter.
- `@UseGson` - force usage of **Google Gson** for json resources.
- `@UseJackson` - force usage of **Jackson Object Mapper** for json resources. 
