package io.github.vitorsalgado.puma4j.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.gson.Gson;
import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.core.unmarshallers.BinaryUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.JsonUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.PropertiesUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.TextUnmarshaller;
import io.github.vitorsalgado.puma4j.core.unmarshallers.YamlUnmarshaller;
import java.lang.reflect.AnnotatedElement;
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
        new ObjectMapper(new YAMLFactory()).registerModule(new ParameterNamesModule());
    final ObjectMapper objectMapperForJson =
        JsonMapper
            .builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
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

  public static boolean isResourceAnnotationPresent(final AnnotatedElement annotatedElement) {
    return annotatedElement.isAnnotationPresent(Res.class);
  }

  public Provider resourceProvider() {
    return new DefaultResourceProvider();
  }

  public void registerMarshallerForExtension(
      final String extension, final Unmarshaller<?> unmarshaller) {
    READER_REGISTRY.registerMarshallerForExtension(extension, unmarshaller);
  }

  @SuppressWarnings("squid:S1452")
  public Optional<Unmarshaller<?>> getMarshallerByExtension(final String extension) {
    return READER_REGISTRY.getMarshallerByExtension(extension);
  }

  public JsonUnmarshaller jsonMarshaller() {
    return this.jsonMarshaller;
  }

  public YamlUnmarshaller yamlMarshaller() {
    return this.yamlMarshaller;
  }

  public PropertiesUnmarshaller propertiesMarshaller() {
    return this.propertiesMarshaller;
  }

  public TextUnmarshaller textMarshaller() {
    return this.textMarshaller;
  }

  public BinaryUnmarshaller binaryMarshaller() {
    return this.binaryMarshaller;
  }

  private static class Puma4jSingleton {

    private static final Puma4j INSTANCE = new Puma4j();
  }
}
