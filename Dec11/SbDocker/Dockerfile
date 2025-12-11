# Step 1: Base image
FROM eclipse-temurin:17-jdk

# Step 2: Working directory
WORKDIR /app

# Step 3: Copy JAR
COPY target/SbDocker-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port
EXPOSE 8080

# Step 5: Run application
ENTRYPOINT ["java","-jar","app.jar"]