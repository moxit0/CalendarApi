# SouthGate Calendar API

Demo project given on SouthGate technical interview based on Spring Boot.

## Requirements

The goal of the application is to have a calendar to manage users meetings.

A meeting must contain - start/end hours, name, meeting room. 

Two meetings can't be placed in the same room at the same time or having one user taking part in more than one meetings at a time.

With the provided JSON file populate a database of your choice and create the needed REST API's for booking calendar events for multiple users.

The API should allow for: creating new, deleting and editing events as well as adding multiple participants.

The API should allow only authenticated users and should control ownership permissions for these users (one cannot list or edit other users objects).

The API should be built with scalability in mind and support handling tens of thousands of concurrent users.

JSON data: https://slack-files.com/TLYRT5VGQ-F014G8476RY-38c3178694
### DB

Used DataBase is H2. Since H2 is used as runtime DB all data will be wiped out on application shutdown.

### Spring
    
To run the project:
    
	mvn spring-boot:run
	
To build the project:
	
	mvn clean package
	
To run jar file:

    java -jar target/calendar-0.0.1-SNAPSHOT.jar

### Security
There are two provided users that can be used for login: 

        userName: user / pass: user
        userName: admin / pass: admin

### Rest endpoints

Add initial data

    POST: http://localhost:8080/calendar

Add new meeting

    POST: http://localhost:8080/meetings

Edit given meeting

    PUT: http://localhost:8080/meetings
    
Get all meetings

    GET: http://localhost:8080/meetings
    
Get meeting by id

    GET: http://localhost:8080/meetings/{id}
    
Delete given meeting

    DELETE: http://localhost:8080/meetings/{id}
