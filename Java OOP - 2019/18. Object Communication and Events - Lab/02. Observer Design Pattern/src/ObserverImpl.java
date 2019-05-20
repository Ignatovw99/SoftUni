public abstract class ObserverImpl implements Observer {
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
