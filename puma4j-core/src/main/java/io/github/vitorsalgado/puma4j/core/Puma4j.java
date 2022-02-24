package io.github.vitorsalgado.puma4j.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.gson.Gson;
import io.github.vitorsalgado.puma4j.core.unmarshallers.BinaryUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.JsonUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.PropertiesUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.TextUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.YamlUnmarshaller;
import java.util.Arrays;
import java.util.Optional;

public final class Puma4j {

  private static final UnmarshallerRegistry READER_REGISTRY = new UnmarshallerRegistry();

  private final JsonUnmarshaller jsonMarshaller;
  private final TextUnmarshaller textMarshaller;
  private final YamlUnmarshaller yamlMarshaller;
  private final PropertiesUnmarshaller propertiesMarshaller;
  private final BinaryUnmarshaller binaryMarshaller;

  Puma4j() {
    final Gson gson = new Gson();
    final ObjectMapper objectMapperForYaml =
        new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
    final ObjectMapper objectMapperForJson =
        JsonMapper
            .builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .findAndAddModules()
            .build();

    this.jsonMarshaller = new JsonUnmarshaller(gson, objectMapperForJson);
    this.yamlMarshaller = new YamlUnmarshaller(objectMapperForYaml);
    this.textMarshaller = new TextUnmarshaller();
    this.propertiesMarshaller = new PropertiesUnmarshaller();
    this.binaryMarshaller = new BinaryUnmarshaller();

    Arrays
        .stream(Extensions.JSON)
        .forEach(ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.jsonMarshaller));
    Arrays
        .stream(Extensions.PROPERTIES)
        .forEach(
            ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.propertiesMarshaller));
    Arrays
        .stream(Extensions.TEXT)
        .forEach(ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.textMarshaller));
    Arrays
        .stream(Extensions.YML)
        .forEach(ext -> READER_REGISTRY.registerMarshallerForExtension(ext, this.yamlMarshaller));
  }

  public static Puma4j instance() {
    return Puma4jSingleton.INSTANCE;
  }

  public Provider resourceProvider() {
    return new DefaultResourceProvider();
  }

  public void registerMarshallerForExtension(
      final String extension, final Unmarshaller<?> unmarshaller) {
    READER_REGISTRY.registerMarshallerForExtension(extension, unmarshaller);
  }

  public Optional<Unmarshaller<?>> getMarshallerByExtension(final String extension) {
    return READER_REGISTRY.getMarshallerByExtension(extension);
  }

  public Unmarshaller<?> jsonMarshaller() {
    return this.jsonMarshaller;
  }

  public Unmarshaller<?> yamlMarshaller() {
    return this.yamlMarshaller;
  }

  public Unmarshaller<?> propertiesMarshaller() {
    return this.propertiesMarshaller;
  }

  public Unmarshaller<?> textMarshaller() {
    return this.textMarshaller;
  }

  public Unmarshaller<?> binaryMarshaller() {
    return this.binaryMarshaller;
  }

  private static class Puma4jSingleton {

    private static final Puma4j INSTANCE = new Puma4j();
  }
}
