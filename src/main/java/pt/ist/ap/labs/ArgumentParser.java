package pt.ist.ap.labs;

import java.util.ArrayList;

public class ArgumentParser {

    private ArrayList<Object> parsedObjects = new ArrayList<>();

    public ArgumentParser(String[] arguments) {
        this.arguments = arguments;
    }

    public Object[] parse() {
        return parsedObjects.toArray();
    }

}
