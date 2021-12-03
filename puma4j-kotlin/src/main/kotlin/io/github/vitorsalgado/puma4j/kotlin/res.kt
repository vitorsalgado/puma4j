package io.github.vitorsalgado.puma4j.kotlin

fun <R : Any, T> res(resource: String) = ResourceProviderDelegate<R, T>(resource)
