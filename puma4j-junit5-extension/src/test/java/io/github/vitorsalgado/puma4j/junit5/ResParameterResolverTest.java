package io.github.vitorsalgado.puma4j.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.vitorsalgado.puma4j.core.Res;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestResourceParameterResolver.class)
class ResParameterResolverTest {

  @Test
  void itShouldInjectAnObjectInstanceWhenParameterTypeIsObject(
      final @Res("fixtureTest.json") TestData testData) {
    assertEquals(123, testData.getId());
    assertEquals("hello world", testData.getTest());
  }

  @Test
  void itShouldInjectTheStringContentFromResourceFile(
      final @Res("fixtureTest.json") String message) {
    assertEquals("{\"id\": 123,\"test\": \"hello world\"}", message);
  }

  static class TestData {

    private int id;
    private String test;

    public TestData(int id, String test) {
      this.id = id;
      this.test = test;
    }

    public TestData() {}

    public int getId() {
      return id;
    }

    public String getTest() {
      return test;
    }
  }
}
