package com.company.vehicles;

import com.company.enums.Color;
import com.company.enums.VehicleSize;

public class Car extends Vehicle
{
    public Car(String registrationNo, Color color)
    {
        super(registrationNo, color, VehicleSize.MEDIUM);
    }
}
