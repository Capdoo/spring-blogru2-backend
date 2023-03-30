# Spring Blogru2 Backend

Backend for the Blogru2 project

## Deployment

* [Project Deploy Swagger](https://spring-blogru-backend-f-production.up.railway.app/swagger-ui/index.html#/)


## Description

Spring Boot version > 2.6.X is used

## Start

### Dependencies

* Spring v2.6.2, Spring JPA, Mongo Starter Data, Java 8+.
* Independent Operating System

### Installation

* Clone this repository
```
https://github.com/Capdoo/spring-blogru2-backend
```

* If you are in Linux, create the folder mongo_data/ and change its permissions.
```
mkdir mongodb_data
sudo chown -R 1001:1001 mongodb_data/
```
* Run docker compose for creating the database.
```
mkdir mongodb_data
sudo chown -R 1001:1001 mongodb_data/
docker compose up -d
```


### Edit properties

* It's necessary to modify the application.properties file
* Set the username and password for the Mongo database according to the docker-compose.yml file.

```
spring.data.mongodb.uri=mongodb://{USERNAME}:{PASSWORD}@localhost:27017/?authSource=db1&replicaSet=rs1
spring.data.mongodb.database=db1
accessTokenExpirationMinutes=5
refreshTokenExpirationDays=30
accessTokenSecret=12345
refreshTokenSecret=54321
```

## Execution

Start the application with any IDE or with maven by navigating to the root directory of the project
```
mvn clean install
mvn spring-boot:run
```


## Database Diagram
![plot](./src/main/resources/static/diagram3.png)

## Authors

Contributors and links

. [@Capdoo](https://github.com/Capdoo)


## License

This project is licensed under the terms of the MIT license.

## Acknowledgments

Spring Data e Hibernate
* [Spring Mongo - Baeldung](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/)