package io.github.vitorsalgado.puma4j.core

import io.github.vitorsalgado.puma4j.annotations.Res
import io.github.vitorsalgado.puma4j.core.models.SimpleModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KotlinSupportTest {

  @Res("simple.json")
  private val valJson: SimpleModel? = null
  @Res("simple.json")
  private lateinit var varJson: SimpleModel

  private val provider: Provider = DefaultResourceProvider()

  @Test
  fun `should load resource from annotated fields`() {
    val context = Context("")

    val valJsonResource = provider.provide(
      Provider.Args.annotatedType(
        context,
        KotlinSupportTest::class.java,
        KotlinSupportTest::class.java.getDeclaredField("valJson")
      )
    ) as SimpleModel

    val varJsonResource = provider.provide(
      Provider.Args.annotatedType(
        context,
        KotlinSupportTest::class.java,
        KotlinSupportTest::class.java.getDeclaredField("varJson")
      )
    ) as SimpleModel

    assertNotNull(valJsonResource)
    assertNotNull(varJsonResource)
    assertEquals(valJsonResource, varJsonResource)
  }
}
