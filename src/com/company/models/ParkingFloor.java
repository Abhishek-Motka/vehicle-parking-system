package com.company.models;

import com.company.exceptions.NoSuchParkingException;
import com.company.exceptions.VehicleNotFoundException;
import com.company.strategies.BestFitAssignmentStrategy;
import com.company.strategies.SlotAssignmentStrategy;
import com.company.enums.ParkingSlotType;
import com.company.enums.TicketStatus;
import com.company.exceptions.NoSuchSlotException;
import com.company.vehicles.Vehicle;

import java.util.*;

public class ParkingFloor
{
    private static Map<Integer, ParkingFloor> parkings = new HashMap<>();

    private int floorNo;
    private Map<ParkingSlotType, List<ParkingSlot>> parkingSlots;

    private ParkingFloor(int floorNo) {
        this.floorNo = floorNo;
        parkingSlots = new EnumMap<>(ParkingSlotType.class);
        parkingSlots.put(ParkingSlotType.SMALL, new ArrayList<>());
        parkingSlots.put(ParkingSlotType.MEDIUM, new ArrayList<>());
        parkingSlots.put(ParkingSlotType.LARGE, new ArrayList<>());
        parkingSlots.put(ParkingSlotType.XLARGE, new ArrayList<>());
    }

    public static ParkingFloor createInstance(int floorNo) {
        ParkingFloor parkingFloor = parkings.get(floorNo);
        if (Objects.nonNull(parkingFloor)) return parkingFloor;
        synchronized (ParkingFloor.class) {
            parkingFloor = parkings.get(floorNo);
            if (Objects.isNull(parkingFloor)) {
                parkingFloor = new ParkingFloor(floorNo);
                parkings.put(floorNo, parkingFloor);
            }
        }
        return parkingFloor;
    }

    public static ParkingFloor getInstance(int floorNo) {
        ParkingFloor parkingFloor = parkings.get(floorNo);
        if (Objects.isNull(parkingFloor)) throw new NoSuchParkingException(floorNo);
        return parkingFloor;
    }

    public int getFloorNo()
    {
        return floorNo;
    }

    public void addParkingSlots(ParkingSlotType type, int count) {
        List<ParkingSlot> slots = parkingSlots.get(type);
        int newParkingNo = slots.size();
        for (int i = 0; i < count; i++) {
            slots.add(new ParkingSlot(newParkingNo++, type));
        }
    }

    public ParkingSlot assignParkingSlot(Vehicle vehicle, SlotAssignmentStrategy strategy) {
        return strategy.assignParkingSlot(vehicle, parkingSlots);
    }

    public ParkingSlot getParkingSlot(int parkingNo) {
        for (ParkingSlotType slotType : ParkingSlotType.values()) {
            List<ParkingSlot> slots = parkingSlots.getOrDefault(slotType, Collections.EMPTY_LIST);
            Optional<ParkingSlot> slotOptional = slots.stream().filter(slot -> slot.getParkingNo() == parkingNo).findFirst();
            if (slotOptional.isPresent()) return slotOptional.get();
        }
        throw new NoSuchSlotException(parkingNo);
    }

    public ParkingSlot getParkingSlotForVehicle(String registrationNo) {
        for (ParkingSlotType slotType : ParkingSlotType.values()) {
            Optional<ParkingSlot> slotOptional = parkingSlots.get(slotType).stream()
                    .filter(slot -> slot.isAssigned())
                    .filter(slot -> slot.getVehicle().getRegistrationNo().equalsIgnoreCase(registrationNo))
                    .findFirst();
            if (slotOptional.isPresent()) return slotOptional.get();
        }
        throw new VehicleNotFoundException(registrationNo);
    }

    public void enter(Vehicle vehicle) {
        ParkingSlot slot = assignParkingSlot(vehicle, new BestFitAssignmentStrategy());
        ParkingTicket ticket = TicketRegistry.getInstance().generateTicket(vehicle, slot);
        vehicle.park(slot, ticket);
    }

    public void leave(Vehicle vehicle, TicketStatus status) {
        if (status == TicketStatus.LOST) {
            ParkingTicket ticket = TicketRegistry.getInstance().markTicketLost(vehicle);
            vehicle.setTicket(ticket);
            vehicle.unpark();
        } else {
            ParkingTicket ticket = vehicle.getTicket();
            TicketRegistry.getInstance().markTicketPaid(ticket);
            vehicle.unpark();
        }
    }

    public List<ParkingSlot> getParkingSlotOfType(ParkingSlotType slotType) {
        return parkingSlots.get(slotType);
    }

    public DisplayBoard getDisplayBoard() {
        return DisplayBoard.getInstance(this);
    }
}
