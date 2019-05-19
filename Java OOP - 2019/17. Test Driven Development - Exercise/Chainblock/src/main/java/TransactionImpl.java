import java.util.Objects;

public class TransactionImpl implements Transaction, Comparable<TransactionImpl>{

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public TransactionStatus getTransactionStatus() {
        return this.status;
    }

    @Override
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.status = transactionStatus;
    }

    @Override
    public String getSender() {
        return this.from;
    }

    @Override
    public String getReceiver() {
        return this.to;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    public int compareTo(TransactionImpl o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        TransactionImpl that = (TransactionImpl) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
