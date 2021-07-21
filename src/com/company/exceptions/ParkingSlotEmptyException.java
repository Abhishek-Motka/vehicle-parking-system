package com.company.exceptions;

import com.company.models.ParkingSlot;

public class ParkingSlotEmptyException extends RuntimeException
{
    private ParkingSlot parkingSlot;

    public ParkingSlotEmptyException(ParkingSlot parkingSlot)
    {
        this.parkingSlot = parkingSlot;
    }

    @Override
    public String getMessage()
    {
        return "Parking slot: " + parkingSlot.getParkingNo() + " is not parked.";
    }

    public ParkingSlot getParkingSlot()
    {
        return parkingSlot;
    }
}
