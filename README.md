# SCHEDULE API :calendar:
<h1 align="center">
  <p>SCHEDULE API </p>
  <p>Java | Spring Boot | PostgreSQL</p>
</h1>


<p align="center">
  <a href="#sobre-wave">About</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#como-usar-rocket">How to use</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#funcionalidades-star">Resources</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#tecnologias-man_technologist">Techs</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#documentação-book">Documentation</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="https://todolist-java-ypkr.onrender.com/tasks/all" target="_blank">Running on Heroku</a>
</p>

<br/>


## About :wave:

A `Tasks API` é uma API RESTful criada para simplificar o gerenciamento de tarefas. Com ela é possível realizar o cadastro de usuários e realizar o **CRUD** das tarefas, sendo possível criar, editar, excluir e listar tarefas de acordo com cada usuário autenticado e autorizado. Foi desenvolvida utilizando Java, Maven, Spring Boot e H2 Database como banco de dados em memória.


## How to Use :rocket:

Primeiro rode o servidor da aplicação com o comando:

```bash
mvn spring-boot:run
```

Para acessar as informações no Banco de Dados acesse o [Link](http://localhost:8080/h2-console/login.jsp?jsessionid=4bf1d0ac053d3554d4039c013a0e6305)


Utilize as informações abaixo para ter acesso:

* JDBC URL: `jdbc:h2:mem:todolist`
* Usuário: `admin`
* Senha: `admin`


## Resources:star:

Schedule API works with the following tables:

 - `Users`

   

Em relação ao `Users`, é possível acessar a rota:

 - `POST /users/create`
 > Cria um novo usuário com os dados fornecidos no corpo da requisição, verifica se o email do usuário já existe no banco de dados, realiza a criptografia da senha do usuário.

```BASH
// Exemplo de acesso para a rota POST /users/create

https://todolist-java-ypkr.onrender.com/users/create
```

<details>
  <summary>Exemplo de Resposta</summary>

```javascript
{
  "id": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
  "username": "hormandev",
  "name": "André Horman",
  "password": "$2a$12$Fu1zwPvszgvy5My37kv9OOfrYI7pW5vaUIfhIiC3fuYzMctTAr7Hm",
  "createdAt": "2023-10-14T01:48:57.067647"
}
```

</details>


Em relação ao `Tasks`, é possível acessar as seguintes rotas:

 - `POST /tasks/create`
 > Cria uma nova tarefa com os dados fornecidos no corpo da requisição, valida se o usuário é autenticado de acordo com o Username e o Password cadastrado.

```BASH
// Exemplo de acesso para a rota POST /tasks/create

https://todolist-java-ypkr.onrender.com/tasks/create
```

<details>
  <summary>Exemplo de Resposta</summary>

```javascript
{
  "id": "b895e822-36ed-42d3-9005-9c4357569a08",
  "idUser": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
  "title": "Aprender Java",
  "description": "Gerenciador de tarefas criado com Java + Spring Boot",
  "priority": "Alta",
  "startAt": "2023-10-14T12:30:00",
  "endAt": "2023-10-14T13:45:00",
  "createdAt": "2023-10-14T01:58:57.958766"
}
```

</details>

 - `GET /tasks/all`
 > Retorna uma lista com todas as tarefas cadastradas.

```BASH
// Exemplo de acesso para a rota GET /tasks/all

https://todolist-java-ypkr.onrender.com/tasks/all
```

<details>
  <summary>Exemplo de Resposta</summary>

```javascript
[
  {
    "id": "b895e822-36ed-42d3-9005-9c4357569a08",
    "idUser": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
    "title": "Aprender Java",
    "description": "Gerenciador de tarefas criado com Java + Spring Boot",
    "priority": "Alta",
    "startAt": "2023-10-14T12:30:00",
    "endAt": "2023-10-14T13:45:00",
    "createdAt": "2023-10-14T01:58:57.958766"
  },
  {
    "id": "6fa479d0-ce09-42a2-b5b9-a24718b64fd5",
    "idUser": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
    "title": "Criar Documentação",
    "description": "Descrever todas as Rotas cadastradas na Tasks API",
    "priority": "Alta",
    "startAt": "2023-10-14T12:30:00",
    "endAt": "2023-10-14T13:45:00",
    "createdAt": "2023-10-14T02:01:50.555669"
  }
]
```

</details>

 - `GET /tasks/all-user`
 > Retorna uma lista com todas as tarefas cadastradas de acordo com o usuário autenticado.

```BASH
// Exemplo de acesso para a rota GET /tasks/all-user

https://todolist-java-ypkr.onrender.com/tasks/all-user
```

<details>
  <summary>Exemplo de Resposta</summary>

```javascript
[
  {
    "id": "b895e822-36ed-42d3-9005-9c4357569a08",
    "idUser": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
    "title": "Aprender Java",
    "description": "Gerenciador de tarefas criado com Java + Spring Boot",
    "priority": "Alta",
    "startAt": "2023-10-14T12:30:00",
    "endAt": "2023-10-14T13:45:00",
    "createdAt": "2023-10-14T01:58:57.958766"
  },
  {
    "id": "6fa479d0-ce09-42a2-b5b9-a24718b64fd5",
    "idUser": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
    "title": "Criar Documentação",
    "description": "Descrever todas as Rotas cadastradas na Tasks API",
    "priority": "Alta",
    "startAt": "2023-10-14T12:30:00",
    "endAt": "2023-10-14T13:45:00",
    "createdAt": "2023-10-14T02:01:50.555669"
  }
]
```

</details>

 - `PUT /tasks/{idTask}`
 > Retorna as informações atualizadas da tarefa, valida se o id é passado como parâmetro na rota e se a tarefa pertence ao usuário autenticado.

```BASH
// Exemplo de acesso para a rota PUT /tasks/{idTask}

https://todolist-java-ypkr.onrender.com/tasks/b895e822-36ed-42d3-9005-9c4357569a08
```

<details>
  <summary>Exemplo de Resposta</summary>

```javascript
{
  "id": "b895e822-36ed-42d3-9005-9c4357569a08",
  "idUser": "7adfeab0-2b75-4da3-9c2a-8392576013ea",
  "title": "TESTE",
  "description": "XABLAU",
  "priority": "Alta",
  "startAt": "2023-10-14T12:30:00",
  "endAt": "2023-10-14T13:45:00",
  "createdAt": "2023-10-14T01:58:57.958766"
}
```

</details>


## Techs:man_technologist:

Principais tecnologias utilizadas no projeto

* [Spring Initializr](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=br.com.andrehorman&artifactId=todolist&name=todolist&description=Gerenciador%20de%20tarefas&packageName=br.com.andrehorman.todolist&dependencies=web,devtools,lombok,data-jpa,h2)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#using.devtools)
* [Project Lombok](https://projectlombok.org/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Bcrypt Java](https://github.com/patrickfav/bcrypt)


## Documentation:book:

Guias de como utilizar algumas features de forma concreta:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/#build-image)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
