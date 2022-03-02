package com.test.demoAssignment.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.test.demoAssignment.entity.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Ticket file(Ticket ticket) {
        dynamoDBMapper.save(ticket);
        return ticket;
    }

    public List<Ticket> getAll(String userId) {
        List<Ticket> tempTickets = dynamoDBMapper.scan(Ticket.class, new DynamoDBScanExpression());
        List<Ticket> tickets = new ArrayList<Ticket>();
        for(int i = 0; i < tempTickets.size(); i++) {
            Ticket ticket = tempTickets.get(i);
            if(ticket.getEntityType().equals("TICKET")) {
                if(ticket.getTicketType().toString().equals("PUBLIC"))
                    tickets.add(ticket);
                else if(ticket.getCreatedBy().equals(userId))
                    tickets.add(ticket);
            }
        }
        return tickets;
    }

}
