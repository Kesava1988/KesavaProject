#Restaurant Reservation project

REST Api for RRS is provided in RRSProject -- Project-API folder.

This folder contains complete eclipse project along with Swagger documentation and SQL dump file with sample schema.

In order to test this project. Following steps have to be followed:

1. Import the SQL data using the SQLDump.sql file provided with the project. This can be done using MYSql workbench UI.
2. From MYSql workbench UI, go to SERVER --> Data import menu.
3. select "import from self-contained file" option and browse to the sql dump file (SQLDump.sql).
4. Press start import button. This will import the sql data along with schema and table structure.
5. If you want to create your own data, truncate all tables. You can do this by right click on table and truncate (from workbench).
6. import the project in eclise. eclipse --> file --> import --> general --> existing projects into workspace (browse to the project).
7. you need to have tomcat 8.0 installed or configured to eclipse.
8. Start the project in eclipse.


Urls for the functionality :

RESTAURANT :

    http://localhost:8080/RRSRestApi/api/restaurant/newrestaurant  <-- creates a new restaurant (consumes restaurant JSON object) POST
    
    http://localhost:8080/RRSRestApi/api/restaurant  <-- Get all restaurants in database   GET
    
    http://localhost:8080/RRSRestApi/api/restaurant/editrestaurant <-- Edit restaurant details (consumes restaurant JSON object) PUT
    
    http://localhost:8080/RRSRestApi/api/restaurant/{id}  <-- get restaurant from database based on id GET
    
    http://localhost:8080/RRSRestApi/api/restaurant/{id}  <-- delete restaurant from database based on id DELETE
    
    Restaurant JSON (to create)
    {
      "name": "string",       <-- Name of restaurant
      
      "open_time": "string",  <-- Opening time of restaurant in YYYY-MM-DD-HH-MIN format (needs to be hiphenated)
      
      "close_time": "string", <-- closing time of restaurant in YYYY-MM-DD-HH-MIN format (needs to be hiphenated)
      
      "address1": "string",   <-- Address of restaurant
      
      "address2": "string",   <-- Address of restaurant
      
      "city": "string",       <-- City of restaurant
      
      "state": "string",      <-- State of restaurant
      
      "zip": 0,               <-- Address of restaurant (integer only)
      
      "email": "string",      <-- Email of restaurant
      
      "phone": "string",      <-- Phone of restaurant (Length of string should not exceed 10. no further validations, 
      expecting to have then in client side)
      
      "table_1": 0,           <-- Number of tables of size 1 (int)
      
      "table_2": 0,           <-- Number of tables of size 2 (int)
      
      "table_4": 0,           <-- Number of tables of size 4 (int)
      
      "table_6": 0,           <-- Number of tables of size 6 (int)
      
      "table_8": 0,           <-- Number of tables of size 8 (int)
      
      "id": 0,                <-- Restaurant ID (Auto assigned in database) (hard coded to 1 for simplicity)
      
      "auto_assign": 0        <-- Auto assign toggle
    }

    Note : Only one restaurant is allowd at this moment. Also editing the number of tables doesnt have any effect and not implemented.
    
RESERVATION :
    
    http://localhost:8080/RRSRestApi/api/reservation <-- Get all reservations from database GET
    
    http://localhost:8080/RRSRestApi/api/reservation/{conf_no} <-- Get all reservations from database based on conf no GET
    
    http://localhost:8080/RRSRestApi/api/reservation/confirm  <-- Doesnt do any thing PUT
    
    http://localhost:8080/RRSRestApi/api/reservation/editreservation <-- Edit reservation date, time, party size etc (consumes JSON object) PUT
    
    http://localhost:8080/RRSRestApi/api/reservation/reserve <-- Make a reservation. (consumes JSON object) POST
    
    http://localhost:8080/RRSRestApi/api/reservation/{conf_no} <-- delete reservation from database based on confirmation number DELETE
    
    Reservation JSON object :
    
    {
      "customerEmail": "string", <-- Customer email
      
      "first_name": "string",    <-- Customer first name
      
      "last_name": "string",     <-- Customer last name
      
      "phone": "string",         <-- Customer phone (Length of string should not exceed 10. no further validations,
                                     expecting to have then in client side)
      
      "datetime": "string",      <-- Reservation date and time in YYYY-MM-DD-HH-MIN format (needs to be hiphenated) 
       (validations are done such that it cannot be past. should be within restaurant open close timings only)
      
      "partySize": 0,            <-- Party size(should be positive > 0) 
      
      "rest_id": 0,              <-- restaurant id(hardcoded to 1)
      
      "confNo": 0,               <-- Auto generated in database
      
      "status": 0,               <-- status of reservation. 1- success, 0- waiting
      
      "tableID": 0               <-- based on auto assign, tableID can be assigned when reservation is created or 
      can be assigned later. value = -1 if auto assign is off or in waiting status
    }
    
CUSTOMER :

    http://localhost:8080/RRSRestApi/api/customer  <-- Get all customers in database GET
    
    http://localhost:8080/RRSRestApi/api/customer/{email}  <-- Get customer from database based on email GET
    
    http://localhost:8080/RRSRestApi/api/customer/{email}  <-- Delete customer from database based on email DELETE
    
    http://localhost:8080/RRSRestApi/api/customer/editcustomer <- Edit customer in database (consumes JSON object) PUT
    
    http://localhost:8080/RRSRestApi/api/customer/newcustomer  <-- create a new customer (consumes JSON object) POST
    
    http://localhost:8080/RRSRestApi/api/customer/newcustomer  <-- create a new customer (consumes JSON object) POST
    
    Customer JSON Object :
    {
      "email": "string",
      "firstName": "string",
      "lastName": "string",
      "phone": "string"
    }

OWNER

    http://localhost:8080/RRSRestApi/api/login/user{email}&password{pass}  <-- Returns confirmation if login is 
    successful or not based on email and password GET
    
    http://localhost:8080/RRSRestApi/api/login/{email}   <-- Delete owner from database based on email DELETE
    
    http://localhost:8080/RRSRestApi/api/login/addowner  <-- Add a new owner to database (consumes JSON object) POST
    
    http://localhost:8080/RRSRestApi/api/login/editowner <-- Edit password of owner (consumes JSON object) PUT
    
    owner JSON object
    
    {
      "email": "string",
      "password": "string"
    }

VALIDATIONS :
    Following validations are implemented :

    Datetime <-- Restaurant open and close time are to be on same day.

    Datetime <-- Restaurant open time should be before close time.

    Datetime <-- Reservation should be in restaurant operating time 
                (opentime <= reservation time < closetime).
                
    Datetime <-- Should be in formwat YYYY-MM-DD-HH-MIN

    No of tables  <-- positive integer (can be zero)

    table sizes <-- fixed (1,2,4,6,8)

    party size <-- positive integer ( > 0)

    customer <-- when a new reservation is created, it will check if the customer is in database or not 
            based on his email. If customer is not present, he will be added to databse.