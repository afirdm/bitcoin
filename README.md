[![Build Status](https://travis-ci.com/afirdm/bitcoin.svg?branch=main)](https://travis-ci.com/github/afirdm/bitcoin)
[![codecov](https://codecov.io/gh/afirdm/bitcoin/branch/main/graph/badge.svg)](https://codecov.io/gh/afirdm/bitcoin)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fafirdm%2Fbitcoin.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fafirdm%2Fbitcoin?ref=badge_shield)

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
	
	TABLE: BITCOIN

###### A) Ejemplo (Consultar microservicio REST y almacenar los datos)

Request:

	GET → https://cex.io/api/last_price/BTC/USD

Response: 

	{
        "lprice": "31566.1",
        "curr1": "BTC",
        "curr2": "USD"
    }

###### 1) Ejemplo (Obtener el precio del bitcoin en cierto timestamp)

Request:

	GET → http://localhost:8082/wenance/bitcoin
	RequestParam fecha → 2021-01-06 14:25:15.334

Response: 

	{
        "id": "200",
        "lprice": "31566.1",
        "curr1": "BTC",
        "curr2": "USD",
        "createDate":"2021-01-06 14:25:15.334"
    }
    
###### 2) Ejemplo (Conocer el promedio de valor entre dos timestamps y el valor máximo almacenado para toda la serie temporal disponible)

Request:

	GET → http://localhost:8082/wenance/bitcoin/metrics
    RequestParam fechaDesde → 2021-01-06 23:51:41.449
    RequestParam fechaHasta → 2021-01-06 23:52:56.244

Response: 

	{
        "average": 36843.58666666667,
        "maxPrice": 36847.4
    }
    

Cualquier duda o consulta por favor enviar mail a alejandro.firdman@hotmail.com muchas gracias!
