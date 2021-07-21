package com.company.vehicles;

import com.company.enums.Color;
import com.company.enums.VehicleSize;

public class BUS extends Vehicle
{
    public BUS(String registrationNo, Color color)
    {
        super(registrationNo, color, VehicleSize.XLARGE);
    }
}
