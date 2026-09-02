package org.fransanchez;

public record SomeClass(String field1, int field2) {
  public String method() {
    return field1 + " " + field2;
  }
}
