package com.java.innerclient.model;

public class Agent {
    private int agentId;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private double premiumPaid;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPremiumPaid() {
        return premiumPaid;
    }

    public void setPremiumPaid(double premiumPaid) {
        this.premiumPaid = premiumPaid;
    }
}
