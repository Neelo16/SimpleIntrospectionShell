package pt.ist.ap.labs;

public class ExitCommand extends Command {
    ExitCommand(ObjectHandler handler, String[] arguments) {
        super(handler, arguments);
    }

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
