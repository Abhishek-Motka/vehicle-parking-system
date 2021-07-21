package com.company.exceptions;

public class VehiclNotSupportedException extends RuntimeException
{
    private String vehicle;

    public VehiclNotSupportedException(String arg)
    {
        this.vehicle = arg;
    }

    @Override
    public String getMessage()
    {
        return "Vehicle type: " + vehicle + " not supported.";
    }
}
