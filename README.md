# **BSF assigment Application using Java 8, Spring Boot, PostgreSQL, Docker Compose**

RESTful API to simulate simple banking operations.

Requirements
CRUD operations for users and accounts.
It’s possible to create users, accounts, replenishment transactions, 
balance reduction and transfer from account to account. 
The project is deployed to docker compose (server on one container and database on another).

# **Getting Started**
Checkout the project from GitHub
`git clone https://github.com/Pavlovich-kll/BankApp`

Enable Lombok support on your IDE

in src/main/docker
`- Run docker-compose up`

Default port for the api: 8181


#Swagger
Please find the Rest API documentation in the below url

`http://localhost:8181/api/swagger-ui.html`



Browse to <project-root>/src/test/resources to find sample requests to add users, 
accounts and to create some transactions.

#Author
**Kirill Pavlovich**