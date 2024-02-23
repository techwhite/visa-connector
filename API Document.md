

# RESTful API Documentation

## Introduction

This document provides comprehensive information about the RESTful API project. It covers the endpoint,
request method, request and response format, authentication, and other relevant details.

## Authentication

The API requires authentication using API keys. Include the API key/value in the headers of each request:
### key
```text
Authorization
```
### value
The value's token can get from Post /login API (Please see below)
```text
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmR5LnlvdSIsImlhdCI6MTcwNDU5NDY1M30" +
".RdBMCIUlpZJJ0-mHWNwwzJc70cuNOR2BoTz385GBtOA
```

## Endpoints

#### 1. loginUser
```text
POST /login
```
##### Description:
```text
get token for other api request. Here you should provide username and password information
```
##### Parameters:
```json
{
    "name": "andy-you",
    "password": "mkfewfdk1343"
}
```
##### Response Body:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmR5LXlvdSIsImlhdCI6MTcwODY1NTQxNiwiZXhwIjoxNzA4NjkxNDE2fQ.i4LddlCbAhhkddJhjMM0RM3RuQS26ABlx-fEc1p1Zzc"
}
```

#### connect
```text
GET /connection
```
##### Description:
```text
test connection availability of visa service
```
##### Parameters:
none
##### Response Body:
```json
{
  "code": 200,
  "status": "OK",
  "message": "{\"timestamp\":\"2024-02-23T01:56:20\",\"message\":\"helloworld\"}",
  "stackTrace": null,
  "data": null
}
```

## Error Handling
### Global Exception Handler
```text
// Global exception handling involves the centralization of exception handling logic.
// It defines how the application should respond to different types of exceptions, like
// logging the error, providing custom error messages, and returning specific HTTP status codes.
```

### HttpStatus
```text

The API follows standard HTTP status codes. In case of an error, additional details will be provided in the response body.

Example error response:
```
```json
{
  "code": 200,
  "status": "OK",
  "message": "{\"responseStatus\":{\"status\":401,\"code\":\"9124\",\"severity\":\"ERROR\",\"message\":\"Incorrect credentials supplied\",\"info\":\"\"}}",
  "stackTrace": null,
  "data": null
}
```

## Versioning

## Changelog
version: v1
date: Feb 23, 2024
Author: Andy You