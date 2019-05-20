public class EmailObserver extends ObserverImpl {
    @Override
    public void update(String message) {
        System.out.print("Email: ");
        super.update(message);
    }
}
