# Notes App API

This repository contains multiple versions of the Notes App API, each providing different response handling mechanisms.

## Versions

- [Version 1](#version-1): Default ResponseEntity
- [Version 2](#version-2): CustomResponse
- [Version 3](#version-3): CustomResponse wrapped in ResponseEntity

## Version 1

This version uses the default `ResponseEntity` for handling API responses.

### Endpoints

- **GET /api/v1/notes/list**: Get all notes.
- **GET /api/v1/notes/{id}**: Get a note by ID.
- **POST /api/v1/notes/add**: Create a new note.
- **PUT /api/v1/notes/edit/{id}**: Update an existing note.
- **DELETE /api/v1/notes/delete/{id}**: Delete a note by ID.

### Advantages:
- _Code simplicity_

### Problems:

- _Insufficient control over returned response._


## Version 2

This version introduces a custom response wrapper, `CustomResponse`, to handle different response scenarios.

### Endpoints

- **GET /api/v2/notes/list**: Get all notes.
- **GET /api/v2/notes/{id}**: Get a note by ID.
- **POST /api/v2/notes/add**: Create a new note.
- **PUT /api/v2/notes/edit/{id}**: Update an existing note.
- **DELETE /api/v2/notes/delete/{id}**: Delete a note by ID.

### Advantages:
- _Full control over returned response._

### Problems:

- _Returned HTTP status code always 200Ok_.
- _More complex code._

## Version 3

This version uses CustomResponse wrapped in ResponseEntity.

### Endpoints

- **GET /api/v3/notes/list**: Get all notes.
- **GET /api/v3/notes/{id}**: Get a note by ID.
- **POST /api/v3/notes/add**: Create a new note.
- **PUT /api/v3/notes/edit/{id}**: Update an existing note.
- **DELETE /api/v3/notes/delete/{id}**: Delete a note by ID.

### Advantages:
- _Full control over returned response._
- _Full control over returned HTTP status code._

### Problems:

- _Insufficient control over returned response_.
- _Even more complex code._

## SWAGGER DOCS
- Launch the application
- Click -> 
[![Swagger](https://img.shields.io/badge/API-Documentation-orange.svg?style=flat)](http://localhost:8080/dev13)


