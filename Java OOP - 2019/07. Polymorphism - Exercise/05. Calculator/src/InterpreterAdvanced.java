import java.util.ArrayDeque;
import java.util.Deque;

public class InterpreterAdvanced extends InputInterpreter {
    private Deque<Integer> memory;

    public InterpreterAdvanced(CalculationEngine engine) {
        super(engine);
        this.memory = new ArrayDeque<>();
    }

    @Override
    public Operation getOperation(String operation) {
        Operation operationFromBaseClass = super.getOperation(operation);
        if (operationFromBaseClass == null) {
            if (operation.equals("/")) {
                return new DivisionOperation();
            } else if (operation.equals("ms") || operation.equals("mr")) {
                return new MemoryOperation(this.memory, operation);
            }
        }
        return operationFromBaseClass;
    }
}
