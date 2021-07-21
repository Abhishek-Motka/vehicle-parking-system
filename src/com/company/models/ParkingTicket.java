package com.company.models;

import com.company.enums.TicketStatus;
import com.company.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket
{
    private UUID ticketNo;
    private Vehicle vehicle;
    private TicketStatus status;
    private ParkingSlot slot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingTicket(Vehicle vehicle, ParkingSlot slot)
    {
        this.ticketNo = UUID.randomUUID();
        this.vehicle = vehicle;
        this.slot = slot;
        this.status = TicketStatus.ACTIVE;
        this.entryTime = LocalDateTime.now();
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public TicketStatus getStatus()
    {
        return status;
    }

    public ParkingSlot getSlot()
    {
        return slot;
    }

    public LocalDateTime getEntryTime()
    {
        return entryTime;
    }

    public LocalDateTime getExitTime()
    {
        return exitTime;
    }

    public UUID getTicketNo()
    {
        return ticketNo;
    }

    public void markPaid() {
        this.status = TicketStatus.PAID;
        this.exitTime = LocalDateTime.now();
    }

    public void markLost() {
        this.status = TicketStatus.LOST;
        this.exitTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof ParkingTicket
                && this.ticketNo.equals(((ParkingTicket) obj).getTicketNo());
    }
}
