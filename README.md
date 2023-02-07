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
./target/springboot-3-overview
```
> :warning: **Liquibase is enabled by default**: If you want to see the real start time in native mode, you might want to disable it
> ```./target/springboot-3-overview --spring.liquibase.enabled=false```

# Execute containers

> IMPORTANT: The configuration used requires a docker registry where the images must be pushed to. This has to 
> be configured in the docker-compose.yaml

## Create Docker image for JVM mode
```
docker build -f Dockerfile -t {YOUR_DOCKER_REGISTRY}/image-jvm .
docker push {YOUR_DOCKER_REGISTRY}/image-jvm
```

## Create Docker image for Native mode
```
docker build --platform=linux/arm64 -f ./Dockerfile.native -t {YOUR_DOCKER_REGISTRY}/image-native .
docker push {YOUR_DOCKER_REGISTRY}/image-native
```

## Run Docker compose
```
docker compose up
```