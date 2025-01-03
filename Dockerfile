# Use the Maven image as the base
FROM maven:3.9.9-eclipse-temurin-23 AS compiler

# Define application directory
ARG APP_DIR=/app 
WORKDIR ${APP_DIR}

# Copy project files into the image
COPY pom.xml .
COPY src src

# Build the application; might have to use mvn instead of /mvnw
RUN mvn clean package -Dmaven.test.skip=true 


#Stage 2
FROM maven:3.9.9-eclipse-temurin-23

ARG DEPLOY_DIR=/code_folder

WORKDIR ${DEPLOY_DIR}

COPY --from=compiler /app/target/miniProj-0.0.1-SNAPSHOT.jar ssfMiniProj.jar

# Set the server port
ENV SERVER_PORT=4000

# Expose the port
EXPOSE ${SERVER_PORT}

# Run the application
ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar ssfMiniProj.jar