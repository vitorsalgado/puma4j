package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.vitorsalgado.resources.injector.junit5.Res;
import io.github.vitorsalgado.resources.injector.junit5.TestResourceParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestResourceParameterResolver.class)
public class JUnit5ExtensionUsageExample {

  @Test
  void itShouldInjectAnObjectInstanceWhenParameterTypeIsObject(
      final @Res("fixtureTest.json") TestData testData) {
    assertEquals(123, testData.getId());
    assertEquals("hello world", testData.getTest());
  }
}
