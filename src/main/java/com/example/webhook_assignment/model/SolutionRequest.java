package com.example.webhook_assignment.model;

public class SolutionRequest {
    private String finalQuery;

    public SolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    // Default constructor
    public SolutionRequest() {}

    // Getter and Setter
    public String getFinalQuery() { return finalQuery; }
    public void setFinalQuery(String finalQuery) { this.finalQuery = finalQuery; }
}
