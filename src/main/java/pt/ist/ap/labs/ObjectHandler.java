package pt.ist.ap.labs;

import java.util.HashMap;

public class ObjectHandler {

    private Object lastResult;
    private HashMap<String,Object> savedObjects = new HashMap<>();

    void store(Object o) {
        lastResult = o;
    }

    void set(String name) {
        savedObjects.put(name, lastResult);
    }
}
