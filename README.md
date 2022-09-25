
# This repository contains refactored Bgiackend of the addonis-telerik-graduate-project.
(Still no front end is available)

In order to start the application Docker Desktop is needed to instantiate the containers. Also, bootRun the application.

![img_9.png](readme_images/img_9.png)


# Brief description:  
 - Hibernate is replaced with Spring Data JPA
 - MariaDB is replaced with PostgreSQL. The server is instantiated in Docker container (docker-compose.yml)
 - The tables in the database and the sample data in the database are created and imported using Liquibase (changeLog.xml)
 - Security layer was added using Keycloak which is instantiated in Docker container (docker-compose.yml). The embedded H2 database of the JBoss/Keycloak image was replaced with PostgreSQL database
 - Keycloak's realm setting are imported at start up using keycloak-realm-settings.json file
 - Both databases (application's and Keycloak's) are created via bash script (bash_script folder) on application start up

PostgreSQL and Keycloak containers in Docker Desktop  
</br>

   ![img.png](readme_images/img.png)
   </br></br>

Keycloak admin console  

 - http://localhost:8081/auth/
 - Admin dummy username: admin
 - Admin dummy password: admin

# Public endpoint preview 

- Status code 200
  </br></br>

   ![img_1.png](readme_images/img_1.png)

# Restricted endpoint preview 

- Returns status code 200 in Postman, because it actually returns the Log in page, but the resource is NOT available without authentication
  </br></br>    
   ![img_4.png](readme_images/img_4.png)
    
   ![img_3.png](readme_images/img_3.png)
  </br></br>

- Must authenticate to obtain the resource (user: ivan, password: ivan)
  </br></br>

   ![img_6.png](readme_images/img_6.png)
  </br></br>

- Another option to obtain the resource from the application is to use JWT (OpenId Connect)
</br></br>

  (Obtain access token)
   ![img_7.png](readme_images/img_7.png)
  </br></br>

  (Use obtained JWT for authentication)
</br></br>
![img_8.png](readme_images/img_8.png) 
