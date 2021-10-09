package org.example.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Fixture;
import org.example.FixtureParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FixtureParameterResolver.class)
public class JUnit5ExtensionUsageExample {

  @Test
  void itShouldInjectAnObjectInstanceWhenParameterTypeIsObject(
      final @Fixture("fixtureTest.json") TestData testData) {
    assertEquals(123, testData.getId());
    assertEquals("hello world", testData.getTest());
  }
}
