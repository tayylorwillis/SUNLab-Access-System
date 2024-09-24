# SUN Lab System
Simple system for SUN Lab access records.

# Description
System with access to logs of students accessing the SUN Lab, either entry or exit, by utilizing their ID cards. Admins have access to time stamps, dates, and the user's ID number. GUI and database are utilized to store and browse data.

# Getting Started
## Dependencies
Java, Java IDE, Tomcat, XAMPP, search engine required.
## Installing
- Download latest version of Java and a Java IDE (I used eclipse).
- Download XAMPP in order to utilize the MySQL database (https://www.apachefriends.org/download.html).
- Download Apache Tomcat (I am using v. 10.0.27). Can be done from within Eclipse (https://www.youtube.com/watch?v=0CsWW1Ni8jA).
- Download the SQL file to use for the database.

# Executing Program
1. Open XAMPP (manager-osx) and start MySQL Database and Apache Web Server. *Note that if MySQL Database will not start, open your terminal and type sudo killall mysqld, then try again.*
2. Confirm everything started properly by visiting http://localhost/phpmyadmin/ on your search engine. Make sure to implement the SQL file provided.
3. In your IDE, start the AdminLoginServlet (mine runs on Tomcat 10.0.27). *Note that if you are not redirected to your search engine once Tomcat starts successfully, go to http://localhost:8080/CMPSC487WProject1/login.*
4. If successful, the admin login should appear. To login, username is "user" and password is "pass". *Since the data in this project is hypothetical and there is no real secure information, the username and password are hard coded and encryption did not seem necessary.*
5. After logging in, the access records should become available on the GUI, grabbing data from the database.

# Known Issues and Their Solutions
- If MySQL Database will not start, type sudo killall mysqld into the terminal, and try again. Often, it will randomly no longer start. This is the quickest solution if this issue occurs.
- Tomcat may fail to start. A possible reason could have to do with how it was configured. For example, right click the project and go to Properties > Java Build Path > Libraries, and under Modulepath you should see "Server Runtime [apache-tomcat-10.0.27]" or something similar. If it is not there, add a new Library and use Server Runtime. This is one of many solutions, but a step that can be easily missed.
