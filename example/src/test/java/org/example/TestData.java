package org.example;

public class TestData {
  private int id;
  private String test;

  public TestData(int id, String test) {
    this.id = id;
    this.test = test;
  }

  public TestData() {}

  public int getId() {
    return id;
  }

  public String getTest() {
    return test;
  }
}