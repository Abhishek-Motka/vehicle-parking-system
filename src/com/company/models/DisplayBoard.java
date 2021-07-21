package com.company.models;

import com.company.enums.ParkingSlotType;

import java.util.*;
import java.util.stream.Collectors;

public class DisplayBoard
{
    private static Map<Integer, DisplayBoard> displayBoards = new HashMap<>();
    private ParkingFloor parkingFloor;

    private DisplayBoard(ParkingFloor parkingFloor)
    {
        this.parkingFloor = parkingFloor;
    }

    public static DisplayBoard getInstance(ParkingFloor parkingFloor) {
        int floorNo = parkingFloor.getFloorNo();
        DisplayBoard displayBoard = displayBoards.get(floorNo);
        if (Objects.nonNull(displayBoard)) return displayBoard;
        synchronized (DisplayBoard.class) {
            displayBoard = displayBoards.get(floorNo);
            if (Objects.isNull(displayBoard)) {
                displayBoard = new DisplayBoard(parkingFloor);
                displayBoards.put(floorNo, displayBoard);
            }
        }
        return displayBoard;
    }

    public void showAvailableParkings() {
        System.out.println("Floor: " + parkingFloor.getFloorNo());
        List<ParkingSlot> availableSlots = new ArrayList<>();
        for (ParkingSlotType slotType : ParkingSlotType.values()) {
            List<ParkingSlot> typeWiseAvailableSlots = parkingFloor.getParkingSlotOfType(slotType)
                    .stream().filter(slot -> slot.isAvailable()).collect(Collectors.toList());
            availableSlots.addAll(typeWiseAvailableSlots);
        }

        if (availableSlots.size() == 0) {
            System.out.println("No parking available.");
            return;
        }

        System.out.println("Available parking slots: ");
        for (ParkingSlot slot : availableSlots) {
            System.out.println("Parking No: " + slot.getParkingNo() + " Type: " + slot.getType());
        }
    }

    public void showParkedVehicles() {
        System.out.println("Floor: " + parkingFloor.getFloorNo());
        List<ParkingSlot> assignedSlots = new ArrayList<>();
        for (ParkingSlotType slotType : ParkingSlotType.values()) {
            assignedSlots.addAll(parkingFloor.getParkingSlotOfType(slotType)
                                          .stream().filter(slot -> slot.isAssigned()).collect(Collectors.toList()));
        }

        if (assignedSlots.size() == 0) {
            System.out.println("No car parked");
            return;
        }

        System.out.println("Parked vehicles: ");
        for (ParkingSlot slot : assignedSlots) {
            System.out.println("Parking No: " + slot.getParkingNo() + " Vehicle [" + slot.getVehicle().toString() + "]");
        }
    }
}
