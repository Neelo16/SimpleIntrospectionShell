package pt.ist.ap.labs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PackageCommand extends Command {
    PackageCommand(ObjectHandler handler, String[] arguments) {
        super(handler, arguments);
    }

    @Override
    public void execute() {
        Object lastResult = this.handler.getLastResult();
        Package pkg = lastResult.getClass().getPackage();
        System.out.print("Package information");
        System.out.print(" of [" + lastResult.getClass() + "]");
        System.out.print(" of previous object [" + lastResult + "]):");
        System.out.println(" package " + pkg.getName());
        if (this.arguments.length == 0)
            printInformation(pkg, "");
        else
            printInformation(pkg, this.arguments[0]);
        System.out.println(lastResult);
    }

    private void printInformation(Package pkg, String match) {
        for (Method method : pkg.getClass().getDeclaredMethods()) {
            if (!method.getName().contains(match) || Modifier.isStatic(method.getModifiers()) ||
                    method.isBridge() || method.getParameterCount() != 0 || !method.canAccess(pkg))
                continue;
            System.out.print(method.getName() + ": ");
            try {
                System.out.println(getResult(method.invoke(pkg)));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
