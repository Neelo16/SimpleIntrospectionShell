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
        ArgumentParser parser = new ArgumentParser(this.arguments);
        Object[] args = parser.parse();
        Method[] methods = Arrays.stream(lastResult.getClass().getMethods()).filter((m) ->
                m.getName().equals(this.commandName) &&
                m.getParameterCount() == args.length)
                .toArray(Method[]::new);
        for (Method m: methods) {
            boolean canCall = true;
            Class<?>[] parameterTypes = m.getParameterTypes();
            for (int i = 0; i < this.arguments.length; i++) {
                String argument = this.arguments[i];
                Class<?> parameterType = parameterTypes[i];
                if (!args.getClass().equals(parameterType)) {
                    canCall = false;
                    break;
                }
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
}
