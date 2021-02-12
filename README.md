# User Authentication

User Authentication is a Java Spring Boot with Hibernate project to do unit testing of the user authentication class which is used in the main web application. The objective is to create a JUnit class that will test all aspects of the authentication class.

## Installation

Use [docker](https://docs.docker.com/get-docker/) for database.

```bash
docker-compose --file docker/compose.yaml up --build -d
```
Run in IDE by importing /feedback 
or by using Maven
```bash
cd authentication/
mvn spring-boot:run
```

## Usage

Post to http://localhost:8081/authenticate to authenticate user.
```bash
curl -d "username=april13&password=password123" -X POST http://localhost:8081/authenticate
```

## Stopping

```bash
docker-compose --file docker/compose.yaml down
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
