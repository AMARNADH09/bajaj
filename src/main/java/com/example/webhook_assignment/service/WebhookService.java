package com.example.webhook_assignment.service;

import com.example.webhook_assignment.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void executeWorkflow() {
        try {
            System.out.println("=== BAJAJ FINSERV HEALTH - JAVA QUALIFIER 1 ===");
            System.out.println("Starting webhook workflow...");
            System.out.println("===============================================");
            
            // Step 1: Generate webhook
            System.out.println("\nStep 1: Generating webhook...");
            WebhookResponse response = generateWebhook();
            System.out.println("✅ Webhook generated successfully");
            System.out.println("📋 Webhook URL: " + response.getWebhook());
            System.out.println("🔑 Access Token: " + response.getAccessToken());
            
            // Step 2: Determine and solve question
            System.out.println("\nStep 2: Determining and solving question...");
            String regNo = "22BPS1147"; // Use the regNo from request
            String finalQuery = solveAssignedQuestion(regNo);
            System.out.println("✅ Question solved");
            System.out.println("💡 Final SQL Query: " + finalQuery);
            
            // Step 3: Submit solution
            System.out.println("\nStep 3: Submitting solution...");
            submitSolution(response.getWebhook(), response.getAccessToken(), finalQuery);
            System.out.println("✅ Solution submitted successfully");
            
            System.out.println("\n🎉 Workflow completed successfully!");
            System.out.println("===============================================");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error in workflow: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private WebhookResponse generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        
        WebhookRequest request = new WebhookRequest(
            "SRIKAKOLAPU VEERA VENKATA AMARNADH BABU", 
            "22BPS1147", 
            "srikakolapu.veera2022@vitstudent.ac.in"
        );
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);
        
        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
            url, entity, WebhookResponse.class
        );
        
        return response.getBody();
    }

    private String solveAssignedQuestion(String regNo) {
        // Extract last two digits
        String lastTwoChars = regNo.substring(regNo.length() - 2);
        int lastTwoDigits = Integer.parseInt(lastTwoChars);
        
        if (lastTwoDigits % 2 == 1) {
            // Odd - Question 1
            System.out.println("Assigned Question 1 (Odd regNo: " + lastTwoDigits + ")");
            return solveQuestion1();
        } else {
            // Even - Question 2
            System.out.println("Assigned Question 2 (Even regNo: " + lastTwoDigits + ")");
            return solveQuestion2();
        }
    }

    private String solveQuestion1() {
        try {
            String driveUrl = "https://drive.google.com/file/d/1IeSI6l6KoSQAFfRihIT9tEDICtoz−G/view?usp=sharing";
            downloadQuestion("Question 1", driveUrl);
            
            // TODO: After downloading the actual question, implement the real solution here
            // For now, using placeholder - replace with actual solution after downloading question
            System.out.println("📋 Question 1: Find employees in IT department ordered by salary");
            return "SELECT * FROM employees WHERE department = 'IT' ORDER BY salary DESC;";
        } catch (Exception e) {
            System.err.println("❌ Error solving Question 1: " + e.getMessage());
            return "SELECT * FROM employees WHERE department = 'IT' ORDER BY salary DESC;";
        }
    }

    private String solveQuestion2() {
        try {
            System.out.println("Downloading Question 2...");
            // Question 2 URL: https://drive.google.com/file/d/143MR5cLFrlNEuHzzWJ5RHnEWuijuM9X/view?usp=sharing
            // For now, using placeholder - you'll need to download and implement the actual solution
            System.out.println("Question 2: Count orders from 2023 onwards");
            return "SELECT COUNT(*) FROM orders WHERE order_date >= '2023-01-01';";
        } catch (Exception e) {
            System.err.println("Error solving Question 2: " + e.getMessage());
            return "SELECT COUNT(*) FROM orders WHERE order_date >= '2023-01-01';";
        }
    }

    /**
     * Utility method to download questions from Google Drive
     * Note: You'll need to implement the actual download logic based on the question content
     */
    private void downloadQuestion(String questionNumber, String driveUrl) {
        System.out.println("📥 Downloading " + questionNumber + " from: " + driveUrl);
        System.out.println("⚠️  Manual step required: Download the file and implement the solution");
        System.out.println("📝 After downloading, replace the placeholder SQL query with the actual solution");
    }

    private void submitSolution(String webhookUrl, String accessToken, String finalQuery) {
        SolutionRequest solution = new SolutionRequest(finalQuery);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken); // JWT token (direct, not "Bearer ")
        
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(solution, headers);
        
        // Use the fixed webhook URL as specified in the assignment
        String submissionUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
        
        ResponseEntity<String> response = restTemplate.postForEntity(
            submissionUrl, entity, String.class
        );
        
        System.out.println("Solution submitted with status: " + response.getStatusCode());
        System.out.println("Response: " + response.getBody());
    }
}
