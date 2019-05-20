public class LoginObserver extends ObserverImpl {
    @Override
    public void update(String message) {
        System.out.print("Login: ");
        super.update(message);
    }
}
