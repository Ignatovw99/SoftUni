public class Main {
    public static void main(String[] args) {
        Subject subject = new MessageSubject();
        Observer emailObserver = new EmailObserver();
        Observer loginObserver = new LoginObserver();

        subject.register(emailObserver);
        subject.setNextData("NEW DATA");

        subject.register(loginObserver);
        subject.setNextData("NEW DATA FOR BOTH");
    }
}
