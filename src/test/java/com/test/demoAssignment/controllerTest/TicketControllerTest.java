package com.test.demoAssignment.controllerTest;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demoAssignment.controller.TicketController;
import com.test.demoAssignment.entity.ticket.TicketRequest;
import com.test.demoAssignment.entity.ticket.TicketResponse;
import com.test.demoAssignment.entity.ticket.Type;
import com.test.demoAssignment.entity.user.UserRequest;
import com.test.demoAssignment.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@WebMvcTest (TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    TicketResponse mockTicketResponse = new TicketResponse("1", "Shashank", "Server issue", Type.PUBLIC);

    TicketRequest mockTicketRequest = new TicketRequest("Server issue", Type.PUBLIC);

    @Test
    public void fileTicketTest() throws Exception {

        Mockito.when(ticketService.fileTicket(Mockito.anyString(), Mockito.any(TicketRequest.class))).thenReturn(mockTicketResponse);

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/ticket").header("userId", "1234")
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(mockTicketRequest))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(ticketService).fileTicket(Mockito.anyString(), Mockito.any(TicketRequest.class));

    }

    @Test
    public void getTicketsTest() throws Exception {

        TicketResponse response1 = new TicketResponse("2", "Shashank", "issue1", Type.PUBLIC);
        TicketResponse response2 = new TicketResponse("3", "Shashank", "issue2", Type.PRIVATE);
        TicketResponse response3 = new TicketResponse("2", "Rohit", "issue1", Type.PUBLIC);
        TicketResponse response4 = new TicketResponse("2", "Rohit", "issue1", Type.PRIVATE);

        List<TicketResponse> mockTicketResponses = new ArrayList<>(Arrays.asList(response1, response2, response3));

        Mockito.when(ticketService.getAllTickets(Mockito.anyString())).thenReturn(mockTicketResponses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/tickets").header("userId", "1234")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        verify(ticketService).getAllTickets(Mockito.anyString());
    }

}
