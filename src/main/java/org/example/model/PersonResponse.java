package org.example.model;

public class PersonResponse {

  private final String message;

  public PersonResponse(Person person) {
    this.message = "Received person: " + person.getFirstName() + " " + person.getLastName();
  }

  public String getMessage() {
    return message;
  }
}
