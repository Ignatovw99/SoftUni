package core.commands;

import contracts.Executable;

public abstract class CommandImpl implements Executable {
    private String[] data;

    protected CommandImpl(String[] data) {
        this.data = data;
    }

    protected String[] getData() {
        return this.data;
    }
}
