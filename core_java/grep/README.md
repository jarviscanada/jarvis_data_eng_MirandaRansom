# Introduction
Java grep is an implementation of grep in the Java language. The purpose of this project is to develop core java knowledge.  JavaGrepImp uses java.util.regex to match a given pattern, java.io for file io and slf4j logger.  JavaGrepLambdaImp extends JavaGrepImp to use streams for listing files and reading lines rather than the for loops originally implemented. The project is managed with Maven and has been deployed with docker. 

# Quick Start
usage
```
JavaGrep regex rootPath outFile
```


# Implementation
## Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issues
Data processing is limited to heap capacity. To work around this issue in future improvements, matched lines can be directly written to file rather than being stored to a list to be bulk written to a file at the end.

# Test
A known regex contained in shakespeare.txt was used to test the grep application on a file. William and Shakespeare was used in this case. To test finding files recursively, extra files and directories were added to /data to ensure proper functioning with multiple files and directories including nested cases.

# Deployment
This application has been dockerized to allow simple distribution and running.  Volumes need to be created for /data and /logs for input and output respectively.  The dockerfile is as follows.
```
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
```

# Improvements
1. Write directly to file to allow processing of large data 
2. Capture multiple regex patterns
3. Allow option to skip specified files/directories
