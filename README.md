# price-service
Basic REST service to retrieve the products prices from a H2 database

#### Design decisions
* New column in the PRICES database table, ID, used like primary key. After reading carefully the specification is not clear for me if the PRICE_LIST can be used as primary key.
* I have assumed that 2 decimal digits is enough for the prices regarding the examples in the specification.
