# Readme: test-0.0.1-SNAPSHOT.jar

## Requerimientos

El proyecto completo utiliza los siguientes frameworks:

* Spring Framework
* SpringBoot
* H2
* JPA

## Dependencias

Hay una serie de dependencias de terceros utilizadas en el proyecto. Explore el archivo Maven pom.xml para obtener detalles de las bibliotecas y las versiones utilizadas.
## Prerequisitos:

* Java JDK 8
* Maven 3.8.1
* Git
* Docker

Clone el proyecto y use Maven para construir el servidor

	$ mvn clean install

Para construir la imagen y ejecutar el contenedor de Docker, seguir las siguientes instrucciones:

    $ docker build -t test-docker .
    $ docker run -d test-docker -p 8080:8080

## OpenApi:

URL: http://localhost:8080/swagger-ui/index.html

## URL Endpoint:
```
    $ curl --location --request GET 'localhost:8080/1'
```
    $ curl --location --request POST 'http://localhost:8080/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "name": "Juan Lopez",
    "email": "j@gmail.com",
    "password": "AB1234568abj",
    "phones": [
    {
    "number": "87654321",
    "city_code": "9",
    "country_code": "57"
    }
    ]
    }'
```
    $ curl --location --request GET 'localhost:8080/all'
```
    $curl --location --request PUT 'localhost:8080/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "id": "1",
    "name": "Ana Lopez",
    "email": "a@gmail.com",
    "password": "AB1234568abj",
    "phones": [
    {
    "number": "87654321",
    "city_code": "9",
    "country_code": "57"
    }
    ]
    }'
```
    $curl --location --request DELETE 'localhost:8080/1'
```