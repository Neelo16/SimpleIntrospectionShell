package pt.ist.ap.labs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvocationCommand extends Command {
    private String commandName;

    InvocationCommand(ObjectHandler handler, String commandName, String[] arguments) {
        super(handler, arguments);
        this.commandName = commandName;
    }

    @Override
    public void execute() {
        System.out.println("Trying generic command: " + this.commandName);
        Object o = this.handler.getLastResult();
        Method[] methods = Arrays.stream(o.getClass().getMethods()).filter((m) ->
                m.getName().equals(this.commandName) &&
                m.getParameterCount() == this.arguments.length)
                .toArray(Method[]::new);
        if (methods[0].getParameterCount() == 0) {
            try {
                Object result = methods[0].invoke(o);
                this.handler.store(result);
                System.out.println(super.getResult(result));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new UnsupportedOperationException("Command not implemented");
        }
    }
}
