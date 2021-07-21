package com.company.strategies;

import com.company.enums.ParkingSlotType;
import com.company.enums.VehicleSize;
import com.company.exceptions.NoSlotAvailableException;
import com.company.models.ParkingSlot;
import com.company.vehicles.Vehicle;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BestFitAssignmentStrategy implements SlotAssignmentStrategy
{
    @Override
    public ParkingSlot assignParkingSlot(Vehicle vehicle, Map<ParkingSlotType, List<ParkingSlot>> parkingSlots)
    {
        VehicleSize vehicleSize = vehicle.getSize();
        ParkingSlotType bestFitType = ParkingSlotType.XLARGE;
        for (ParkingSlotType slotType : ParkingSlotType.values()) {
            if (slotType.bestFitVehicleSize().equals(vehicleSize)) {
                bestFitType = slotType;
            }
        }
        List<ParkingSlot> slots = parkingSlots.getOrDefault(bestFitType, Collections.EMPTY_LIST);
        Optional<ParkingSlot> slotOptional = slots.stream().filter(slot -> slot.isAvailable()).findFirst();
        if (!slotOptional.isPresent()) throw new NoSlotAvailableException(vehicleSize);
        return slotOptional.get();
    }
}
