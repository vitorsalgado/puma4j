package org.example.juni5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.vitorsalgado.puma4j.annotations.Res;
import io.github.vitorsalgado.puma4j.annotations.Use;
import io.github.vitorsalgado.puma4j.core.Unmarshaller;
import io.github.vitorsalgado.puma4j.junit5.UsePuma4j;
import org.example.models.ComplexModel;
import org.example.models.SimpleModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UsePuma4j
class UsageWithJUnit5Test {

  @Res("data.txt")
  private static String staticTextData;

  @Res("complex.json")
  private List<ComplexModel> complexModelList;

  @Res("complex-yml.yaml")
  private List<ComplexModel> complexModelListFromYaml;

  @Res("test.properties")
  private Properties testProperties;

  @Res("simple.json")
  @Use(JsonTreeUnmarshaller.class)
  private JsonNode simpleJsonTree;

  @Test
  void simpleTest(final @Res("simple.json") SimpleModel model) {
    assertEquals(50, model.getAge());
    assertEquals("no-one", model.getName());
  }

  public static class JsonTreeUnmarshaller implements Unmarshaller<JsonNode> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public JsonNode unmarshal(final Args args) throws IOException {
      return OBJECT_MAPPER.readTree(args.getInput());
    }
  }
}
