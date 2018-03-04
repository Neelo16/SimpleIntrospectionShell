package pt.ist.ap.labs;

public abstract class Command {
    protected ObjectHandler handler;
    protected String[] arguments;

    Command() { this.handler = null; }
    Command(ObjectHandler handler) { this.handler = handler; }
    Command(ObjectHandler handler, String[] arguments) {
        this.handler = handler;
        this.arguments = arguments.clone();
    }

    public abstract void execute();
}
