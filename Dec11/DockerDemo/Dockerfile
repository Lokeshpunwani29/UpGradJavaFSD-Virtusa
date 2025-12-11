FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy source code
COPY src ./src

# Compile Java program
RUN javac src/main/java/com/java/HelloWorld.java -d out

# Set default command
CMD ["java", "-cp", "out", "com.java.HelloWorld"]
