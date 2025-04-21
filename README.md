# peekr

## Overview
This project is a task management application built with Spring Boot and GraphQL, focused on exploring GraphQL concepts and modern Spring Boot practices.

## Features
- Task querying with dynamic filters defined by the frontend in GraphQL requests.
- Integration with PostgreSQL, without using an ORM.
- GraphQL `types` and `inputs` to structure requests and responses.

## Technologies Used
- Spring Boot
- GraphQL
- PostgreSQL

## Setup
To run the project locally, make sure PostgreSQL is running on port `5432`, and the API on `8080`.

Once everything's up, open your browser and go to **http://localhost:8080/graphiql**.

## Example Query

```graphql
query foo {
  tasksByFilters(
    filters: [{name: "tasks.name", operator: EQUALS, values: ["bar"]}, {name: "tasks.id", operator: IN, values: ["1", "2"]}]
  ) {
    id
    name
    owner {
      id
      name
    }
    status
    createdAt
    completedAt
  }
}
```
