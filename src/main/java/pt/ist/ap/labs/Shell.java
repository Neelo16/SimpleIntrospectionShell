package pt.ist.ap.labs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Shell {
    public static void main(String[] args) throws IOException {
        String line;
        CommandFactory factory = new CommandFactory();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Simple Introspection Shell!");

        while (true) {
            System.out.print("Command:> ");
            System.out.flush();
            if ((line = in.readLine()) == null)
                break;
            String[] arguments = line.split(" ");
            String commandName = arguments[0];
            arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            Command command = factory.getCommand(commandName, arguments);
            command.execute();
        }
    }
}
