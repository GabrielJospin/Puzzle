# Puzzle
A big project develop to revolutionize
the distributed system, this is Google Cloud. Puzzle
it's a simple Programming Exercise to Distributed System class
in the São Paulo University. 

Despite the modesty of the program, was really fun to
developer it. I hope you like your experience to use it.

    - That's not just a restaurant, that's a gastronomic experience
    - Sir, This is a Wendy's

## Prerequisites
 - Java 11.0.15 or similar
 - Gradle 7.2 or similar
 - ~~Norton Antivirus~~

## Before use

Before you start use this system you need run the 
Gradle dependencies, because need add the libraries
to the gradle package. For this you run the command:
```bash 
    ./gradlew server:dependencies 
```


## To Run Server
Use the command: 
```bash 
    ./gradlew server:run --args "<server Name>"
```
The servers use the config from ```server/src/main/resources/config.json```.
You can change the name and the port in json. The default config id:

| Server         | port  |
|----------------|-------|
| server1        | 10001 |
| server2        | 10002 |
| server3        | 10003 |
| server4        | 10004 |
| server5        | 10005 |
| server6        | 10006 |
| server7        | 10007 |
| server8        | 10008 |
| server9        | 10009 |
| serverA        | 10010 |
| strangerServer | 10011 |


## To Run Client
```bash
    ./gradlew client:run --console=plain
``` 
This will show you a Command line
To access all available commands
you can use the command ```help```.
For understand better a command you can
use the structure ```<command> --help```


## Future Additions
 - A Shell Script to easily up yhe servers and run the Puzzle
