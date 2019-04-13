package cresla.entities.reactors;

public class CryoReactor extends BaseReactor {
    private int cryoProductionIndex;

    public CryoReactor(int id, int moduleCapacityint, int cryoProductionIndex) {
        super(id, moduleCapacityint);
        this.cryoProductionIndex = cryoProductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        return super.getTotalEnergyOutput() * this.cryoProductionIndex;
    }
}
