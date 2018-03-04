package pt.ist.ap.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Shell {
    public static void main(String[] args) throws IOException {
        String command;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Simple Introspection Shell!");

        while ((command = in.readLine()) != null) {
            System.out.print("Command:> ");
            System.out.flush();
            String[] splitCommand = command.split(" ");
            String[] arguments = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
        }
    }
}
