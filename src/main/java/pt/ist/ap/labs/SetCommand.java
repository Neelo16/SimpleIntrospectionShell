package pt.ist.ap.labs;

public class SetCommand extends Command {
    SetCommand(ObjectHandler handler, String[] arguments) {
        super(handler, arguments);
    }

    @Override
    public void execute() {
        this.handler.set(arguments[0]);
        Object lastResult = this.handler.getLastResult();
        System.out.println("Saved name for object of type: " + lastResult.getClass());
        System.out.println(lastResult);
    }
}
