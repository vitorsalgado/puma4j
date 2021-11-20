package org.example.juni5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.vitorsalgado.puma4j.core.Res;
import io.github.vitorsalgado.puma4j.junit5.TestResourceParameterResolver;
import org.example.TestData;
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
