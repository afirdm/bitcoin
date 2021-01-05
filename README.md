[![Build Status](https://travis-ci.com/afirdm/bitcoin.svg?branch=main)](https://travis-ci.com/github/afirdm/bitcoin)

# Bitcoin - Code Challenge Wenance

![N|Solid](https://images.idgesg.net/images/article/2018/02/bitcoin_increase_growth_on_fire_thinkstock_886921368-100749770-large.jpg)

- ### Enunciado:
A) Construir un microservicio que haciendo uso del siguiente servicio REST (https://cex.io/api/last_price/BTC/USD)
realice una llamada recurrente cada 10 segundos, almacene los datos y que exponga a través de un API rest las siguientes funcionalidades:

1. Obtener el precio del bitcoin en cierto timestamp.
2. Conocer el promedio de valor entre dos timestamps así como la diferencia porcentual entre ese valor promedio y el valor máximo almacenado para toda la serie temporal disponible.

B) Incoporar un conjunto de test unitarios que demuestran la corrección de la solución

C) incorporar un archivo READ.ME que contenga una descripción de la solución propuesta así como instrucciones de ejecución en entorno local.

Indicaciones:
* La aplicación deberá estar desarrollada usando Springboot y subida a un repositorio en github con permisos públicos de acceso y clonado.
* La aplicación deberá ser ejecutada en entorno local sin necesidad de dockerización ni de otro software más que java 1.8
* El uso de frameworks accesorios queda a la elección del candidato
* La persistencia de información se realizará en una estructura de datos en memoria lo más optimizada posible.
* se aconseja estructurar la información y utilizar funcionalidades que permitan asegurar que las llamadas son lo menos bloqueantes posible y así soportar el mayor número de requests concurrentes.


## Resultados en Leguaje JAVA:

Anexo código fuente en repositorio github: https://github.com/afirdm/bitcoin

Anexo documentación de la API con Swagger http://localhost:8082/swagger-ui.html

Pre requisito: utilizar un cliente ej Postman o [apitester](https://apitester.com "cliente para probar la api")

##### Nivel 1:

##### Build

Run `mvn clean package` to build the project. The build artifacts will be stored in the `target/` directory

###### Running tests

Run `mvn clean test` 

###### Running application on localhost

Run `mvn spring-boot:run -Dspring-boot.run.profiles=local` to execute this app locally.

###### Debugging application on localhost with port 5005

Run `mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005" -Dspring-boot.run.profiles=local`

##### HealthCheck

	http://localhost:8182/health
	
##### Database Admin

	CONSOLE: http://localhost:8082/h2-console
	USER: SA
	PASSWORD: 


###### 1) Ejemplo (Obtener el precio del bitcoin en cierto timestamp)

Request:

	GET → https://cex.io/api/last_price/BTC/USD

Response: 

	{
        "lprice": "31566.1",
        "curr1": "BTC",
        "curr2": "USD"
    }

Cualquier duda o consulta por favor enviar mail a alejandro.firdman@hotmail.com muchas gracias!
