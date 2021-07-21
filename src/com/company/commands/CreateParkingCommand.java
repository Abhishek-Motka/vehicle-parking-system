package com.company.commands;

import com.company.enums.ParkingSlotType;
import com.company.models.ParkingFloor;

public class CreateParkingCommand implements Command
{
    @Override
    public boolean execute(String[] args)
    {
        int floorNo = Integer.parseInt(args[1]);
        ParkingFloor parkingFloor = ParkingFloor.createInstance(floorNo);
        for (int i = 2; i < args.length; i += 2) {
            ParkingSlotType type = ParkingSlotType.valueOf(args[i]);
            int newSlots = Integer.parseInt(args[i+1]);
            parkingFloor.addParkingSlots(type, newSlots);
        }
        parkingFloor.getDisplayBoard().showAvailableParkings();
        return true;
    }
}
