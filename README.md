# <span style="vertical-align: sub"><img src="src/main/resources/icons/marathon.png"></span> Marathon Application <span style="vertical-align: sub"><img src="src/main/resources/icons/marathon.png"></span>

### <span style="vertical-align: middle"><img src="src/main/resources/icons/java.png"></span> Project description:

This is a simple implementation of a marathon application, created using Spring Boot, REST principles and supports parse and CRUD operations.

### <span style="vertical-align: middle"><img src="src/main/resources/icons/features.png"></span> Features:

This app parses information about all marathon participants from logs and displays to user top ten competitors in JSON format.

Supported operations:
- get Top 10 competitors

### <span style="vertical-align: middle"><img src="src/main/resources/icons/structure.png"></span> Structure:

1. `java.app.marathon` package folders:
    - `controller` - contains presentation layer's class
    - `dto` - contains model's response DTO
    - `model` - contains competitor's model
    - `service` - contains business logic layer's interfaces/classes and DTO mappers

2. `resources` package:
    - Spring Boot configuration folders
    - icons for `README.md`
    - logs with input data

### <span style="vertical-align: middle"><img src="src/main/resources/icons/endpoints.png"></span> List of endpoints:

- `GET`: /competitors/top-ten - get top 10 competitors based on result time

### <span style="vertical-align: middle"><img src="src/main/resources/icons/tech-stack.png"></span> Technologies:

- `Spring Boot`
- `Hibernate`
- `Maven`
- `REST`

### <span style="vertical-align: middle"><img src="src/main/resources/icons/run-project.png"></span> Instructions to run the project

- Install IDE to your PC
- Clone the project to your IDE (e.g. IntelliJ IDEA)
- Run the project via Main method - `MarathonApplication`
- Use `Postman` and send GET request

GET request to retrieve data using Postman:
```
http://localhost:8080/competitors/top-ten - URL
```