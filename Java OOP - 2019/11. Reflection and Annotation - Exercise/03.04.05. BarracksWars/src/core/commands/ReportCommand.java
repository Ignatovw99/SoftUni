package core.commands;

import contracts.Inject;
import contracts.Repository;

public class ReportCommand extends CommandImpl {
    @Inject
    private Repository repository;

    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
