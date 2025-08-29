# Bajaj Finserv Health - Java Qualifier 1

## Project Overview
This is a Spring Boot application that implements the webhook assignment workflow for Bajaj Finserv Health's Java qualifier round.

## Features
- ✅ Automatic webhook generation on startup
- ✅ Dynamic question assignment based on registration number
- ✅ SQL problem solving (placeholder implementations)
- ✅ Solution submission via webhook with JWT authentication
- ✅ Comprehensive logging and error handling

## Project Structure
```
src/main/java/com/example/webhook_assignment/
├── model/
│   ├── WebhookRequest.java      # Request model for webhook generation
│   ├── WebhookResponse.java     # Response model from webhook generation
│   └── SolutionRequest.java     # Request model for solution submission
├── service/
│   └── WebhookService.java      # Main service implementing the workflow
├── config/
│   └── AppConfig.java           # Configuration for RestTemplate
└── WebhookAssignmentApplication.java  # Main Spring Boot application
```

## How It Works

### 1. Webhook Generation
- On startup, sends POST request to `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- Uses registration number: `REG12347`
- Receives webhook URL and access token

### 2. Question Assignment
- **Odd last two digits** → Question 1
- **Even last two digits** → Question 2
- REG12347 ends with "47" (odd) → Assigned Question 1

### 3. Solution Submission
- Sends POST request to `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`
- Uses JWT token in Authorization header
- Submits final SQL query

## Question Links
- **Question 1**: https://drive.google.com/file/d/1IeSI6l6KoSQAFfRihIT9tEDICtoz−G/view?usp=sharing
- **Question 2**: https://drive.google.com/file/d/143MR5cLFrlNEuHzzWJ5RHnEWuijuM9X/view?usp=sharing

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

## Running the Application

### Option 1: Using Maven Wrapper
```bash
./mvnw spring-boot:run
```

### Option 2: Using Maven
```bash
mvn spring-boot:run
```

### Option 3: Build and Run JAR
```bash
mvn clean package
java -jar target/webhook-assignment-0.0.1-SNAPSHOT.jar
```

## Current Status
- ✅ **Webhook Generation**: Working correctly
- ✅ **Question Assignment**: Working correctly (Question 1 assigned)
- ⚠️ **Solution Submission**: Placeholder SQL queries implemented

## Next Steps Required

### 1. Download Actual Questions
- Download Question 1 from the Google Drive link
- Read and understand the SQL problem requirements

### 2. Implement Real Solutions
- Replace placeholder SQL queries in `WebhookService.java`
- Update `solveQuestion1()` method with actual solution
- Update `solveQuestion2()` method with actual solution

### 3. Test Complete Workflow
- Run the application to ensure end-to-end success
- Verify solution submission returns success status

## Configuration
The application uses the following configuration:
- **Port**: 8080 (default)
- **Logging**: DEBUG level for webhook package
- **API Endpoints**: As specified in the assignment

## Troubleshooting

### Common Issues
1. **401 Unauthorized**: JWT token may have expired, regenerate webhook
2. **Connection Issues**: Check internet connectivity and API availability
3. **Port Conflicts**: Change port in `application.properties` if 8080 is busy

### Debug Mode
Enable detailed logging by setting in `application.properties`:
```properties
logging.level.com.example.webhook_assignment=DEBUG
```

## Submission Requirements
- ✅ GitHub repository with complete code
- ✅ Final JAR output
- ✅ RAW downloadable GitHub link to JAR
- ✅ Public JAR file link (downloadable)

## License
This project is created for Bajaj Finserv Health's Java qualifier round.

## Support
For any issues or questions, please refer to the assignment documentation or contact the hiring team.
