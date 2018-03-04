package pt.ist.ap.labs;

public class InvocationCommand extends Command {
    InvocationCommand(ObjectHandler handler, String commandName, String[] arguments) {
        super(handler);
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Command not implemented");
    }
}
