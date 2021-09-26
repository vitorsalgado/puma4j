package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FixtureParameterResolver.class)
public class FixtureParameterResolverTest {

  @Test
  void test_fixture_annotation_when_fixtureTypeIsObject(
      final @Fixture("fixtureTest.json") TestData testData) {
    assertEquals(123, testData.getId());
    assertEquals("hello world", testData.getTest());
  }

  @Test
  void should_return_helloWorldMessage(final @Fixture("fixtureTest.json") String message) {
    assertEquals("{\n" + "  \"id\": 123,\n" + "  \"test\": \"hello world\"\n" + "}", message);
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
