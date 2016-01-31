# Log Parsing Project

This project is designed to set up a way to interact with log files through java console.
This provides a single implementation as of now

##Implementation Use:

The program will take in a path to a directory 

Example:
````
  
  src/logParser/test - for relative
  /C:/DevEnv/LMCC/src/logParser/test - for absolute

````

The out of the box usage is to number the lines of logfiles within the directory.

so that a test on a directory with just a single log file the results look as follows:

logtest.2016-01-30.log
````
aaaaaaaa
bbbbbbbb
cccccccc
dddddddd

````
logtest.2016-01-30.log
````

1. aaaaaaaa
2. bbbbbbbb
3. cccccccc
4. dddddddd

````
##Usage

With the project downloaded package the contents with maven
````Java
mvn compile

mvn package
````
run the main as follows:
````Java
mvn exec:java -Dexec.mainClass="ui.ProjectCommandLine" -Dexec.args="src\logParser\test"

````
You can also use the test class TestCommandLine.java if you prefer


##Testing

Test classes will be run on build.
Test classes are stored in src/test/java

Thanks and Enjoy.
