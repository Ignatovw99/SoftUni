import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock{
    private Map<Integer, Transaction> record;

    public ChainblockImpl() {
        this.record = new HashMap<>();
    }

    public int getCount() {
        return this.record.size();
    }

    public void add(Transaction transaction) {
        this.record.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return this.contains(transaction.getId());
    }

    public boolean contains(int id) {
        return this.record.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!this.contains(id)) throw new IllegalArgumentException();
        this.record.get(id).setTransactionStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!this.contains(id)) throw new IllegalArgumentException();
        this.record.remove(id);
    }

    public Transaction getById(int id) {
        if (!this.contains(id)) throw new IllegalArgumentException();
        return this.record.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions =  this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getTransactionStatus().equals(status))
                .sorted((first, second) -> Double.compare(second.getAmount(), first.getAmount()))
                .collect(Collectors.toCollection(ArrayList::new));

        if (transactions.isEmpty()) throw new IllegalArgumentException();
        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getTransactionStatus().equals(status))
                .sorted((first, second) -> Double.compare(second.getAmount(), first.getAmount()))
                .map(Transaction::getSender)
                .collect(Collectors.toCollection(ArrayList::new));

        if (senders.isEmpty()) throw new IllegalArgumentException();
        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> receivers = this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getTransactionStatus().equals(status))
                .sorted((first, second) -> Double.compare(second.getAmount(), first.getAmount()))
                .map(Transaction::getReceiver)
                .collect(Collectors.toList());

        if (receivers.isEmpty()) throw new IllegalArgumentException();
        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.record
                .values()
                .stream()
                .sorted(this.orderByAmountDescendingThanById())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> transactionsBySenderOrderedByAmount =
                this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getSender().equals(sender))
                .sorted((first, second) -> Double.compare(second.getAmount(), first.getAmount()))
                .collect(Collectors.toList());

        if (transactionsBySenderOrderedByAmount.isEmpty()) throw new IllegalArgumentException();
        return transactionsBySenderOrderedByAmount;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactionsByReceiverOrderedByAmountAndId =
                this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getReceiver().equals(receiver))
                .sorted(this.orderByAmountDescendingThanById())
                .collect(Collectors.toList());

        if (transactionsByReceiverOrderedByAmountAndId.isEmpty()) throw new IllegalArgumentException();
        return transactionsByReceiverOrderedByAmountAndId;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.record
                .values()
                .stream()
                .filter(transaction ->
                        transaction.getTransactionStatus().equals(status) && transaction.getAmount() <= amount)
                .sorted((first, second) -> Double.compare(second.getAmount(), first.getAmount()))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {

        List<Transaction> transactionsBySenderAndMinimumAmountDescending =
                this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getSender().equals(sender) && transaction.getAmount() > amount)
                .sorted((first, second) -> Double.compare(second.getAmount(), first.getAmount()))
                .collect(Collectors.toList());

        if (transactionsBySenderAndMinimumAmountDescending.isEmpty()) throw new IllegalArgumentException();
        return transactionsBySenderAndMinimumAmountDescending;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double low, double high) {
        List<Transaction> transactionsByReceiverWithinRange =
                this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getReceiver().equals(receiver) && transaction.getAmount() >= low && transaction.getAmount() < high)
                .sorted(this.orderByAmountDescendingThanById())
                .collect(Collectors.toList());

        if (transactionsByReceiverWithinRange.isEmpty()) throw new IllegalArgumentException();
        return transactionsByReceiverWithinRange;
    }

    public Iterable<Transaction> getAllInAmountRange(double low, double high) {
        return this.record
                .values()
                .stream()
                .filter(transaction -> transaction.getAmount() >= low && transaction.getAmount() <= high)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return this.record.values().iterator();
    }

    private Comparator<Transaction> orderByAmountDescendingThanById() {
        return (first, second) -> {
            double firstTransactionAmount = first.getAmount();
            double secondTransactionAmount = second.getAmount();
            if (firstTransactionAmount == secondTransactionAmount) {
                return Integer.compare(first.getId(), second.getId());
            }
            return Double.compare(secondTransactionAmount, firstTransactionAmount);
        };
    }
}
