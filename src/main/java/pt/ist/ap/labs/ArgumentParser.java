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
                } else if (isInteger(argument)) {
                    instance = createInteger(argument);
                } else if (isFloat(argument)) {
                    instance = createFloat(argument);
                } else if (isString(argument)) {
                    instance = createString(argument);
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

    private String createString(String argument) {
        return argument;
    }

    private boolean isString(String argument) {
        // FIXME this does not consider unescaped quotes in the middle of the string or escaped quotes at the end
        // Check if argument has quotes around it
        return argument.matches("^\".*\"$");
    }

    private Object createFloat(String argument) {
        return Float.parseFloat(argument);
    }

    private boolean isFloat(String argument) {
        // Regex for an exponent component to the float (i.e. 314e-2 or 3e6)
        String expRegex = "([eE][+-]?[0-9])+";
        String sb =
                // Matches integers with exponents
                "[0-9]+" + expRegex +
                "|" +
                // Matches floats with an optional exponent
                "[0-9]+\\.[0-9]*" + expRegex + "?" +
                "|" +
                // Matches floats that do not have an integer component (also with an optional exponent)
                "\\.[0-9]+" + expRegex + "?";

        return argument.matches(sb);
    }

    private Object createInteger(String argument) {
        return Integer.parseInt(argument);
    }

    private boolean isInteger(String argument) {
        // Check if argument is either 0 or a non-zero digit followed by zero or more digits
        return argument.matches("0|[1-9][0-9]*");
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
