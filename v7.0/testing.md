# Testing

Have a PostGres database available, for example through Docker. 

---
docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 postgres
---


## Config

Provide the credentials for the database as configuration values within the `microprofile-config.properties` file.

## Build

Build the project

----
mvn clean package
----

## Run on Payara Micro

If needed, download a Payara Micro version that supports MicroProfile 4.x (version  5.2021.1 or later. Download from Maven Central: https://mvnrepository.com/artifact/fish.payara.extras/payara-micro/5.2022.2)

----
java -jar payara-micro.jar  <path-to>/database-example.war
----

See if the configuration is successful by executing the following command

----
curl 'http://localhost:8080/database-example/products/'
----

This will start the MicroStream Storage manager and you should see the following entry on the console where you have started Payara Micro with the application

----
Loading default StorageManager loading from MicroProfile Config properties. The keys: [storage-filesystem.sql.postgres.password, storage-filesystem.sql.postgres.url, storage-filesystem.sql.postgres.data-source-provider, storage-filesystem.sql.postgres.user]
----

That clearly indicates it is taken the configuration keys related to the database configuration.

## Storing data

Call the endpoint with some JSON data and store a product.

----
curl -X POST 'http://localhost:8080/database-example/products/' 
--header 'Content-Type: application/json' 
--data-raw '{"id": 1, "name": "banana", "description": "a fruit", "rating": 5}'
----

And verify if the data is in memory with the call

----
curl 'http://localhost:8080/database-example/products/'
----

Stop the Payara Micro instance and start it again. Verify if the product data for Banana is still there.

## More testing

Add an additional product

----
curl -X POST 'http://localhost:8080/database-example/products/' \
--header 'Content-Type: application/json' \
--data-raw '{"id": 2, "name": "watermelon", "description": "watermelon sugar ahh", "rating": 4}'
----

Delete the Banana one

----
curl -X DELETE 'http://localhost:8080/database-example/products/1'
----

Stop the Payara Micro instance and start it again.

Do we still have the Watermelon entry?

