package pt.ist.ap.labs;

import java.util.Arrays;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Simple Introspection Shell!");

        while (in.hasNext()) {
            System.out.print("Command:> ");
            System.out.flush();
            String command = in.nextLine();
            String[] splitCommand = command.split(" ");
            String[] arguments = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
            System.out.println("Command: " + command);
            System.out.println("Arguments:");
            for (String arg : arguments) {
                System.out.print(" - ");
                System.out.println(arg);
            }
        }
    }
}
