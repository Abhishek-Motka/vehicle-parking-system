package com.company.vehicles;

import com.company.models.ParkingSlot;
import com.company.models.ParkingTicket;
import com.company.enums.Color;
import com.company.enums.VehicleSize;

public class Vehicle
{
    private String registrationNo;
    private Color color;
    private VehicleSize size;
    private ParkingTicket ticket;

    public Vehicle(String registrationNo, Color color, VehicleSize size)
    {
        this.registrationNo = registrationNo;
        this.color = color;
        this.size = size;
    }

    public String getRegistrationNo()
    {
        return registrationNo;
    }

    public Color getColor()
    {
        return color;
    }

    public VehicleSize getSize()
    {
        return size;
    }

    public void park(ParkingSlot slot, ParkingTicket ticket) {
        slot.markAssigned(this);
        this.ticket = ticket;
    }

    public void unpark() {
        ticket.getSlot().markAvailable();
    }

    public void setTicket(ParkingTicket ticket)
    {
        this.ticket = ticket;
    }

    public ParkingTicket getTicket()
    {
        return ticket;
    }

    @Override
    public String toString()
    {
        return "Registration No: " + registrationNo + " Color: " + color;
    }
}
