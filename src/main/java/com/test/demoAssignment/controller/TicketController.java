package com.test.demoAssignment.controller;


import com.test.demoAssignment.entity.ticket.Ticket;
import com.test.demoAssignment.entity.ticket.TicketRequest;
import com.test.demoAssignment.entity.ticket.TicketResponse;
import com.test.demoAssignment.exception.UserNotFoundException;
import com.test.demoAssignment.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public TicketResponse fileTicket(@RequestHeader("userId") String userId, @Valid @RequestBody TicketRequest ticketRequest) {
        try {
            return ticketService.fileTicket(userId, ticketRequest);
        }
        catch(UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exc);
        }
    }

    @GetMapping("/tickets")
    public List<TicketResponse> getTickets(@RequestHeader("userId") String userId) {
        try {
            return ticketService.getAllTickets(userId);
        }
        catch(UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exc);
        }
    }

}
