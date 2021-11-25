package org.example.models;

import java.util.Objects;

public class SimpleModel {

  private final int age;
  private final String name;

  public SimpleModel(final int age, final String name) {
    this.age = age;
    this.name = name;
  }

  public int getAge() {
    return age;
  }

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
    final SimpleModel that = (SimpleModel) o;
    return age == that.age && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(age, name);
  }

  @Override
  public String toString() {
    return "SimpleModel{" + "age=" + age + ", name='" + name + '\'' + '}';
  }
}
