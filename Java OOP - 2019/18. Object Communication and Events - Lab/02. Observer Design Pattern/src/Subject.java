public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void setNextData(String data);
}
