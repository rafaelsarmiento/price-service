# price-service
Basic REST service to retrieve the products prices from a H2 database

#### Design decisions
* New column in the  _PRICES_  database table,  _ID_ , used like primary key. After reading carefully the specification is not clear for me if the  _PRICE_LIST_  can be used as primary key.
* Two decimal digits have been considered enough for the prices (regarding the examples in the specification).
* The database  _start_  and  _end dates_  from the specification have been considered to be in UTC.
* The  _date_  request parameter is considered to be in UTC.
* The  _date_  request parameter has been considered optional. If it is not sent, the current UTC date will be used for the search.


#### Potential improvements
* When the API is called with wrong input data, not numeric brand or product ID or a wrong data formatting, the API response could be considered like not human user readable.