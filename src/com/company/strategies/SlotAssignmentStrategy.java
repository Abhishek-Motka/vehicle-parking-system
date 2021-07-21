package com.company.strategies;

import com.company.enums.ParkingSlotType;
import com.company.models.ParkingSlot;
import com.company.vehicles.Vehicle;

import java.util.List;
import java.util.Map;

public interface SlotAssignmentStrategy
{
    ParkingSlot assignParkingSlot(Vehicle vehicle, Map<ParkingSlotType, List<ParkingSlot>> parkingSlots);
}
