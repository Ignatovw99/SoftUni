package p05_DependencyInversion.p02_Worker;

public class Manager implements Worker {
    @Override
    public void work() {
        System.out.println("Manager is working");
    }
}
