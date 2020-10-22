package org.example.resource;

import org.example.model.Person;
import org.example.model.PersonResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonResourceTest {

  @Test
  public void testGetRandomPerson() {
    Response response = new PersonResource().getRandomPerson();

    Person expected = new Person("Random", "Person");

    assertNotNull(response);
    assertEquals(200, response.getStatus());
    assertEquals(expected, response.getEntity());
  }

  @Test
  public void testAddPerson() {
    Person body = new Person("Bob", "Smith");
    Response response = new PersonResource().addPerson(body);

    PersonResponse expected = new PersonResponse(body);

    assertNotNull(response);
    assertEquals(200, response.getStatus());
    assertEquals(expected, response.getEntity());
  }

}
