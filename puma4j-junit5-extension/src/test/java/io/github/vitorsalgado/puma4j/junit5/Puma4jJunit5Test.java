package io.github.vitorsalgado.puma4j.junit5;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.annotations.UseGson;
import io.github.vitorsalgado.puma4j.annotations.UseJackson;
import io.github.vitorsalgado.puma4j.junit5.models.ComplexModel;
import io.github.vitorsalgado.puma4j.junit5.models.SimpleModel;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class Puma4jJunit5Test {

  @UsePuma4j
  static class General {

    @Test
    void loadFromTwoLevelSubPaths(final @Res("inner/sub/simple.json") SimpleModel resource) {
      assertEquals(1000, resource.getCount());
      assertEquals("hello world", resource.getMessage());
    }
  }

  @UsePuma4j("inner")
  static class WithSubPath {

    @Res("inner.json")
    private static SimpleModel staticFieldRes;

    @Res("inner.json")
    private SimpleModel fieldRes;

    @Test
    @DisplayName("should load resource from base path provided on @UsePuma4j annotation")
    void loadFromBasePath(@Res("inner.json") final SimpleModel model) {
      assertNotNull(model);
      assertNotNull(staticFieldRes);
      assertNotNull(fieldRes);

      assertEquals(100, model.getCount());
      assertEquals("inner json test message", model.getMessage());
      assertEquals(model, staticFieldRes);
      assertEquals(model, fieldRes);
      assertEquals(fieldRes, staticFieldRes);
    }

    @Test
    @DisplayName(
        "should load resource from sub path inside the one specified on @UsePuma4j annotation")
    void testSubPathInsideBasePath(final @Res("sub/simple.json") SimpleModel resource) {
      assertEquals(1000, resource.getCount());
      assertEquals("hello world", resource.getMessage());
    }
  }

  @TestInstance(Lifecycle.PER_CLASS)
  @UsePuma4j
  static class WithLifeCyclePerClass {

    @Res("complex.json")
    private static List<ComplexModel> staticComplexJson;

    @Res("simple.json")
    private static SimpleModel simpleStaticJson;

    @Res("hello_world.txt")
    private String helloWorld;

    @Test
    @DisplayName("should load resource based on @Res() value when annotated on test method")
    void loadJsonFromMethodParameterRef(final @Res("simple.json") SimpleModel testModel) {
      assertEquals(1000, testModel.getCount());
      assertEquals("hello world", testModel.getMessage());
    }

    @Test
    @DisplayName("should be able to parse an complex object type")
    void complexTypeTest(
        final @Res("complex.json") List<ComplexModel> complexTypes,
        final @Res("simple.json") SimpleModel simpleModel,
        final @Res("hello_world.txt") String helloWorld) {
      assertEquals(complexTypes, staticComplexJson);
      assertEquals(simpleModel, simpleStaticJson);
      assertEquals(helloWorld, this.helloWorld);
    }
  }

  @TestInstance(Lifecycle.PER_METHOD)
  @UsePuma4j
  static class WithLifeCyclePerMethod {

    @Res("complex.json")
    private static List<ComplexModel> staticComplexJson;

    @Res("simple.json")
    private static SimpleModel simpleStaticJson;

    @Res("hello_world.txt")
    private String helloWorld;

    @Test
    @DisplayName("should load resource based on @Res() value when annotated on test method")
    void loadJsonFromMethodParameterRef(final @Res("simple.json") SimpleModel testModel) {
      assertEquals(1000, testModel.getCount());
      assertEquals("hello world", testModel.getMessage());
    }

    @Test
    @DisplayName("should be able to parse an complex object type")
    void complexTypeTest(
        final @Res("complex.json") List<ComplexModel> complexTypes,
        final @Res("simple.json") SimpleModel simpleModel,
        final @Res("hello_world.txt") String helloWorld) {
      assertEquals(complexTypes, staticComplexJson);
      assertEquals(simpleModel, simpleStaticJson);
      assertEquals(helloWorld, this.helloWorld);
    }
  }

  @UsePuma4j
  @UseGson
  static class WithClassLevelUnmarshaller {

    @Test
    @DisplayName("should use gson only as it is defined on class level")
    void testUsingGsonFromClassLevel(final @Res("key_value.json") GsonReq resource) {
      assertEquals("key-100", resource.getName());
      assertEquals("value-100", resource.getData());
    }

    @Test
    @DisplayName(
        "should ignore from class level and use jackson when method parameter is annotated with"
            + " @UseJackson()")
    void testUsingJacksonFromParameterLevel(
        final @UseJackson @Res("key_value.json") JacksonReq resource) {
      assertEquals("key-100", resource.getName());
      assertEquals("value-100", resource.getData());
    }

    static class GsonReq {

      @SerializedName("key")
      private final String name;

      @SerializedName("value")
      private final String data;

      GsonReq(final String name, final String data) {
        this.name = name;
        this.data = data;
      }

      public String getName() {
        return name;
      }

      public String getData() {
        return data;
      }
    }

    static class JacksonReq {

      @JsonProperty("key")
      private String name;

      @JsonProperty("value")
      private String data;

      public JacksonReq(final String name, final String data) {
        this.name = name;
        this.data = data;
      }

      public JacksonReq() {
      }

      public String getName() {
        return name;
      }

      public String getData() {
        return data;
      }
    }
  }
}
