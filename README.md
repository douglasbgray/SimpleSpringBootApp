# Simple Spring Boot Service

## Requirements:

* JDK 11
* Maven

## Running Tests

Run this command: `mvn clean verify`

Open file in your browser: `${project_home}/target/site/jacoco/index.html`

## Operations

#### GET

`http://{host}:{port}/sample/v1/person`

**Response:** 
```
{"firstName": "Random", "lastName": "Person"}
```

#### Post

`http://{host}:{port}/sample/v1/person`

**Header:**

```
Content-Type: application/json
```

**Body:**
```
{"firstName": "Random", "lastName": "Person"}
```

**Response:**
```
{"message": "Received person: Random Person"}
```