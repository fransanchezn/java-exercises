package org.fransanchez;

public record SomeClass(String prop1, int prop2) {
  public String someMethod() {
    return prop1 + prop2;
  }
}
