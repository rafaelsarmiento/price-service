# price-service
Basic REST service to retrieve the products prices from a H2 database.

To check the functionalities it offers in detail, please take a look at the **swagger.yaml** file.

#### How to run it
The only requirement to run the program is to have installed in your machine a **Java 21** version.

Being in the directory where the source code is, execute the below  _maven_  command:

```console
	mvnw clean spring-boot:run
```

The program is going to run a  _Tomcat_  server in port 8080 by default. If that port is currently busy in your system, please execute the below  _maven_  command instead choosing your port: 

```console
	mvnw clean spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

#### How to test it
1-. Manual testing

Once the program is running in your machine, please open a  _browser_  and access the below URL (depending on the port selected in the above chapter) where we can play with the 3 input parameters (brandId, productId and date)

```
	http://localhost:8080/price-service/api/v1/price/1/35455?date=2020-06-14T10:00:00
```

2-. Automatic testing

These are the test included in the source code that can be executed with the below  _maven_  command:

```console
	mvnw clean test
```

#### Design decisions
* New column in the  _PRICES_  database table,  _ID_ , used like primary key. After reading carefully the specification is not clear for me if the  _PRICE_LIST_  can be used as primary key.
* Two decimal digits have been considered enough for the prices (regarding the examples in the specification).
* The database  _start_  and  _end dates_  from the specification have been considered to be in UTC.
* The  _date_  request parameter is considered to be in UTC.
* The  _date_  request parameter has been considered optional. If it is not sent, the current UTC date will be used for the search.


#### Potential improvements
* When the API is called with wrong input data, not numeric brand or product ID or a wrong data formatting, the API response could be considered like not human user readable.