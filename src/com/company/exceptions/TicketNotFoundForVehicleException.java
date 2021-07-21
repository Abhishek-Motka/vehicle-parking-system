package com.company.exceptions;

import com.company.vehicles.Vehicle;

public class TicketNotFoundForVehicleException extends RuntimeException
{
    private Vehicle vehicle;

    public TicketNotFoundForVehicleException(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    @Override
    public String getMessage()
    {
        return "TicketNotFoundForVehicleException{" +
                "vehicle=" + vehicle +
                '}';
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }
}
