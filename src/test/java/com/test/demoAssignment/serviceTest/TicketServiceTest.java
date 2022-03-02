package com.test.demoAssignment.serviceTest;

import com.test.demoAssignment.entity.ticket.Ticket;
import com.test.demoAssignment.entity.ticket.TicketRequest;
import com.test.demoAssignment.entity.ticket.TicketResponse;
import com.test.demoAssignment.entity.ticket.Type;
import com.test.demoAssignment.entity.user.User;
import com.test.demoAssignment.repository.TicketRepository;
import com.test.demoAssignment.repository.UserRepository;
import com.test.demoAssignment.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Autowired
    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;


    Ticket mockTicket = new Ticket("1","1234", "Server issue", Type.PUBLIC);

    TicketRequest mockTicketRequest = new TicketRequest("Server issue", Type.PUBLIC);

    User mockUser = new User("1234", "Shashank", "shashank@gmail.com");

    @Test
    public void fileTicketTest() throws Exception {

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(mockUser);

        Mockito.when(ticketRepository.file(Mockito.any(Ticket.class))).thenReturn(mockTicket);

        TicketResponse ticketResponse = ticketService.fileTicket("1234", mockTicketRequest);

        assertEquals(ticketResponse.getTicketId(), "1");

        assertEquals(ticketResponse.getCreatedBy(), "Shashank");
    }

    @Test
    public void getTicketsTest() throws Exception {

        Ticket response1 = new Ticket("2", "1234", "issue1", Type.PUBLIC);
        Ticket response2 = new Ticket("3", "1234", "issue2", Type.PRIVATE);
        Ticket response3 = new Ticket("2", "2345", "issue1", Type.PUBLIC);
        Ticket response4 = new Ticket("2", "2345", "issue1", Type.PRIVATE);

        List<Ticket> mockTickets = new ArrayList<>(Arrays.asList(response1, response2, response3));

        Mockito.when(userRepository.get(Mockito.anyString())).thenReturn(mockUser);

        Mockito.when(ticketRepository.getAll(Mockito.anyString())).thenReturn(mockTickets);

        List<TicketResponse> ticketResponses = ticketService.getAllTickets("1234");

        assertEquals(ticketResponses.size(), 3);

        assertEquals(ticketResponses.get(0).getCreatedBy(), "Shashank");

    }
}
