package io.github.vitorsalgado.puma4j.core;

import static org.junit.jupiter.api.Assertions.*;

import io.github.vitorsalgado.puma4j.core.marshallers.JsonMarshaller;
import io.github.vitorsalgado.puma4j.core.marshallers.YamlMarshaller;
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

    assertInstanceOf(JsonMarshaller.class, puma4j.getMarshallerByExtension(jsonExt).orElseThrow());

    puma4j.registerMarshallerForExtension(jsonExt, new CustomJsonMarshaller());
    puma4j.registerMarshallerForExtension(unknownExt, new NewMarshaller());

    assertInstanceOf(
        CustomJsonMarshaller.class, puma4j.getMarshallerByExtension(jsonExt).orElseThrow());
    assertInstanceOf(
        NewMarshaller.class, puma4j.getMarshallerByExtension(unknownExt).orElseThrow());
  }

  @Test
  @DisplayName("should not allow null extensions when registering a marshaller")
  void addMarshallerWithInvalidExtension() {
    assertThrows(
        NullPointerException.class,
        () -> puma4j.registerMarshallerForExtension(null, new NewMarshaller()));
  }

  @Test
  @DisplayName("should return the correct marshaller for provided extension")
  void getMarshallerByExt() {
    assertInstanceOf(YamlMarshaller.class, puma4j.getMarshallerByExtension("yaml").orElseThrow());
  }

  static class CustomJsonMarshaller implements Marshaller<Object> {

    @Override
    public Object unmarshal(final Args args) {
      return null;
    }
  }

  static class NewMarshaller implements Marshaller<Object> {

    @Override
    public Object unmarshal(final Args args) {
      return null;
    }
  }
}
