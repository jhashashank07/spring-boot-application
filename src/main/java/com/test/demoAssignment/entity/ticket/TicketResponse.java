package com.test.demoAssignment.entity.ticket;

public class TicketResponse {

    private String ticketId;

    private String createdBy;

    private String ticketDescription;

    private Type ticketType;

    public TicketResponse(String ticketId, String createdBy, String ticketDescription, Type ticketType) {
        this.ticketId = ticketId;
        this.createdBy = createdBy;
        this.ticketDescription = ticketDescription;
        this.ticketType = ticketType;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public Type getTicketType() {
        return ticketType;
    }

    public void setTicketType(Type ticketType) {
        this.ticketType = ticketType;
    }
}
