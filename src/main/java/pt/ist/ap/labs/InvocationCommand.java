package pt.ist.ap.labs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvocationCommand extends Command {
    private String commandName;

    InvocationCommand(ObjectHandler handler, String commandName, String[] arguments) {
        super(handler, arguments);
        this.commandName = commandName;
    }

    @Override
    public void execute() {
        System.out.println("Trying generic command: " + this.commandName);
        Object lastResult = this.handler.getLastResult();
        Method[] methods = Arrays.stream(lastResult.getClass().getMethods()).filter((m) ->
                m.getName().equals(this.commandName) &&
                m.getParameterCount() == this.arguments.length)
                .toArray(Method[]::new);
        Object[] args = new Object[this.arguments.length];
        for (Method m: methods) {
            System.out.println("m = " + m);
            boolean canCall = true;
            Class<?>[] parameterTypes = m.getParameterTypes();
            for (int i = 0; i < this.arguments.length; i++) {
                String argument = this.arguments[i];
                Class<?> parameterType = parameterTypes[i];
                Object arg = stringToObject(argument, parameterType);
                if (arg == null) {
                    canCall = false;
                    break;
                }
                args[i] = arg;
            }
            if (canCall) {
                try {
                    Object result = m.invoke(lastResult, args);
                    this.handler.store(result);
                    System.out.println(getResult(result));
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("No such method");
    }

    private Object stringToObject(String string, Class type) {
        System.out.println("string = [" + string + "], type = [" + type + "]");
        if (type.equals(String.class)) {
            return string;
        } else if (type.equals(Integer.class)) {
            try {
                Integer i = Integer.parseInt(string);
                return i;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (type.equals(Double.class)) {
            try {
                Double d = Double.parseDouble(string);
                return d;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        Object result = this.handler.get(string);
        if (result == null) {
            Class<?>[] possibleTypes = { Integer.class, Double.class, String.class };
            for (Class<?> possibleType : possibleTypes) {
                result = stringToObject(string, possibleType);
                if (result != null)
                    break;
            }
        }
        return result;
    }
}
