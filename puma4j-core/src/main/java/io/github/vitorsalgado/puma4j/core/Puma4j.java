package io.github.vitorsalgado.puma4j.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.gson.Gson;
import io.github.vitorsalgado.puma4j.core.marshallers.BinaryMarshaller;
import io.github.vitorsalgado.puma4j.core.marshallers.JsonMarshaller;
import io.github.vitorsalgado.puma4j.core.marshallers.PropertiesMarshaller;
import io.github.vitorsalgado.puma4j.core.marshallers.TextMarshaller;
import io.github.vitorsalgado.puma4j.core.marshallers.YamlMarshaller;
import java.util.Arrays;
import java.util.Optional;

public final class Puma4j {

  // region Fields

  private static final MarshallerRegistry READER_REGISTRY = new MarshallerRegistry();

  private final JsonMarshaller jsonMarshaller;
  private final TextMarshaller textMarshaller;
  private final YamlMarshaller yamlMarshaller;
  private final PropertiesMarshaller propertiesMarshaller;
  private final BinaryMarshaller binaryMarshaller;

  // endregion

  // region Init

  Puma4j() {
    final Gson gson = new Gson();
    final ObjectMapper objectMapperForYaml =
        new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
    final ObjectMapper objectMapperForJson =
        JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .findAndAddModules()
            .build();

    this.jsonMarshaller = new JsonMarshaller(gson, objectMapperForJson);
    this.yamlMarshaller = new YamlMarshaller(objectMapperForYaml);
    this.textMarshaller = new TextMarshaller();
    this.propertiesMarshaller = new PropertiesMarshaller();
    this.binaryMarshaller = new BinaryMarshaller();

    Arrays.stream(Extensions.JSON)
        .forEach(ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.jsonMarshaller));
    Arrays.stream(Extensions.PROPERTIES)
        .forEach(
            ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.propertiesMarshaller));
    Arrays.stream(Extensions.TEXT)
        .forEach(ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.textMarshaller));
    Arrays.stream(Extensions.YML)
        .forEach(ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.yamlMarshaller));
  }

  private static class Puma4jSingleton {

    private static final Puma4j INSTANCE = new Puma4j();
  }

  public static Puma4j instance() {
    return Puma4jSingleton.INSTANCE;
  }

  // endregion

  // region Marshaller Interface

  public Provider resourceProvider() {
    return new DefaultResourceProvider();
  }

  public void registerMarshallerForExtension(
      final String extension, final Marshaller<?> marshaller) {
    READER_REGISTRY.registerMarshallerForExtension(extension, marshaller);
  }

  public Optional<Marshaller<?>> getMarshallerByExtension(final String extension) {
    return READER_REGISTRY.getMarshallerByExtension(extension);
  }

  // endregion

  public Marshaller<?> jsonMarshaller() {
    return this.jsonMarshaller;
  }

  public Marshaller<?> yamlMarshaller() {
    return this.yamlMarshaller;
  }

  public Marshaller<?> propertiesMarshaller() {
    return this.propertiesMarshaller;
  }

  public Marshaller<?> textMarshaller() {
    return this.textMarshaller;
  }

  public Marshaller<?> binaryMarshaller() {
    return this.binaryMarshaller;
  }
}
