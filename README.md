SmsAuth v0.0

Creates a SMS authentication system

Files
------
security.sql
/SmsAuth
/SmsServer

Installation requriments
-------------------------
NetBeans with JDK
MYSQL Server
Tomcat Server
Android phone

Installation notes
--------------------
- create a user on MYSQL Server
    access: localhost
    username: security
    password: security

- create a table security on MYSQL server
- grant all access to security on on MYSQL server
- import security.sql into table named security on MYSQL server
- deploy the SmsAuth website; this project is made with NetBeans 8.2
- install the SmsServer project on your Android device; this project is made with Android studio 3.2