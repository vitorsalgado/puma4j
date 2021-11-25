package io.github.vitorsalgado.puma4j.junit5.models;

import java.util.Objects;

public class SimpleModel {

  private int count;
  private String message;

  public SimpleModel(final int count, final String message) {
    this.count = count;
    this.message = message;
  }

  public SimpleModel() {}

  public int getCount() {
    return count;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SimpleModel that = (SimpleModel) o;
    return count == that.count && Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, message);
  }

  @Override
  public String toString() {
    return "SimpleModel{" + "count=" + count + ", message='" + message + '\'' + '}';
  }
}
