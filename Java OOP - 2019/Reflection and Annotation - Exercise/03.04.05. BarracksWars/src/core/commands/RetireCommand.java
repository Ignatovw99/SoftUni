package core.commands;

import contracts.Inject;
import contracts.Repository;
import jdk.jshell.spi.ExecutionControl;

public class RetireCommand extends CommandImpl {
    @Inject
    private Repository repository;

    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        try {
            this.repository.removeUnit(unitType);
            return unitType + " retired!";
        } catch (ExecutionControl.NotImplementedException e) {
            return e.getMessage();
        }
    }
}
