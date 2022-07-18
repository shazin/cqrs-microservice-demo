Developing Microservices with CQRS and Event Sourcing Patterns using GraphQL + Spring Boot + Kafka
================================

https://shazinsadakath.medium.com/developing-microservices-with-cqrs-and-event-sourcing-patterns-using-graphql-spring-boot-kafka-19f259a7aaa5

# Running Demonstration


Run Commands
------

`docker compose up -d`

`curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '{ "name": "user-connector", "config": { "connector.class": "io.debezium.connector.mysql.MySqlConnector", "tasks.max": "1", "database.hostname": "db", "database.port": "3306", "database.user": "root", "database.password": "root", "database.server.id": "184054", "database.server.name": "dbserver1", "database.whitelist": "userrdb", "database.history.kafka.bootstrap.servers": "kafka:9092", "database.history.kafka.topic": "dbhistory.user" } }'`

`curl -H "Accept:application/json" localhost:8083/connectors/user-connector`

