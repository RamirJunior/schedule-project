<h1 align="center">
  <p>SCHEDULE API <br>
  Java | Spring Boot | PostgreSQL</p>
</h1>


<p align="center">
  <a href="#about-wave">About</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#how-to-use-rocket">How to use</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#resourcesstar">Resources</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#techscomputer">Techs</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#documentationbook">Documentation</a>&nbsp;&nbsp;&nbsp;
</p>




<br/>


## About :wave:

The `Schedule API` is a REST API designed to manage a user's appointment schedule. It allows users to register and perform CRUD operations on appointments for scheduled days. Users can create, edit, delete, and list tasks, all according to their authenticated and authorized access. The API was developed using Java, Maven, Spring Boot, and PostgreSQL as the database.


## How to Use :rocket:

First, start the application server with the command:

```bash
mvn spring-boot:run
```


I used the information below for access:

* JDBC URL: `jdbc:postgresql://localhost:5432/my-schedule-db`
* Usuário: `postgres`
* Senha: `123`


## Resources:star:

Schedule API works with the following tables:

 - `Users`

 - `Schedule`

   

#### Regarding `Users`, you can access the following routes:

 - `POST /user`
 > Creates a new user with the data provided in the request body and checks if the user's email already exists in the database.

```BASH
// Example for access to route POST /user

localhost:8080/user
```

<details>
  <summary>Response Example</summary>


```javascript
{
  "id": "3a056937-d969-45f3-9e9d-1bb3b8be7146",
  "name": "Ramir",
  "lastname": "Junior"
}
```

</details>

 - `GET /user`

 > Lists all registered users in the database.

```BASH
// Example for access route GET /user

localhost:8080/user
```

<details>
  <summary>Response Example</summary>


```javascript
[
    {
        "id": "3a056937-d969-45f3-9e9d-1bb3b8be7146",
        "name": "Ramir",
        "lastname": "Junior"
    },
    {
        "id": "02f1b7e8-7ac7-4c4b-ac92-e940dca5477e",
        "name": "Mariana",
        "lastname": "Ribeiro"
    }
]
```

</details>

 - `GET /user/id`

 > Returns the user found corresponding to the `id` provided in the URI.

```BASH
// Example for access route GET /user/{id}

localhost:8080/user/02f1b7e8-7ac7-4c4b-ac92-e940dca5477e
```

<details>
  <summary>Response Example</summary>


```javascript
{
    "id": "02f1b7e8-7ac7-4c4b-ac92-e940dca5477e",
    "name": "Mariana",
    "lastname": "Ribeiro"
}
```

</details>

 - `PUT /user/id`

 > Updates the user corresponding to the id according to the information provided in the request `body`.

```BASH
// Example for access route PUT /user/{id}

localhost:8080/user/02f1b7e8-7ac7-4c4b-ac92-e940dca5477e

//BODY
{
    "name":"Mari"
}
```

<details>
  <summary>Response Example</summary>


```javascript
{
    "id": "02f1b7e8-7ac7-4c4b-ac92-e940dca5477e",
    "name": "Mari",
    "lastname": "Ribeiro"
}
```

</details>

 - `DELETE /user/id`

 > Deletes the user record corresponding to the provided `id`.

```BASH
// Exemplo de acesso para a rota DELETE /user/{id}

localhost:8080/user/02f1b7e8-7ac7-4c4b-ac92-e940dca5477e
```

<details>
  <summary>Response Example</summary>


```javascript
{
    "id": "02f1b7e8-7ac7-4c4b-ac92-e940dca5477e",
    "name": "Mariana",
    "lastname": "Ribeiro"
}
```

</details>

#### Regarding Schedule, you can access the following routes:

 - `POST /schedule`
 > Schedules a new appointment with the data provided in the request body, validates if the user exists in the database according to `userId`, and also validates if the desired date is a future or current date.

```BASH
// Example for access route POST /schedule

localhost:8080/schedule

// BODY
{
    "description":"Estudar Custom Validations",
    "dateTime":"2023-12-13T09:00:02",
    "userId":"3a056937-d969-45f3-9e9d-1bb3b8be7146"
}
```

<details>
  <summary>Response Example</summary>


```javascript
{
  "id": "583a02ff-9541-4484-a6f6-f395cbc5e90d",
    "description": "Estudar Custom Validations",
    "dateTime": "2023-12-13T09:00:02.0386635",
    "user": {
        "id": "3a056937-d969-45f3-9e9d-1bb3b8be7146",
        "name": "Ramir",
        "lastname": "Junior"
    }
}
```

</details>

 - `GET /schedule`
 > Returns a list of all appointments registered in the database.

```BASH
// Example for access route GET /schedules

localhost:8080/user
```

<details>
  <summary>Response Example</summary>


```javascript
[
    {
        "id": "583a02ff-9541-4484-a6f6-f395cbc5e90d",
    	"description": "Estudar Custom Validations",
    	"dateTime": "2023-12-13T09:00:02.0386635",
    	"user": {
        	"id": "3a056937-d969-45f3-9e9d-1bb3b8be7146",
        	"name": "Ramir",
        	"lastname": "Junior"
    	}
    },
    {
        "id": "3f660fdb-0a63-4ba2-aabb-27d30d2df328",
        "description": "Reunião com o time de desenvolvimento",
        "dateTime": "2023-11-30T19:30:00.400832",
        "user": {
            "id": "02f1b7e8-7ac7-4c4b-ac92-e940dca5477e",
            "name": "Mari",
            "lastname": "Ribeiro"
        }
    }
]
```

</details>




## Techs :computer:

Key technologies used in the project:

* [Spring Initializr](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=br.com.andrehorman&artifactId=todolist&name=todolist&description=Gerenciador%20de%20tarefas&packageName=br.com.andrehorman.todolist&dependencies=web,devtools,lombok,data-jpa,h2)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://github.com/spring-projects/spring-data-jpa)
* [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
* [Java-Jwt](https://github.com/auth0/java-jwt)
* [Project Lombok](https://projectlombok.org/)
* [Flyway](https://documentation.red-gate.com/fd)
* [Model Mapper](https://modelmapper.org/getting-started/)


## Documentation:book:

Guides on how to use some features concretely.:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/#build-image)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
