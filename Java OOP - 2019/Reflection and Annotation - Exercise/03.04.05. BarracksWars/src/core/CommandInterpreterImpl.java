package core;

import contracts.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String COMMAND_PACKAGE = "core.commands.";

    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    // Problem 05 - Dependency injection
    @SuppressWarnings("unchecked")
    @Override
    public Executable interpretCommand(String[] data) {
        String commandName = Character.toUpperCase(data[0].charAt(0)) +
                data[0].substring(1) + "Command";
        Executable executable = null;
        try {
            Class<? extends Executable> commandClass =
                    (Class<? extends Executable>) Class.forName(CommandInterpreterImpl.COMMAND_PACKAGE + commandName);
            Constructor constructor = commandClass.getDeclaredConstructor(String[].class);
            executable = (Executable) constructor.newInstance(new Object[]{ data });

            Field[] executableFields = executable.getClass().getDeclaredFields();
            Field[] thisCommandImplFields = this.getClass().getDeclaredFields();

            for (Field executableField : executableFields) {
                if (executableField.isAnnotationPresent(Inject.class)) {
                    for (Field thisCommandImplField : thisCommandImplFields) {
                        if (executableField.getType().equals(thisCommandImplField.getType())) {
                            executableField.setAccessible(true);
                            executableField.set(executable, thisCommandImplField.get(this));
                        }
                    }
                }
            }

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return executable;
    }
}
