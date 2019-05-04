package core.commands;

import contracts.Inject;
import contracts.Repository;
import contracts.UnitFactory;

public class AddCommand extends CommandImpl {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        this.repository.addUnit(this.unitFactory.createUnit(unitType));
        return unitType + " added!";
    }
}
