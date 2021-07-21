package com.company.commands;

public enum UserCommands
{
    CREATE_SLOTS
            {
                @Override
                public Command getCommand()
                {
                    return new CreateParkingCommand();
                }
            },
    DISPLAY_INFO
            {
                @Override
                public Command getCommand()
                {
                    return new DisplayInfoCommand();
                }
            },
    PARK_VEHICLE
            {
                @Override
                public Command getCommand()
                {
                    return new ParkVehicleCommand();
                }
            },
    UNPARK_VEHICLE
            {
                @Override
                public Command getCommand()
                {
                    return new UnparkVehicleCommand();
                }
            };

    public abstract Command getCommand();

    public void executeCommand(String[] args) {
        try {
            boolean successful = getCommand().execute(args);
            if (!successful) {
                System.out.println("Failed to execute the command");
            }
        } catch (RuntimeException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
