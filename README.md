# Application Setup Guide

## Overview
This application utilizes a database for data storage and management. To ensure proper functionality across all environments, it is important to configure the `application.properties` file correctly.

## Configuration Instructions

### 1. Locate the `application.properties` file
The `application.properties` file can be found in the `src/main/resources` directory of your application.

### 2. Database Configuration
Open the `application.properties` file and set the necessary database connection parameters. These include the database URL, username, password, and any additional properties required by your environment. Below is an example of what you might need to configure:

```application.properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Additional Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Environment-Specific Configurations
Ensure that any environment-specific values (such as database credentials and URLs) are correctly set based on the environment where the application is being deployed (e.g., development, staging, or production).

### 4. Save and Restart
Once the `application.properties` file is configured, save the changes and restart your application to apply the new settings.

## Running the Application

1. **Start All Applications Simultaneously**  
   Make sure that all the related applications are running on their respective ports. For example, you might be running:
   - Application 1: Port 8081 (for User functionalities)
   - Application 2: Port 8083 (for Admin functionalities)
   
2. **Create an Admin Account**  
   To explore the full functionality, create an admin account by navigating to:  
   [http://localhost:8083/Admin/register](http://localhost:8083/Admin/register)  
   Follow the registration process to set up the admin account.

3. **Create a User Account**  
   Similarly, create a user account by visiting:  
   [http://localhost:8081/User/register](http://localhost:8081/User/register)  
   Follow the registration instructions to create a user account and access the user functionalities.

Once both accounts are created, you will be able to fully explore the application’s features for both users and admins.
Thank you.
