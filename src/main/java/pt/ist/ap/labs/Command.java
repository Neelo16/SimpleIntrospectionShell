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

    protected String getResult(Object obj) {
        if (obj == null)
            return "Result Non Defined";
        if (obj.getClass().isArray()) {
            StringBuilder sb = new StringBuilder();
            for (Object o : (Object[]) obj) {
                sb.append(getResult(o));
                sb.append("\n");
            }
            return sb.toString();
        }
        return obj.toString();
    }
}
