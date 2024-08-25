# Karabook API

The Karabook API is one of the main parts of the overall Karabook project. This API contains important business logic that makes it possible to ensure the stable operation of the entire project system.

# How to use this API

If you want use this API in your development usability you need to follow next steps:

- Fetch code to your server(local or remote):

```bash

> git clone https://github.com/the-az-dev/Karabook-API.git

```

- Install Karabook API project dependencies:

```bash

> ./mvnw install

```
<br>
<hr>
<div style="font-size: 16px"><b>NOTE:</b> You need to setup configuration in application.properties using the following example in application.properties.example file.</div>
<br>

- Setup your own port to run this project on it using `server.port` parametr, ex.: 

```properties
server.port=8080
```

- Set your database URL for connecting to your project using `spring.datasource.url`, ex.:

```properties
# You set your own IP adress and Database name based on your situation and needings
spring.datasource.url=jdbc:mysql://localhost/karabook 
```

- Set driver class name using `spring.datasource.driver-class-name` based on your DB usage, ex.:


```properties
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

- Set DB name and password using `spring.datasource.username` and `spring.datasource.password` parameters based on your setups, ex.:

```properties
spring.datasource.username=karabook-db-usr
spring.datasource.password=1234567890
```
<hr>
<br>

- Next setep, compile exiting project files:

```bash

> ./mvnw compile

```

- Run package project into `.jar` file:

```bash

> ./mvnw package

```

- To run `.jar` file, you need to use this command:

```bash
> java -jar ./target/karabook-api-0.4.0.jar
```

After setup and configure you can use the following API in your application!) Enjoy!)

This API implements all possible options for requests that allow you to organize stable functionality of the project, such as - GET, POST, PUT, DELETE.

For more information and examples check - <a href="http://karabook.pp.ua/docs/api">MK API Documentation</a>