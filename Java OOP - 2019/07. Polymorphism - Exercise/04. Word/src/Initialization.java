public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface commandInterface = new AdvancedCommands(text);
        commandInterface.init();
        return commandInterface;
    }
}
