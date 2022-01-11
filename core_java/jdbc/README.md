# Introduction
This JDBC project is focused around creating CustomerDAO to gain experience and knowledge of the java database connections API. 
A database HPlusSports is set up to track orders and related information. Classes of note are `Customer`, `JDBCExecutor`  and related DAO, DTO classes. 
The `CustomerDAO` extends the `DataAccessObject` to implement JDBC commands such as `findById` `update` `create` and `delete`. 
It should be noted that the `update` method turns off `autocommit` as a study of transactions, commits, and rollbacks.

# Implementation
## ER Diagram
![ER Diagram](./assets/HPlusSport_ERDiagram.png)

## Design Patterns

DAO: the DAO pattern follows that there is a data access object in which does the heavy lifting of locating (and commonly storing) data from the database providing an abstraction layer between JDBC and the rest of the code.
DTOs are used in conjunction with DAOs to aid in encapsulation. DAOs take DTOs as inputs and give DTOs as outputs.

Repository: the Repository pattern also separates the business logic from the data access logic. In contrast to the DAO pattern when working with Repository you'll be expected to join in the code, and you are able to shard your database. 
The Repository pattern fits best when the application is horizontally scalable or the database is highly normalized.
A big downfall of the pattern are atomic transactions are difficult. 


# Test
This project was tested from the JDBCExecutor class.  Specific methods were written to execute the statements and display the results to ensure the correct output. 
The executor currently is set to test the update and delete DAO methods.  A new customer is added, displayed, updated and displayed again to demonstrate the update has worked correctly. 
Afterwords, the delete method is called. To ensure the delete executed correctly, the database was manually queried with the customer id.