public class Robot extends BaseResident {
    private String model;

    public Robot(String id, String model) {
        super(id);
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }
}
