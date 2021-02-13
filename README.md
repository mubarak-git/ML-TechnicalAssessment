# ML-TechnicalAssessment
MLTechnicalAssessment
Technical Assessment
User story: As Product Manager, I would like to manage users’ accesses to new features via feature switches,
i.e. enabling/disabling certain feature based on a user’s email and feature names).

Solution:
Spring boot API has been developed for the above requirements
Start the application
Navigate to the target folder and run the jar file 
java -jar featureswitches-0.0.1-SNAPSHOT.jar
 
After the application starts, we can test the endpoints
Post some data
By using postman we can insert some data by calling the API end point
http://localhost:8080/api/v1/feature
 

{
    "featureName": "update user",
    "email": "mubarak",
    "enable": "true"
}



We can see the results in H2 database by access the DB from URL
http://localhost:8080/h2/login.do?jsessionid=e2e6d8e8a4de70ee36e57749f1cad6e2
 
 

GET feature by email and featureName
http://localhost:8080/api/v1/feature?email=mubarak&featureName=update user
 

If the feature not found will return not found error

