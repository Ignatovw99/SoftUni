public interface Transaction {
    int getId();

    TransactionStatus getTransactionStatus();

    void setTransactionStatus(TransactionStatus transactionStatus);

    double getAmount();

    String getSender();

    String getReceiver();
}
