package io.github.vitorsalgado.puma4j.core.models;

import java.util.Objects;

public class SimpleDataModel {

  private int age;
  private String name;

  public SimpleDataModel(final int age, final String name) {
    this.age = age;
    this.name = name;
  }

  public SimpleDataModel() {
  }

  public int getAge() {
    return age;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final SimpleDataModel that = (SimpleDataModel) o;
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
