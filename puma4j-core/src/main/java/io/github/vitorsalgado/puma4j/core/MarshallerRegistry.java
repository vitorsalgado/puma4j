package io.github.vitorsalgado.puma4j.core;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class MarshallerRegistry {

  private static final Map<String, Marshaller<?>> READERS_BY_EXTENSION = new HashMap<>();

  void registerMarshallerForExtension(final String extension, final Marshaller<?> parser) {
    requireNonNull(extension);
    requireNonNull(parser);

    READERS_BY_EXTENSION.put(extension, parser);
  }

  Optional<Marshaller<?>> getMarshallerByExtension(final String extension) {
    return Optional.ofNullable(READERS_BY_EXTENSION.get(requireNonNull(extension)));
  }
}
