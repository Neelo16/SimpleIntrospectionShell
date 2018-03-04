package pt.ist.ap.labs;

public class IndexCommand extends Command {
    IndexCommand(ObjectHandler handler, String[] arguments) {
        super(handler, arguments);
    }

    @Override
    public void execute() {
        Object[] array = (Object[]) this.handler.getLastResult();
        int index = Integer.parseInt(arguments[0]);
        this.handler.store(array[index]);
        System.out.println(array[index]);
    }
}
