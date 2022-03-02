package com.test.demoAssignment.entity.ticket;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMapper {

    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    Ticket ticketRequestToTicket(TicketRequest ticketRequest);

    @Mapping(source = "entityId", target = "ticketId")
    TicketResponse ticketToTicketResponse(Ticket ticket);
}
