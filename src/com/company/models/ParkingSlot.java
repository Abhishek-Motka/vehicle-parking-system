package com.company.models;

import com.company.enums.ParkingSlotStatus;
import com.company.enums.ParkingSlotType;
import com.company.exceptions.ParkingSlotEmptyException;
import com.company.vehicles.Vehicle;

public class ParkingSlot
{
    private int parkingNo;
    private Vehicle vehicle;
    private ParkingSlotStatus status;
    private ParkingSlotType type;

    public ParkingSlot(int parkingNo, ParkingSlotType type)
    {
        this.parkingNo = parkingNo;
        this.type = type;
        this.status = ParkingSlotStatus.AVAILABLE;
    }

    public int getParkingNo()
    {
        return parkingNo;
    }

    public Vehicle getVehicle()
    {
        if (!isAssigned()) throw new ParkingSlotEmptyException(this);
        return vehicle;
    }

    public boolean isAvailable()
    {
        return status.equals(ParkingSlotStatus.AVAILABLE);
    }

    public boolean isAssigned()
    {
        return status.equals(ParkingSlotStatus.ASSIGNED);
    }

    public ParkingSlotType getType()
    {
        return type;
    }

    public ParkingSlotStatus getStatus()
    {
        return status;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return type.canFit(vehicle.getSize());
    }

    public void markAssigned(Vehicle vehicle) {
        this.status = ParkingSlotStatus.ASSIGNED;
        this.vehicle = vehicle;
    }

    public void markAvailable() {
        this.status = ParkingSlotStatus.AVAILABLE;
        this.vehicle = null;
    }
}
