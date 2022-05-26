# Readme: test-0.0.1-SNAPSHOT.jar

## Requerimientos

El proyecto completo utiliza los siguientes frameworks:

* Spring Framework
* SpringBoot
* H2
* JPA
* JUNIT
* MOCKITO

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

    $ docker build -t test-crosales .
    $ docker run -d test-crosales -p 8080:8080