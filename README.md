# Simple Spring Boot Service

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