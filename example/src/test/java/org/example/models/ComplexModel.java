package org.example.models;

import java.util.Objects;

public class ComplexModel {

  private int id;
  private String test;
  private Inner inner;

  public ComplexModel(final int id, final String test, final Inner inner) {
    this.id = id;
    this.test = test;
    this.inner = inner;
  }

  public ComplexModel() {}

  public int getId() {
    return id;
  }

  public String getTest() {
    return test;
  }

  public Inner getInner() {
    return inner;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ComplexModel that = (ComplexModel) o;
    return id == that.id && Objects.equals(test, that.test) && Objects.equals(inner, that.inner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, test, inner);
  }

  @Override
  public String toString() {
    return "ComplexModel{" + "id=" + id + ", test='" + test + '\'' + ", inner=" + inner + '}';
  }

  public static class Inner {

    private String name;

    public Inner(final String name) {
      this.name = name;
    }

    public Inner() {}

    public String getName() {
      return name;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      final Inner inner = (Inner) o;
      return Objects.equals(name, inner.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }

    @Override
    public String toString() {
      return "Inner{" + "name='" + name + '\'' + '}';
    }
  }
}
