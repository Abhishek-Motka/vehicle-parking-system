package com.company.exceptions;

public class NoSuchParkingException extends RuntimeException
{
    private int floorNo;

    public NoSuchParkingException(int floorNo)
    {
        this.floorNo = floorNo;
    }

    @Override
    public String getMessage()
    {
        return "Parking does not exist at floor: " + floorNo;
    }

    public int getFloorNo()
    {
        return floorNo;
    }
}
