package org.example.kotlin

import io.github.vitorsalgado.puma4j.annotations.Res
import io.github.vitorsalgado.puma4j.junit5.UsePuma4j
import io.github.vitorsalgado.puma4j.kotlin.res
import org.example.models.ComplexModel
import org.example.models.SimpleModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@UsePuma4j
class KotlinExampleTest {

  private val data: String by res("hello-world.txt")
  private val simpleModel: SimpleModel by res("simple.json")
  private val complexModel: List<ComplexModel> by res("complex.json")

  @Test
  fun test() {
    assertEquals("hello world", data)
  }

  @Test
  fun simpleResTest(@Res("simple.json") model: SimpleModel) {
    assertEquals(50, model.age)
    assertEquals("no-one", model.name)
  }

  @Test
  fun simpleTest() {
    assertEquals(50, simpleModel.age)
    assertEquals("no-one", simpleModel.name)
  }

  @Test
  fun complexTest() {
    assertEquals(1, complexModel.size)
  }
}
