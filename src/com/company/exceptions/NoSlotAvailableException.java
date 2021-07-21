package com.company.exceptions;

import com.company.enums.VehicleSize;

public class NoSlotAvailableException extends RuntimeException
{
    private VehicleSize vehicleSize;

    public NoSlotAvailableException(VehicleSize vehicleSize)
    {
        this.vehicleSize = vehicleSize;
    }

    @Override
    public String getMessage()
    {
        return "No slot available for vehicle size " + vehicleSize.toString();
    }

    public VehicleSize getVehicleSize()
    {
        return vehicleSize;
    }
}
