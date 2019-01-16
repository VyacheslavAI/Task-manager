#Task Manager

##### Task Manager is a software project for creating and viewing information about projects and their tasks
If you think you have found a bug, please send an error message to the email address: vyacheslavivanov93@gmail.com

***
###Project Structure
**Main class:** TaskManager - class with psvm
- **entry package**
  + Project - project entity
  + Task - task entity
- **dao package**
  + ProjectRepository - repository for projects
  + TaskRepository - repository for tasks
- **controller package**
  + Controller - business logic class
- **util package**
  + ConsoleHelper - class for working with the console
  + SerializationUtil - class to serialize objects
  + Domain - class for storing application entities for subsequent transfer, recording, or downloading
- **error package**
  + ObjectIsNotFoundException - Is thrown if the object is not found
- **command package**
    + "This package contains executable commands that implement the command interface."
  
***
###Base Functional

You can create and manage information about existing projects and their tasks.

***

###Building from Source
You need to build Task Manager: 
* [Java SE 7](https://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html) to build Task Manager.
* [Maven](https://maven.apache.org/download.cgi#) 3.5.4 or higher

```Build command: mvn clean install```

***
###How to start application by console
```java -start```








