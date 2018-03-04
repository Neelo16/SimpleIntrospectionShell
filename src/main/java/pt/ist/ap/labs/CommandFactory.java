package pt.ist.ap.labs;

import java.lang.reflect.InvocationTargetException;

public class CommandFactory {

    private ObjectHandler handler = new ObjectHandler();

    @SuppressWarnings("unchecked")
    public Command getCommand(String commandName, String[] arguments) {
        try {
            Class commandClass = Class.forName("pt.ist.ap.labs." + commandName + "Command");
            return (Command) commandClass.getDeclaredConstructor(handler.getClass(), String[].class)
                    .newInstance(new Object[] {handler, arguments});
        } catch (ClassNotFoundException e) {
            return new InvocationCommand(handler, commandName, arguments);
        } catch (IllegalAccessException | InstantiationException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
