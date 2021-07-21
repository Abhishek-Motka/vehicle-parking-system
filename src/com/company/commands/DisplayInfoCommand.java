package com.company.commands;

import com.company.models.DisplayBoard;
import com.company.models.ParkingFloor;

public class DisplayInfoCommand implements Command
{
    @Override
    public boolean execute(String[] args)
    {
        int floor = Integer.parseInt(args[1]);
        DisplayBoard displayBoard = ParkingFloor.getInstance(floor).getDisplayBoard();
        displayBoard.showAvailableParkings();
        displayBoard.showParkedVehicles();
        return true;
    }
}
