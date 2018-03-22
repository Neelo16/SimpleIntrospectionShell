package pt.ist.ap.labs;

import java.util.ArrayList;

public class ArgumentParser {

    private String[] arguments;
    private int argumentIndex;
    private ArrayList<Object> parsedObjects = new ArrayList<>();

    public ArgumentParser(String[] arguments) {
        this.arguments = arguments;
    }

    public Object[] parse() {
        try {
            for (argumentIndex = 0; argumentIndex < arguments.length; argumentIndex++) {
                String argument = arguments[argumentIndex];
                Object instance;
                if (isNew(argument)) {
                    instance = instantiateObject();
                } else {
                    throw new RuntimeException("Failed to parse arguments");
                }
                parsedObjects.add(instance);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Failed to parse arguments", e);
        }
        return parsedObjects.toArray();
    }

    private boolean isNew(String argument) {
        return argument.equals("new");
    }

    // TODO
    private Object instantiateObject() {
        String argument = arguments[++argumentIndex];
        int arrayDepth = 0;
        while (argument.matches(".*\\[]$")) {
            ++arrayDepth;
            argument = argument.substring(0, argument.length() - 2);
        }
        return null;
    }
}
