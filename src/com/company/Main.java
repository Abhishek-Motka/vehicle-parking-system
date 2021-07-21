package com.company;

import com.company.commands.UserCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args)
    {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(streamReader);
        while (true) {
            System.out.print("Enter command: ");
            String input = "";
            try
            {
                input = reader.readLine();
            }
            catch (IOException e)
            {
                System.out.println("Failed to get input from user.");
            }

            String[] command = input.split(" ");
            if (command.length == 0) {
                System.out.println("Invalid command");
                continue;
            }

            if (command[0].equalsIgnoreCase("exit")) break;

            UserCommands.valueOf(command[0]).executeCommand(command);
        }

        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
