# Banking Transfer Scheduler - Backend application

## Introduction
Backend application to schedule a money Transfer between two accounts.

This project was built under the MVC (Model View Controller) due to its high cohesion, easy to maintain or modify, clear divided responsabilities ans very popular among the developer community.

The View layer of its project consists into an Angular10 application [available here](https://github.com/fePremazzi/angular-banking-transfer).

To resolve how the Tax is calculated a Chain of resposability design patter was used starting from the A tax and moving to the next until it reaches its right tax calculation or throws an exception with a "Tax not found" exception.

The persistence layer was built to be in memory using [H2 database](https://www.h2database.com).

## Main components

* [Maven](https://maven.apache.org/) - Dependency and build management
* [Java jdk 14](https://openjdk.java.net/projects/jdk/14/) - Programming language, can be download [here](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html).
* [Springboot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - Main framework used
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa#overview) - used to persist data on the database.
* [H2 database](https://www.h2database.com) - In memory database
* [Modelmapepr](http://modelmapper.org/) - Responsible for mapping a model to another.
* [Junit 5](https://junit.org/junit5/) - For testing the application.

## Deployment

### Docker approach

First of all, you must have Docker installed on your machine. If you use Linux than [click here](https://docs.docker.com/engine/install/ubuntu/) and read the Docker documentation on how install Docker and docker-compose on linux or [click here](https://docs.docker.com/docker-for-windows/install/) if you are a Windows user to install Docker Desktop for Windows.

After installing docker you can deploy this application by using the following docker command on the project root folder:

```
docker run -d -p 8080:8080 fepremazzi/banking-transfer
```

You can test by importing a Postman collection of requests on Postman provided in this repository named by ``BankingScheduler.postman_collection.json``.

The command above only run a container with the backend application, if you looking for the Full application (backend and [frontend](https://github.com/fePremazzi/angular-banking-transfer)) you can simply run the docker-compose command below on the project root folder:

```
docker-compose up
```

This command will download both images (backend and frontend) and initialize their containers and expose the web application on ``http://localhost:4201``.

### Maven build approach

* Download and install Java jdk 14 version on <https://openjdk.java.net/projects/jdk/14/> and set a environment variable on PATH and JAVA_HOME with the bin folder from the Java JDK14 installation.
* You can check the installation by running a ``java -version`` command on your terminal.
* Download and install Maven on <https://maven.apache.org/install.html> following the site instructions.
* Navigate to the root folder of your project and run the ``mvn clean package`` command to build the application jar.
* Navigate to your target folder and execute the jar by using the ``java -jar banking-transfer-0.0.1-SNAPSHOT.jar`` command.
* Then it will be espose on 8080 por at ``http://localhost:8080``.
* You can test by importing a Postman collection of requests on Postman provided in this repository named by ``BankingScheduler.postman_collection.json``.
