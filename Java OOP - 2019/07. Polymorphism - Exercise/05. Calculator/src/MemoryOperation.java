import java.util.Deque;

public class MemoryOperation implements Operation {
    private static final String REMOVE_COMMAND_STRING = "mr";
    private Deque<Integer> memory;
    private String operation;

    public MemoryOperation(Deque<Integer> memory, String operation) {
        this.memory = memory;
        this.operation = operation;
    }

    @Override
    public void addOperand(int operand) {
        this.memory.push(operand);
    }

    @Override
    public int getResult() {
        return this.memory.pop();
    }

    @Override
    public boolean isCompleted() {
        if (MemoryOperation.REMOVE_COMMAND_STRING.equals(this.operation)) {
            return true;
        }
        return false;
    }
}
