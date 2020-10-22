package org.example.model;

import java.util.Objects;

public class PersonResponse {

  private final String message;

  public PersonResponse(Person person) {
    this.message = "Received person: " + person.getFirstName() + " " + person.getLastName();
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonResponse that = (PersonResponse) o;
    return Objects.equals(getMessage(), that.getMessage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMessage());
  }
}
