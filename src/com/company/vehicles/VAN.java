package com.company.vehicles;

import com.company.enums.Color;
import com.company.enums.VehicleSize;

public class VAN extends Vehicle
{
    public VAN(String registrationNo, Color color)
    {
        super(registrationNo, color, VehicleSize.LARGE);
    }
}
