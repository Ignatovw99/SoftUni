package app;

public class Engine implements Runnable {
    private QueryManager queryManager;

    public Engine(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public void run() {
        System.out.println(this.queryManager.executeExercise());
    }
}
