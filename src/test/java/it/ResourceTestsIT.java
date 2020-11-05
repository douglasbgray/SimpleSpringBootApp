package it;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.SampleApplication;
import org.example.model.Person;
import org.example.model.PersonResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SampleApplication.class})
public class ResourceTestsIT {

  @LocalServerPort
  int randomServerPort;

  private String itEndpoint;
  private Boolean simulateFailure = false;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Before
  public void setUp() {
    String itEndpointFromSystem = System.getProperty("itEndpoint");
    itEndpoint = StringUtils.isNotBlank(itEndpointFromSystem) ? itEndpointFromSystem : "http://localhost:" + randomServerPort;
    System.out.println("+++ IT Endpoint = " + itEndpoint);

    String simulateFailureFromSystem = System.getProperty("simulateFailure");
    if (StringUtils.isNotBlank(simulateFailureFromSystem)) {
      simulateFailure = simulateFailureFromSystem.equalsIgnoreCase("true");
    }
    System.out.println("+++ Simulate Failure = " + simulateFailure);
  }

  @Test
  public void testGetRandomPerson() throws Exception {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(itEndpoint + "/sample/v1/person");
      CloseableHttpResponse response = httpclient.execute(httpGet);
      Person person = objectMapper.readValue(EntityUtils.toString(response.getEntity()), Person.class);

      assertEquals(200, response.getStatusLine().getStatusCode());
      assertEquals("Random", person.getFirstName());
      assertEquals("Person", person.getLastName());
    }
  }

  @Test
  public void testAddPerson() throws Exception {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      HttpPost httpPost = new HttpPost(itEndpoint + "/sample/v1/person");
      httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(new Person("Bob", "Jones"))));
      httpPost.addHeader("content-type", "application/json");
      CloseableHttpResponse response = httpclient.execute(httpPost);
      PersonResponse personResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()), PersonResponse.class);

      assertEquals(200, response.getStatusLine().getStatusCode());
      assertEquals("Received person: Bob Jones", personResponse.getMessage());
    }
  }

  @Test
  public void testReplacePerson() throws Exception {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      HttpPut httpPut = new HttpPut(itEndpoint + "/sample/v1/person");
      httpPut.setEntity(new StringEntity(objectMapper.writeValueAsString(new Person("Sally", "Smith"))));
      httpPut.addHeader("content-type", "application/json");
      CloseableHttpResponse response = httpclient.execute(httpPut);
      PersonResponse personResponse = objectMapper.readValue(EntityUtils.toString(response.getEntity()), PersonResponse.class);

      assertEquals(200, response.getStatusLine().getStatusCode());
      assertEquals("Received person: Sally Smith", personResponse.getMessage());
    }
  }

  @Test
  public void simulatedFailureTest() {
    if (simulateFailure) {
      throw new IllegalArgumentException("Simulating a test failure.");
    }
  }

}
