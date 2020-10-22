package org.example;

import org.example.resource.PersonResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/sample/v1")
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    register(PersonResource.class);
  }

}
