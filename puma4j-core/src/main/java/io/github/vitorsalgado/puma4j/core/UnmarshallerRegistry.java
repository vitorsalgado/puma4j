package io.github.vitorsalgado.puma4j.core;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class UnmarshallerRegistry {

  private static final Map<String, Unmarshaller<?>> READERS_BY_EXTENSION = new HashMap<>();

  void registerMarshallerForExtension(final String extension, final Unmarshaller<?> parser) {
    requireNonNull(extension);
    requireNonNull(parser);

    READERS_BY_EXTENSION.put(extension, parser);
  }

  Optional<Unmarshaller<?>> getMarshallerByExtension(final String extension) {
    return Optional.ofNullable(READERS_BY_EXTENSION.get(requireNonNull(extension)));
  }
}
