package io.github.vitorsalgado.puma4j.core;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.annotations.Use;
import io.github.vitorsalgado.puma4j.annotations.UseGson;
import io.github.vitorsalgado.puma4j.annotations.UseJackson;
import io.github.vitorsalgado.puma4j.core.Provider.Args;
import io.github.vitorsalgado.puma4j.core.models.ComplexDataModel;
import io.github.vitorsalgado.puma4j.core.models.ComplexModel;
import io.github.vitorsalgado.puma4j.core.models.SimpleDataModel;
import io.github.vitorsalgado.puma4j.core.models.SimpleModel;
import io.github.vitorsalgado.puma4j.core.utils.StreamUtils;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResourceProviderTest {

  // region Res

  private final Provider provider = new DefaultResourceProvider();
  @Res("no_extension")
  private SimpleModel noExtension;
  @Res("no_extension")
  @Use(CustomJsonMarshaller.class)
  private SimpleModel noExtensionWithCustomMarshaller;
  @Res("no_extension")
  @Use(InvalidMarshaller.class)
  private SimpleModel resWithInvalidMarshallerRef;
  @Res("data.txt")
  @Use(FailingMarshaller.class)
  private String resWithFailingMarshaller;
  @Res("data.txt")
  private String textRes;
  @Res("data.txt")
  private byte[] textResToBytes;
  @Res("data.txt")
  private Byte[] textResToBytesType;
  @Res("test.properties")
  private Properties propertiesRes;
  @Res("not_supported_extension.bin")
  private SimpleModel notSupportedData;
  @Res("simple.json")
  private SimpleModel simpleJson;
  @Res("simple.json")
  @UseGson
  private SimpleModel simpleJsonUsingGson;
  @Res("simple.json")
  @UseJackson
  private SimpleModel simpleJsonUsingJackson;
  @Res("simple-yml.yaml")
  private SimpleModel simpleYaml;
  @Res("complex.json")
  @UseGson
  private List<ComplexModel> complexJsonUsingGson;
  @Res("complex.json")
  @UseJackson
  private List<ComplexModel> complexJsonUsingJackson;

  // endregion
  @Res("complex-yml.yaml")
  private List<ComplexDataModel> complexYaml;

  @Test
  @DisplayName("should use marshaller from @Use to load extensionless file")
  void noExtensionWithCustomMarshaller() throws NoSuchFieldException {
    final var context = new Context("/");

    final var resource =
        (SimpleModel)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField(
                        "noExtensionWithCustomMarshaller")));

    assertEquals(150, resource.getAge());
    assertEquals("SomeOne Nice", resource.getName());
  }

  @Test
  @DisplayName("should read text resource")
  void textResource() throws NoSuchFieldException {
    final var context = new Context("");

    final var resource =
        (String)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    String.class,
                    String.class,
                    ResourceProviderTest.class.getDeclaredField("textRes")));

    assertEquals("Header\nLine 1\nFooter", resource);
  }

  @Test
  @DisplayName("should read text resource to byte array when type byte[] or Byte[]")
  void textResToBytes() throws NoSuchFieldException {
    final var context = new Context("/");

    final var byteArr =
        (byte[])
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    byte[].class,
                    byte[].class,
                    ResourceProviderTest.class.getDeclaredField("textResToBytes")));
    final var byteTypeArr =
        (byte[])
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    Byte[].class,
                    Byte[].class,
                    ResourceProviderTest.class.getDeclaredField("textResToBytesType")));

    assertNotNull(byteArr);
    assertNotNull(byteTypeArr);
    assertEquals(new String(byteArr), new String(byteTypeArr));
  }

  @Test
  @DisplayName("should read properties resource")
  void propertiesResource() throws NoSuchFieldException {
    final var context = new Context("");

    final var resource =
        (Properties)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    Properties.class,
                    Properties.class,
                    ResourceProviderTest.class.getDeclaredField("propertiesRes")));

    assertEquals("properties loading", resource.getProperty("test.name"));
    assertEquals("yes", resource.getProperty("test.enabled"));
  }

  @Test
  @DisplayName("should read simple json resource with the default unmarshaller")
  void readJson() throws NoSuchFieldException {
    final var context = new Context("");

    final var resource =
        (SimpleModel)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField("simpleJson")));

    assertEquals(50, resource.getAge());
    assertEquals("no-one", resource.getName());
  }

  @Test
  @DisplayName("should read simple json resource with Gson")
  void readJsonWithGson() throws NoSuchFieldException {
    final var context = new Context("");

    final var resource =
        (SimpleModel)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField("simpleJsonUsingGson")));

    assertEquals(50, resource.getAge());
    assertEquals("no-one", resource.getName());
  }

  @Test
  @DisplayName("should read simple json resource with Jackson Object Mapper")
  void readJsonWithJackson() throws NoSuchFieldException {
    final var context = new Context("");

    final var resource =
        (SimpleModel)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField("simpleJsonUsingJackson")));

    assertEquals(50, resource.getAge());
    assertEquals("no-one", resource.getName());
  }

  @Test
  @DisplayName("should read simple yaml resource")
  void readSimpleYamlFile() throws NoSuchFieldException {
    final var context = new Context("");

    final var resource =
        (SimpleDataModel)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleDataModel.class,
                    SimpleDataModel.class,
                    ResourceProviderTest.class.getDeclaredField("simpleYaml")));

    assertEquals(50, resource.getAge());
    assertEquals("no-one", resource.getName());
  }

  @Test
  @DisplayName("should read complex json resource with Gson")
  void readComplexJsonWithGson() throws NoSuchFieldException {
    final var context = new Context("");
    final var field = ResourceProviderTest.class.getDeclaredField("complexJsonUsingGson");

    final var resource =
        (List<ComplexModel>)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    field.getType(),
                    field.getGenericType(),
                    field));

    assertEquals(1, resource.size());

    final var model = resource.get(0);

    assertEquals(100, model.getId());
    assertEquals("test", model.getTest());
    assertEquals("name", model.getInner().getName());
  }

  @Test
  @DisplayName("should read complex json resource with Jackson Object Mapper")
  void readComplexJsonWithJackson() throws NoSuchFieldException {
    final var context = new Context("");
    final var field = ResourceProviderTest.class.getDeclaredField("complexJsonUsingJackson");

    final var resource =
        (List<ComplexModel>)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    field.getType(),
                    field.getGenericType(),
                    field));

    assertEquals(1, resource.size());

    final var model = resource.get(0);

    assertEquals(100, model.getId());
    assertEquals("test", model.getTest());
    assertEquals("name", model.getInner().getName());
  }

  @Test
  @DisplayName("should read complex yaml resource")
  void readComplexYamlFile() throws NoSuchFieldException {
    final var context = new Context("");
    final var field = ResourceProviderTest.class.getDeclaredField("complexYaml");

    final var resource =
        (List<ComplexDataModel>)
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    field.getType(),
                    field.getGenericType(),
                    field));

    assertEquals(1, resource.size());

    final var model = resource.get(0);

    assertEquals(100, model.getId());
    assertEquals("test", model.getTest());
    assertEquals("name", model.getInner().getName());
  }

  // region Failure Scenarios

  @Test
  @DisplayName("should fail when resource has no extension and no marshaller was set with @Use")
  void noExtensionAndNoCustomMarshaller() {
    final var context = new Context("");

    assertThrows(
        NoMarshallerException.class,
        () ->
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField("noExtension"))));
  }

  @Test
  @DisplayName("should fail when @Use has a class type without an arg-less constructor")
  void customMarshallerWithoutArgLessConstructor() {
    final var context = new Context("");

    assertThrows(
        InvalidMarshallerClassException.class,
        () ->
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField("resWithInvalidMarshallerRef"))));
  }

  @Test
  @DisplayName("should fail with specific exception type when marshaller throws any error")
  void failingMarshaller() {
    final var context = new Context("");

    assertThrows(
        UnmarshallingException.class,
        () ->
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    String.class,
                    String.class,
                    ResourceProviderTest.class.getDeclaredField("resWithFailingMarshaller"))));
  }

  @Test
  @DisplayName("should fail with specific exception type when marshaller throws any error")
  void noUnmarshallerForExtension() {
    final var context = new Context("");

    assertThrows(
        ExtensionNotSupportedException.class,
        () ->
            provider.provide(
                new Args(
                    context,
                    ResourceProviderTest.class,
                    SimpleModel.class,
                    SimpleModel.class,
                    ResourceProviderTest.class.getDeclaredField("notSupportedData"))));
  }

  // endregion

  // region Test Marshallers

  static class CustomJsonMarshaller implements Marshaller<Object> {

    private static final Gson GSON = new Gson();

    @Override
    public Object unmarshal(final Args args) throws IOException {
      return GSON.fromJson(StreamUtils.toString(args.getInput()), args.getType());
    }
  }

  static class InvalidMarshaller implements Marshaller<Object> {

    InvalidMarshaller(final String wrongParam) {
    }

    @Override
    public Object unmarshal(final Args args) throws IOException {
      return null;
    }
  }

  static class FailingMarshaller implements Marshaller<Object> {

    @Override
    public Object unmarshal(final Args args) throws IOException {
      throw new RuntimeException("Failed!");
    }
  }

  // endregion
}
