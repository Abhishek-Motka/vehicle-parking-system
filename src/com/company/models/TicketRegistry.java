package com.company.models;

import com.company.exceptions.NoSuchTicketException;
import com.company.exceptions.TicketNotFoundForVehicleException;
import com.company.vehicles.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class TicketRegistry
{
    private static TicketRegistry registry;

    private Set<ParkingTicket> activeTickets;
    private List<ParkingTicket> paidTickets;
    private List<ParkingTicket> lostTickets;

    private TicketRegistry() {
        activeTickets = new HashSet<>();
        paidTickets = new ArrayList<>();
        lostTickets = new ArrayList<>();
    }

    public static TicketRegistry getInstance() {
        if (Objects.nonNull(registry)) return registry;
        synchronized (TicketRegistry.class) {
            if (Objects.isNull(registry)) {
                registry = new TicketRegistry();
            }
        }
        return registry;
    }

    public ParkingTicket generateTicket(Vehicle vehicle, ParkingSlot slot)
    {
        ParkingTicket ticket = new ParkingTicket(vehicle, slot);
        this.activeTickets.add(ticket);
        return ticket;
    }

    public void markTicketPaid(ParkingTicket ticket)
    {
        if (!activeTickets.contains(ticket)) throw new NoSuchTicketException(ticket);
        ticket.markPaid();
        paidTickets.add(ticket);
        activeTickets.remove(ticket);
    }

    public ParkingTicket markTicketLost(Vehicle vehicle)
    {
        Optional<ParkingTicket> ticketOptional = activeTickets.stream()
                .filter(ticket -> ticket.getVehicle().equals(vehicle)).findFirst();
        if (!ticketOptional.isPresent()) throw new TicketNotFoundForVehicleException(vehicle);
        ParkingTicket ticket = ticketOptional.get();
        ticket.markLost();
        lostTickets.add(ticket);
        activeTickets.remove(ticket);
        return ticket;
    }

    public List<ParkingTicket> getActiveTickets()
    {
        return activeTickets.stream().collect(Collectors.toList());
    }

    public List<ParkingTicket> getPaidTickets()
    {
        return paidTickets;
    }

    public List<ParkingTicket> getLostTickets()
    {
        return lostTickets;
    }
}
