package com.company.exceptions;

public class VehicleNotFoundException extends RuntimeException
{
    private String registrationNo;

    public VehicleNotFoundException(String registrationNo)
    {
        this.registrationNo = registrationNo;
    }

    public String getRegistrationNo()
    {
        return registrationNo;
    }

    @Override
    public String getMessage()
    {
        return "Vehicle with registration no: " + registrationNo + " not found in parking.";
    }
}
