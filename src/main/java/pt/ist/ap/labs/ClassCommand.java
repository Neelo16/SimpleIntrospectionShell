package pt.ist.ap.labs;

public class ClassCommand extends Command {

    ClassCommand(ObjectHandler handler, String[] arguments) {
        super(handler, arguments);
    }

    @Override
    public void execute() {
        try {
            Class cls = Class.forName(this.arguments[0]);
            System.out.println(cls);
            handler.store(cls);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
