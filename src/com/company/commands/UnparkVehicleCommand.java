package com.company.commands;

import com.company.enums.TicketStatus;
import com.company.models.ParkingFloor;
import com.company.models.ParkingSlot;
import com.company.vehicles.Vehicle;

public class UnparkVehicleCommand implements Command
{
    @Override
    public boolean execute(String[] args)
    {
        int floor = Integer.parseInt(args[1]);
        ParkingFloor parkingFloor = ParkingFloor.getInstance(1);
        ParkingSlot slot = parkingFloor.getParkingSlotForVehicle(args[2]);
        Vehicle vehicle = slot.getVehicle();
        parkingFloor.leave(vehicle, TicketStatus.ACTIVE);
        return true && new DisplayInfoCommand().execute(args);
    }
}
