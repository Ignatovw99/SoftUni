import java.util.ArrayList;
import java.util.List;

public class MessageSubject implements Subject {
    private List<Observer> observers;
    private String latestData;

    public MessageSubject() {
        this.observers = new ArrayList<>();
        this.latestData = "";
    }

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void setNextData(String data) {
        this.latestData = data;
        this.notifyObservers();
    }

    private void notifyObservers() {
        this.observers
                .forEach(observer -> observer.update(this.latestData));
    }
}
