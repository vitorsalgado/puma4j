package io.github.vitorsalgado.puma4j.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ResourceProviderDelegateTest {

  private var data: String by res("data.txt")

  @Test
  fun test() {
    assertEquals("hello world", data)
  }
}
