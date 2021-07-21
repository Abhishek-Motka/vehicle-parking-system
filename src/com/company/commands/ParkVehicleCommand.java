package com.company.commands;

import com.company.enums.Color;
import com.company.exceptions.VehiclNotSupportedException;
import com.company.models.ParkingFloor;
import com.company.vehicles.*;

public class ParkVehicleCommand implements Command
{
    @Override
    public boolean execute(String[] args)
    {
        int floor = Integer.parseInt(args[1]);
        Vehicle vehicle = createVehicle(args);
        ParkingFloor parkingFloor = ParkingFloor.getInstance(floor);
        parkingFloor.enter(vehicle);
        return true && new DisplayInfoCommand().execute(args);
    }

    private Vehicle createVehicle(String[] args) {
        if (args[2].equalsIgnoreCase("car")) {
            return new Car(args[3], Color.valueOf(args[4]));
        } else if (args[2].equalsIgnoreCase("motorcycle")) {
            return new MotorCycle(args[3], Color.valueOf(args[4]));
        } else if (args[2].equalsIgnoreCase("van")) {
            return new VAN(args[3], Color.valueOf(args[4]));
        } else if (args[2].equalsIgnoreCase("bus")) {
            return new BUS(args[3], Color.valueOf(args[4]));
        }
        throw new VehiclNotSupportedException(args[2]);
    }
}
