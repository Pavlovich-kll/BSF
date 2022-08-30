# **BSF assigment Application using Java 8, Spring Boot, PostgreSQL, Docker Compose**

RESTful API to simulate simple banking operations.

Requirements
CRUD operations for users and accounts.
It’s possible to create users, accounts, replenishment transactions, 
balance reduction and transfer from account to account.

# **Getting Started**
Checkout the project from GitHub
`git clone https://github.com/Pavlovich-kll/BSF.git`

Enable Lombok support on your IDE

1) `mvn clean package -DskipTests`

2) `docker run --name multiverse -p 5477:5432 -e POSTGRES_DB=postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres`

3) `mvn spring-boot:run`

4) `execute sql in: src/test/resources/init.sql`

5) `Browse to <project-root>/src/test/resources/generated-requests.http to find sample requests to add users,
   accounts and to create some transactions.`

Default port for the api: 8080


#Swagger
Please find the Rest API documentation in the below url

`http://localhost:8080/api/swagger-ui/index.html`


#Author
**Kirill Pavlovich**