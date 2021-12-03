package io.github.vitorsalgado.puma4j.core;

import static org.junit.jupiter.api.Assertions.*;

import io.github.vitorsalgado.puma4j.core.unmarshallers.JsonUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.YamlUnmarshaller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Puma4jTest {

  private final Puma4j puma4j = new Puma4j();

  @Test
  @DisplayName("should init with correct values")
  void testInit() {
    assertNotNull(puma4j);
    assertNotNull(puma4j.resourceProvider());
    assertNotNull(puma4j.binaryMarshaller());
    assertNotNull(puma4j.jsonMarshaller());
    assertNotNull(puma4j.textMarshaller());
    assertNotNull(puma4j.propertiesMarshaller());
    assertNotNull(puma4j.yamlMarshaller());
  }

  @Test
  @DisplayName("should add or replace a marshaller for a specific extension")
  void addMarshallerByExtension() {
    final var jsonExt = "json";
    final var unknownExt = "unknown";

    assertInstanceOf(JsonUnmarshaller.class, puma4j.getMarshallerByExtension(jsonExt).orElseThrow());

    puma4j.registerMarshallerForExtension(jsonExt, new CustomJsonUnmarshaller());
    puma4j.registerMarshallerForExtension(unknownExt, new NewUnmarshaller());

    assertInstanceOf(
        CustomJsonUnmarshaller.class, puma4j.getMarshallerByExtension(jsonExt).orElseThrow());
    assertInstanceOf(
        NewUnmarshaller.class, puma4j.getMarshallerByExtension(unknownExt).orElseThrow());
  }

  @Test
  @DisplayName("should not allow null extensions when registering a marshaller")
  void addMarshallerWithInvalidExtension() {
    assertThrows(
        NullPointerException.class,
        () -> puma4j.registerMarshallerForExtension(null, new NewUnmarshaller()));
  }

  @Test
  @DisplayName("should return the correct marshaller for provided extension")
  void getMarshallerByExt() {
    assertInstanceOf(YamlUnmarshaller.class, puma4j.getMarshallerByExtension("yaml").orElseThrow());
  }

  static class CustomJsonUnmarshaller implements Unmarshaller<Object> {

    @Override
    public Object unmarshal(final Args args) {
      return null;
    }
  }

  static class NewUnmarshaller implements Unmarshaller<Object> {

    @Override
    public Object unmarshal(final Args args) {
      return null;
    }
  }
}
