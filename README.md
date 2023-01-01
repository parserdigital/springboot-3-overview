# Build

# JVM Mode
```
./mvnw clean package
```

# Native Mode
Ensure you have set up a valid version of GraalVM on your machine and then run:
```
./mvnw -Pnative clean package
```

# Start the application locally

## JVM Mode
```
java -jar target/springboot-3-overview-0.0.1-SNAPSHOT.jar
```

## Native Mode
```
./target/springboot-3-overview
```
If no more liquibase processing is needed, you can skip it by passing this argument
```
./target/springboot-3-overview --spring.liquibase.enabled=false
```


# Execute containers
docker-compose up --build