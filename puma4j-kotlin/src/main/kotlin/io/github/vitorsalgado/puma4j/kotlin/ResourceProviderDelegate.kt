package io.github.vitorsalgado.puma4j.kotlin

import io.github.vitorsalgado.puma4j.core.Context
import io.github.vitorsalgado.puma4j.core.Provider
import io.github.vitorsalgado.puma4j.core.Puma4j
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaGetter

class ResourceProviderDelegate<in R : Any, T>(private val resource: String) {
  operator fun getValue(thisRef: R, property: KProperty<*>): T {
    val context = Context("")
    val provider = Puma4j.instance().resourceProvider()

    return provider.provide(
        Provider.Args.method(
            context,
            thisRef.javaClass,
            resource,
            property.javaGetter)) as T
  }

  operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
  }
}
