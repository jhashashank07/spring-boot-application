package com.test.demoAssignment.service;

import com.test.demoAssignment.entity.ticket.Ticket;
import com.test.demoAssignment.entity.ticket.TicketMapper;
import com.test.demoAssignment.entity.ticket.TicketRequest;
import com.test.demoAssignment.entity.ticket.TicketResponse;
import com.test.demoAssignment.entity.user.User;
import com.test.demoAssignment.exception.UserNotFoundException;
import com.test.demoAssignment.repository.TicketRepository;
import com.test.demoAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public TicketResponse fileTicket(String userId, TicketRequest ticketRequest) {
        User userDb = userRepository.get(userId);
        if(ObjectUtils.isEmpty(userDb))
            throw new UserNotFoundException("userId is invalid");
        Ticket ticket = TicketMapper.INSTANCE.ticketRequestToTicket(ticketRequest);
        ticket.setCreatedBy(userId);
        Ticket ticketDb = ticketRepository.file(ticket);
        TicketResponse ticketResponse = TicketMapper.INSTANCE.ticketToTicketResponse(ticketDb);
        User user = userRepository.get(userId);
        ticketResponse.setCreatedBy(user.getName());
        return ticketResponse;
    }

    public List<TicketResponse> getAllTickets(String userId) {
        User userDb = userRepository.get(userId);
        if(ObjectUtils.isEmpty(userDb))
            throw new UserNotFoundException("userId is invalid");
        List<Ticket> tickets = ticketRepository.getAll(userId);
        List<TicketResponse> ticketResponses = new ArrayList<TicketResponse>();
        for(int i = 0; i < tickets.size(); i++) {
            TicketResponse ticketResponse = TicketMapper.INSTANCE.ticketToTicketResponse(tickets.get(i));
            User user = userRepository.get(ticketResponse.getCreatedBy());
            ticketResponse.setCreatedBy(user.getName());
            ticketResponses.add(ticketResponse);
        }
        return ticketResponses;
    }
}
