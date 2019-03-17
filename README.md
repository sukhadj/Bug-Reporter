# Bug Reporter 
### A stand-alone java project


## Description


### User roles
There are 2 roles in the project
	- Users 
	- Developers

#### User
Users can log in and:
	- View details of the projects associated with them
	- View or add bugs associated with the project
#### Bug
A bug has the following properties:
	- a title 
	- description
	- project_id 
	- user_id (of the user who added the bug) 
	- status

There are 3 possible statuses
	- Pending(p) 
 	- Working(w)
	- Resolved(r)

#### Developers
Developers, in addition to users functionalities, can change the status of Bugs.


### APIs
Apis in the project
	1. Login
	2. Registration
	3. Security (Used by Login and registration APIs)
	4. Projects_Card View
	5. Project View
	6. Add Bugs
	7. BugList View
	8. Bug View
	9. Bug Pie Chart
	10. Change Bug Status (Restricted only to the developers)
	
#### Login:
	Used to validate and user, get his user_id. This is useful in displaying the projects associated with him

#### Registration:
	Used to add new users to the database. New users get 2 default projects associated with them
	This API also performs input validation. Some of them are:
		a. Duplicate username is not allowed
		b. The contact number should be of 5 digits
		c. The password should have a min length of 3

#### Security:
	This module essentially uses a security utility class, java.security.MessageDigest. It uses SHA256 algorithm to digest the password strings. In this way, the passwords of users are never visible, even to the database admin.

#### Project_Cards View:
	This module aims at displaying a preview of the projects: with their title and first 100 characters of their description. For more details, a user may click on "View Project" Button, to see the complete details of the Project. 
	We have created a Custom Panel called Card, that extends JPanel. The card panel uses The gridbagLayout to properly position all the components in it.
	The Project Cards View Frame uses the gridlayout to properly position the cards on the window.

#### Project View:
	This API gives a detailed description of the project. It also provides the options to view/add the bugs. Bug stats can also be viewed.

#### BugList View:
	Displays a list of all the bugs associated with the selected project. The bugs are clickable labels - on clicking the bugs a detailed Bug View window opens.

#### Bug View:
	This window describes the bug in proper details.

#### Add bug:
	Provides the option to users and developers to add a new bug in the project.
#### Bug Pie Chart
	Uses the xchart api, to create a pie chart, that gives a rough estimate of how many bugs have been resolved, etc.
#### Change Bug status
	A developer has the right to change the status of the bug. The changes are reflected immediately.

### Motivation behind the project
	We were fascinated by the idea of bug tracking tools such as JIRA and BugZilla. While these were incredibly powerful, they were also difficult to understand to the end users who had no prior training in these tools. So, we thought, why not try creating a BugTracking tool that had a simpler UI, easily understood by any product user.

### Reasons to choose Java Swing for this project
	1. Java is a core language. And we have always wanted to explore the GUI part of java.
	2. A standalone application means almost no 3rd party services, allowing us to have a greater control over our data
	3. We believed that our project: Bug Tracker would fit best as a desktop application, rather than an android application

### Why MySQL? Why not MongoDB?
	The use case in our project was matching with a relational database [Justified below] . As we had already learned MySQL in DBMS, we decided to implement that.
	 Justification:
		a. The input formats of all the entities are fairly fixed, therefore there is no need of a schemaless database.
		b. By using the integrity constraints (primary key and foreign key) We were able to avoid a lot of discrepancies that are difficult to resolve otherwise
		c. Java is very well compatible with MySQL. It has a standard, thin JDBC driver which works well with MySQL

### Why haven't you used socket programming in your code?

	Our goal of the project was to use the concepts of Java Swing and MySQL to simulate a bug tracking tool. We did not see how a client-server architecture would help in enhancing our project right now, and hence we did not go for it. But in the future, if we plan to deploy the project on a platform, it would be required.

Have you used multithreading in your code?

	Yes, we have. While using the xchart jar for generating pie charts, we discovered that it requires a separate thread to instantiate itself. So we created an object of thread class, and in its run() method, we put the code for pie chart creation.

### Future scope of this project
	We can further enhance this project by adding more features, such as features to mark the added bugs as duplicate/invalid.
	We can also use the concepts of socket programming, to convert this into a client-server application, that can be run on multiple machines.

 
## Contributed by
Mehul Choksi @mehul-choksi
Sukhad Joshi @sukhadj

