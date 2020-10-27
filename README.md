# Simple Spring Boot Service

## Requirements:

* JDK 11
* Maven

## Running Tests

Run this command: `mvn clean verify`

Open file in your browser: `${project_home}/target/site/jacoco/index.html`

## Dockerizing

Run these commands (assuming you have Docker installed) 

```
mvn clean package
docker build -t simple-spring-boot-app:latest .
docker run -it -p8080:8080 simple-spring-boot-app:latest
```

Then access http://localhost:8080/sample/v1/person to verify working.

## Pushing to Docker Hub

Follow these instructions - https://ropenscilabs.github.io/r-docker-tutorial/04-Dockerhub.html

```
docker login --username=douglasbgray
docker images
docker tag <image_id> douglasbgray/simple-spring-boot-app:firsttry
docker push douglasbgray/simple-spring-boot-app
```
## Operations

#### GET

`http://{host}:{port}/sample/v1/person`

**Response:** 
```
{"firstName": "Random", "lastName": "Person"}
```

------------

#### POST

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