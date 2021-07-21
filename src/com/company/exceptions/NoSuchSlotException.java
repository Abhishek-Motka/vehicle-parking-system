package com.company.exceptions;

public class NoSuchSlotException extends RuntimeException
{
    private int parkingNo;

    public NoSuchSlotException(int parkingNo)
    {
        this.parkingNo = parkingNo;
    }

    @Override
    public String getMessage()
    {
        return "No parking slot found with parking no: " + parkingNo;
    }

    public int getParkingNo()
    {
        return parkingNo;
    }
}
