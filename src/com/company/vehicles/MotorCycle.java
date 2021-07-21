package com.company.vehicles;


import com.company.enums.Color;
import com.company.enums.VehicleSize;

public class MotorCycle extends Vehicle
{
    public MotorCycle(String registrationNo, Color color)
    {
        super(registrationNo, color, VehicleSize.SMALL);
    }
}
