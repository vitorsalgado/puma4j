package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.vitorsalgado.resources.injector.junit5.Fixture;
import io.github.vitorsalgado.resources.injector.junit5.FixtureParameterResolver;
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
