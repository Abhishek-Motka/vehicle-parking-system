package com.company.exceptions;

import com.company.models.ParkingTicket;

public class NoSuchTicketException extends RuntimeException
{
    private ParkingTicket ticket;

    public NoSuchTicketException(ParkingTicket ticket)
    {
        this.ticket = ticket;
    }

    @Override
    public String getMessage()
    {
        return "Invalid Parking Ticket: " + ticket.getTicketNo().toString();
    }

    public ParkingTicket getTicket()
    {
        return ticket;
    }
}
