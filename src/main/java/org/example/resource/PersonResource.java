package org.example.resource;

import org.example.model.Person;
import org.example.model.PersonResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Service
@Path("/person")
public class PersonResource {

  @GET
  @Produces("application/json")
  public Response getRandomPerson() {
    Person person = new Person("Random", "Person");
    return Response.status(Response.Status.OK).entity(person).build();
  }

  @POST
  @Produces("application/json")
  @Consumes("application/json")
  public Response addPerson(Person person) {
    PersonResponse personResponse = new PersonResponse(person);
    return Response.status(Response.Status.OK).entity(personResponse).build();
  }

  @PUT
  @Produces("application/json")
  @Consumes("application/json")
  public Response replacePerson(Person person) {
    PersonResponse personResponse = new PersonResponse(person);
    return Response.status(Response.Status.OK).entity(personResponse).build();
  }

}
