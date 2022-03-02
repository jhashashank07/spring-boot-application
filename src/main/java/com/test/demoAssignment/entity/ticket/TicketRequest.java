package com.test.demoAssignment.entity.ticket;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TicketRequest {

    @NotEmpty(message = "Description cannot be empty")
    private String ticketDescription;

    @Enumerated(EnumType.STRING)
    private Type ticketType;

    public TicketRequest(String ticketDescription, Type ticketType) {
        this.ticketDescription = ticketDescription;
        this.ticketType = ticketType;
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
