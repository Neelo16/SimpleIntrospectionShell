package pt.ist.ap.labs;

public class GetCommand extends Command {
    GetCommand(ObjectHandler handler, String[] arguments) {
        super(handler, arguments);
    }

    @Override
    public void execute() {
        Object o = this.handler.get(arguments[0]);
        this.handler.store(o);
        System.out.println(o);
    }
}
