import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ChainblockTests {
    private final int DEFAULT_ID = 1;
    private final TransactionStatus DEFAULT_TRANSACTION_STATUS = TransactionStatus.SUCCESSFUL;
    private final String DEFAULT_SENDER = "Sender";
    private final String DEFAULT_RECEIVER = "Receiver";
    private final double DEFAULT_AMOUNT = 422.21;
    private final double AMOUNT_UPPER_BOUND = DEFAULT_AMOUNT + 1.4;

    private final TransactionStatus[] ALL_STATUSES = TransactionStatus.values();
    private final String[] SENDERS_AND_RECEIVERS = { "John", "Peter", "Smith", "David" };
    private final double[] AMOUNTS = {43.2, 46.4, 49.5, 65.3};
    private final double AMOUNT_LOWER_BOUND = AMOUNTS[0] - 0.001;

    private Chainblock chainblock;
    private Transaction defaultTransaction = new TransactionImpl(DEFAULT_ID, DEFAULT_TRANSACTION_STATUS, DEFAULT_SENDER, DEFAULT_RECEIVER, DEFAULT_AMOUNT);
    private Transaction secondTransaction = new TransactionImpl(DEFAULT_ID + 1, TransactionStatus.ABORTED, DEFAULT_SENDER, DEFAULT_RECEIVER, DEFAULT_AMOUNT);
    private Transaction thirdTransaction = new TransactionImpl(DEFAULT_ID + 2, TransactionStatus.UNAUTHORIZED, DEFAULT_SENDER, DEFAULT_RECEIVER, DEFAULT_AMOUNT);

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();
    }

    @Test
    public void containsTransactionShouldReturnTrueIfTransactionExists() {
        this.chainblock.add(this.defaultTransaction);
        assertTrue(this.chainblock.contains(this.defaultTransaction));
    }

    @Test
    public void containsTransactionShouldReturnFalseForEmptyChainblock() {
        assertFalse(this.chainblock.contains(this.defaultTransaction));
    }

    @Test
    public void containsTransactionShouldReturnFalseIfTransactionDoesNotExist() {
        this.chainblock.add(this.secondTransaction);
        assertFalse(this.chainblock.contains(this.defaultTransaction));
    }

    @Test
    public void getCountShouldWorkCorrectly() {
        this.chainblock.add(this.thirdTransaction);
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.thirdTransaction);
        assertEquals(2, this.chainblock.getCount());
    }

    @Test
    public void addTransactionsShouldIncreaseRecordsCount() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        assertEquals(3, this.chainblock.getCount());
    }

    @Test
    public void addTransactionShouldContainIt() {
        this.chainblock.add(this.defaultTransaction);
        assertTrue(this.chainblock.contains(this.defaultTransaction));
    }

    @Test
    public void addShouldNotIncreaseCountWhenAddingSameTransactionMultipleTimes() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.defaultTransaction);
        assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void containsIdShouldReturnTrueWhenTransactionIsPresent() {
        this.chainblock.add(this.defaultTransaction);
        assertTrue(this.chainblock.contains(DEFAULT_ID));
    }

    @Test
    public void containsIdShouldReturnTrueWhenTransactionIsPresentForMultipleTransactions() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        this.chainblock.add(this.thirdTransaction);
        assertTrue(this.chainblock.contains(DEFAULT_ID));
    }

    @Test
    public void containsIdShouldReturnFalseWhenTransactionIsNotPresent() {
        assertFalse(this.chainblock.contains(DEFAULT_ID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeStatusShouldThrowExceptionIfTransactionDoesNotExist() {
        this.chainblock.changeTransactionStatus(DEFAULT_ID, TransactionStatus.FAILED);
    }

    @Test
    public void changeTransactionStatusShouldNotChangeStatusIfNewValueIsTheSame() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.changeTransactionStatus(DEFAULT_ID, DEFAULT_TRANSACTION_STATUS);
        assertEquals(DEFAULT_TRANSACTION_STATUS, this.defaultTransaction.getTransactionStatus());
    }

    @Test
    public void changeStatusShouldSetNewValueIfTransactionIsPresent() {
        this.chainblock.add(this.defaultTransaction);
        TransactionStatus oldStatus = DEFAULT_TRANSACTION_STATUS;
        TransactionStatus newStatus = TransactionStatus.FAILED;
        this.chainblock.changeTransactionStatus(DEFAULT_ID, newStatus);
        assertNotSame(oldStatus, newStatus);
        assertEquals(newStatus, this.defaultTransaction.getTransactionStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeByIdShouldThrowExceptionIfTransactionWithSuchIdDoesNotExist() {
        this.chainblock.add(this.secondTransaction);
        this.chainblock.removeTransactionById(DEFAULT_ID);
    }

    @Test
    public void removeByIdShouldWorkCorrectlyIfTransactionWithSuchIdExists() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.removeTransactionById(DEFAULT_ID);
        assertFalse(this.chainblock.contains(this.defaultTransaction));
    }

    @Test
    public void removeTransactionByIdShouldWorkCorrectlyIfTransactionWithSuchIdExistsForMultipleTransactions() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.removeTransactionById(DEFAULT_ID + 1);
        assertFalse(this.chainblock.contains(this.secondTransaction));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByIdShouldThrowExceptionWhenTransactionWithSuchIdIsNotPresent() {
        this.chainblock.getById(DEFAULT_ID);
    }

    @Test
    public void getByIdShouldReturnCorrectTransaction() {
        this.chainblock.add(this.defaultTransaction);
        assertEquals(this.defaultTransaction, this.chainblock.getById(DEFAULT_ID));
    }

    @Test
    public void getByIdShouldReturnCorrectTransactionForMultipleTransactions() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        assertEquals(this.secondTransaction, this.chainblock.getById(DEFAULT_ID + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByStatusShouldThrowExceptionForEmptyRecord() {
        this.chainblock.getByTransactionStatus(DEFAULT_TRANSACTION_STATUS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByStatusShouldThrowExceptionIfTransactionsWithGivenStatusNotPresent() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        this.chainblock.getByTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void getByStatusShouldReturnOnlyTransactionsWithCorrectStatus() {
        this.addTransactionsWithDifferentStatusesAndAmounts(this.chainblock);
        Iterable<Transaction> actualTransactions = this.chainblock.getByTransactionStatus(DEFAULT_TRANSACTION_STATUS);

        for (Transaction transaction : actualTransactions) {
            assertEquals(DEFAULT_TRANSACTION_STATUS, transaction.getTransactionStatus());
        }
    }

    @Test
    public void getByTransactionStatusShouldReturnSortedCollection() {
        this.addTransactionsWithDifferentStatusesAndAmounts(this.chainblock);
        Iterator<Transaction> transactionsIterator = this.chainblock.getByTransactionStatus(DEFAULT_TRANSACTION_STATUS).iterator();

        boolean isSorted = true;
        double biggerAmountValue = transactionsIterator.next().getAmount();
        while (transactionsIterator.hasNext()) {
            double currentAmountValue = transactionsIterator.next().getAmount();
            if (biggerAmountValue < currentAmountValue) {
                isSorted = false;
                break;
            }
            biggerAmountValue = currentAmountValue;
        }


        assertTrue("Collection should be sorted.", isSorted);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllSendersWithTransactionStatusShouldThrowExceptionIfStatusNotPresent() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void getAllSendersWithTransactionStatusShouldReturnOnlySendersWithCorrectStatus() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);

        List<String> senders = (List<String>) this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
        boolean containsAll = true;
        for (int i = 0; i < 4; i+=2) {
            if (!senders.contains(SENDERS_AND_RECEIVERS[i])) {
                containsAll = false;
                break;
            }
        }

        assertTrue(containsAll);
    }

    @Test
    public void getAllSendersWithTransactionStatusShouldReturnSortedCollectionWithSenders() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        List<String> actualSenders = (ArrayList<String>) this.chainblock.getAllSendersWithTransactionStatus(DEFAULT_TRANSACTION_STATUS);

        boolean containsAllInCorrectOrder = actualSenders.get(0).equals(SENDERS_AND_RECEIVERS[3])
                && actualSenders.get(1).equals(SENDERS_AND_RECEIVERS[1]);

        assertTrue(containsAllInCorrectOrder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllReceiversWithTransactionStatusShouldThrowExceptionIfSuchStatusNotPresent() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void getAllReceiversWithTransactionStatusShouldReturnOnlyReceiversWithGivenStatus() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);

        List<String> receivers = (List<String>) this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        boolean containsAllReceivers = true;
        for (int i = 1; i < SENDERS_AND_RECEIVERS.length; i += 2) {
            if (!receivers.contains(SENDERS_AND_RECEIVERS[i])) {
                containsAllReceivers = false;
                break;
            }
        }
        assertTrue(containsAllReceivers);
    }

    @Test
    public void getAllReceiversWithTransactionStatusShouldReturnOrderedByDescendingReceivers() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        List<String> orderedReceivers = (List<String>) this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);

        boolean containsAllInCorrectOrder = orderedReceivers.get(0).equals(SENDERS_AND_RECEIVERS[2])
                && orderedReceivers.get(1).equals(SENDERS_AND_RECEIVERS[0]);
        assertTrue(containsAllInCorrectOrder);
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldReturnEmptyCollectionForEmptyChainBlock() {
        assertFalse(this.chainblock.getAllOrderedByAmountDescendingThenById().iterator().hasNext());
    }

    @Test
    public void getAllOrderedByAmountDescendingThenByIdShouldWorkCorrectly() {
        this.addTransactionsWithDifferentStatusesAndAmounts(this.chainblock);
        Iterator<Transaction> orderedByAmountTransactionsIterator = this.chainblock.getAllOrderedByAmountDescendingThenById().iterator();
        boolean areTransactionsOrdered = true;

        Transaction currentTransaction = orderedByAmountTransactionsIterator.next();
        double currentBiggestAmount = currentTransaction.getAmount();
        int currentLowestId = currentTransaction.getId();

        while (orderedByAmountTransactionsIterator.hasNext()) {
            currentTransaction = orderedByAmountTransactionsIterator.next();
            double currentAmountValue = currentTransaction.getAmount();
            int currentId = currentTransaction.getId();
            if (currentAmountValue > currentBiggestAmount) {
                areTransactionsOrdered = false;
                break;
            } else if (currentAmountValue == currentBiggestAmount && currentLowestId > currentId) {
                areTransactionsOrdered = false;
                break;
            }
            currentBiggestAmount = currentAmountValue;
            currentLowestId = currentId;
        }
        assertTrue(areTransactionsOrdered);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderOrderedByAmountDescendingShouldThrowExceptionIfNoSuchSenderPresent() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        this.chainblock.getBySenderOrderedByAmountDescending("Another Sender");
    }

    @Test
    public void getBySenderOrderedByAmountDescendingShouldReturnOnlyTransactionsWithGivenSender() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        String expectedSender = SENDERS_AND_RECEIVERS[0];
        Iterator<Transaction> transactionsWithGivenSenderIterator = this.chainblock.getBySenderOrderedByAmountDescending(expectedSender).iterator();
        boolean areTransactionsSenderCorrect = true;
        while (transactionsWithGivenSenderIterator.hasNext()) {
            String currentSender = transactionsWithGivenSenderIterator.next().getSender();
            if (!currentSender.equals(expectedSender)) {
                areTransactionsSenderCorrect = false;
                break;
            }
        }
        assertTrue(areTransactionsSenderCorrect);
    }

    @Test
    public void getBySenderOrderedByAmountDescendingShouldReturnOnlyOrderedByAmountTransactionsWithGivenSender() {
        this.addTransactionsWithTwoDifferentSendersAndReceivers(this.chainblock);
        List<Transaction> transactionsWithGivenSenderOrdered = (List<Transaction>) this.chainblock.getBySenderOrderedByAmountDescending(SENDERS_AND_RECEIVERS[0]);
        boolean containsAllInCorrectOrder = transactionsWithGivenSenderOrdered.get(0).getAmount() == AMOUNTS[2]
                && transactionsWithGivenSenderOrdered.get(1).getAmount() == AMOUNTS[0];

        assertTrue(containsAllInCorrectOrder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverOrderedByAmountThenByIdShouldThrowExceptionIfNoSuchReceiverPresent() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.getByReceiverOrderedByAmountThenById("Other Receiver");
    }

    @Test
    public void getByReceiverOrderedByAmountThenByIdShouldReturnOnlyTransactionsWithCorrectReceiver() {
        this.addTransactionsWithTwoDifferentSendersAndReceivers(this.chainblock);
        String expectedReceiver = SENDERS_AND_RECEIVERS[0];
        Iterable<Transaction> transactionsWithGivenReceiver = this.chainblock.getByReceiverOrderedByAmountThenById(expectedReceiver);
        boolean areTransactionsReceiverCorrect = true;
        for (Transaction transaction : transactionsWithGivenReceiver) {
            String currentReceiver = transaction.getReceiver();
            if (!expectedReceiver.equals(currentReceiver)) {
                areTransactionsReceiverCorrect = false;
                break;
            }
        }
        assertTrue(areTransactionsReceiverCorrect);
    }

    @Test
    public void getByReceiverOrderedByAmountThenByIdShouldReturnOrderedCollectionWithTransaction() {
        this.addTransactionsWithTwoDifferentSendersAndReceivers(this.chainblock);
        String expectedReceiver = SENDERS_AND_RECEIVERS[1];
        int newTransactionId = 15;
        this.chainblock.add(new TransactionImpl(
                newTransactionId, DEFAULT_TRANSACTION_STATUS, DEFAULT_SENDER, expectedReceiver, AMOUNTS[3]
        ));
        List<Transaction> orderedTransactionByAmountAndId = (List<Transaction>) this.chainblock.getByReceiverOrderedByAmountThenById(expectedReceiver);

        boolean areTransactionsOrderedCorrectly =
                orderedTransactionByAmountAndId.get(0).getAmount() == AMOUNTS[3] & orderedTransactionByAmountAndId.get(0).getId() < newTransactionId
                && orderedTransactionByAmountAndId.get(1).getAmount() == AMOUNTS[3] && orderedTransactionByAmountAndId.get(1).getId() == newTransactionId
                && orderedTransactionByAmountAndId.get(2).getAmount() == AMOUNTS[1];

        assertTrue(areTransactionsOrderedCorrectly);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnEmptyCollectionIfGivenTransactionStatusNotPresent() {
        this.chainblock.add(this.defaultTransaction);
        this.chainblock.add(this.secondTransaction);
        this.chainblock.add(this.thirdTransaction);
        Iterable<Transaction> emptyCollection = this.chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.FAILED, DEFAULT_AMOUNT);
        assertFalse(emptyCollection.iterator().hasNext());
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnEmptyCollectionIfTransactionsAmountsAreGreaterThanGiven() {
        this.chainblock.add(this.defaultTransaction);
        Iterable<Transaction> emptyCollection = this.chainblock.getByTransactionStatusAndMaximumAmount(DEFAULT_TRANSACTION_STATUS, DEFAULT_AMOUNT - 0.1);
        assertFalse(emptyCollection.iterator().hasNext());
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnOnlyTransactionsWithAmountLessOrEqualsToGivenAmount() {
        this.addTransactionsForGetByStatusAndMaxAmount(this.chainblock);
        double amountUpperBound = DEFAULT_AMOUNT + 1.4;
        Iterable<Transaction> transactionsWithGivenStatusAndAmountInRange = this.chainblock.getByTransactionStatusAndMaximumAmount(DEFAULT_TRANSACTION_STATUS, amountUpperBound);

        boolean areTransactionCorrect = true;
        for (Transaction transaction : transactionsWithGivenStatusAndAmountInRange) {
            if (transaction.getAmount() > amountUpperBound) {
                areTransactionCorrect = false;
                break;
            }
        }
        assertTrue(areTransactionCorrect);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnTransactionWithGivenStatusIfAmountIsGreaterThanTransactionsAmounts() {
        this.addTransactionsWithDifferentStatusesAndAmounts(this.chainblock);
        TransactionStatus expectedStatus = DEFAULT_TRANSACTION_STATUS;
        Iterable<Transaction> transactionsWithGivenStatus = this.chainblock.getByTransactionStatusAndMaximumAmount(expectedStatus, DEFAULT_AMOUNT * 13);

        assertTrue(transactionsWithGivenStatus.iterator().hasNext());
        boolean areAllTransactionsCorrect = true;
        for (Transaction transaction : transactionsWithGivenStatus) {
            if (!expectedStatus.equals(transaction.getTransactionStatus())) {
                areAllTransactionsCorrect = false;
                break;
            }
        }
        assertTrue(areAllTransactionsCorrect);
    }

    @Test
    public void getByTransactionStatusAndMaximumAmountShouldReturnOrderedCollectionOfTransactions() {
        this.addTransactionsForGetByStatusAndMaxAmount(this.chainblock);
        List<Transaction> orderedTransaction = (List<Transaction>) this.chainblock.getByTransactionStatusAndMaximumAmount(DEFAULT_TRANSACTION_STATUS, AMOUNT_UPPER_BOUND);
        boolean areOrdered = orderedTransaction.get(0).getAmount() == AMOUNT_UPPER_BOUND
                && orderedTransaction.get(1).getAmount() == AMOUNT_UPPER_BOUND
                && orderedTransaction.get(2).getAmount() == DEFAULT_AMOUNT;
        assertTrue(areOrdered);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderAndMinimumAmountDescendingShouldThrowExceptionIfNoTransactionWithGivenSenderIsFound() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        this.chainblock.getBySenderAndMinimumAmountDescending(DEFAULT_SENDER, DEFAULT_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBySenderAndMinimumAmountDescendingShouldThrowExceptionForAmountGreaterThanAllAmountTransactions() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        this.chainblock.getBySenderAndMinimumAmountDescending(SENDERS_AND_RECEIVERS[0], AMOUNTS[3] + 0.1);
    }

    @Test
    public void getBySenderAndMinimumAmountDescendingShouldReturnAllTransactionsWithGivenSenderAndAmountInRange() {
        this.addTransactionsForGetBySenderAndMinimumAmountDescending(this.chainblock, AMOUNT_LOWER_BOUND);
        Iterable<Transaction> returnedCollectionWithTransactions = this.chainblock.getBySenderAndMinimumAmountDescending(SENDERS_AND_RECEIVERS[0], AMOUNT_LOWER_BOUND);
        for (Transaction transaction : returnedCollectionWithTransactions) {
            assertTrue(transaction.getSender().equals(SENDERS_AND_RECEIVERS[0]) && transaction.getAmount() > AMOUNT_LOWER_BOUND);
        }
    }

    @Test
    public void getBySenderAndMinimumAmountDescendingShouldReturnOrderedCollectionByAmountDesc() {
        this.addTransactionsForGetBySenderAndMinimumAmountDescending(this.chainblock, AMOUNT_LOWER_BOUND);
        List<Transaction> orderedCollectionWithTransactions = (List<Transaction>) this.chainblock.getBySenderAndMinimumAmountDescending(SENDERS_AND_RECEIVERS[0], AMOUNT_LOWER_BOUND);
        boolean areTransactionOrderedCorrectly = orderedCollectionWithTransactions.get(0).getAmount() == AMOUNTS[2]
                && orderedCollectionWithTransactions.get(1).getAmount() == AMOUNTS[0];
        assertTrue(areTransactionOrderedCorrectly);
    }

    @Test
    public void getAllInAmountRangeShouldReturnEmptyCollectionForNoMatches() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        Iterable<Transaction> transactionIterable = this.chainblock.getAllInAmountRange(AMOUNTS[1] + 0.01, AMOUNTS[2] - 0.01);
        assertFalse(transactionIterable.iterator().hasNext());
    }

    @Test
    public void getAllInAmountRangeShouldReturnAllTransactionsWithinRange() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        Iterable<Transaction> transactionsWithinARange = this.chainblock.getAllInAmountRange(AMOUNTS[1], AMOUNTS[2]);
        for (Transaction transaction : transactionsWithinARange) {
            assertTrue(transaction.getAmount() >= AMOUNTS[1] && transaction.getAmount() <= AMOUNTS[2]);
        }
    }

    @Test
    public void getAllInAmountRangeShouldReturnTransactionInRangeByInsertionOrder() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        List<Transaction> transactionsWithinARangeByInsertionOrder = (List<Transaction>) this.chainblock.getAllInAmountRange(AMOUNTS[1], AMOUNTS[2]);
        boolean areTransactionInCorrectOrder = transactionsWithinARangeByInsertionOrder.get(0).getId() == 1
                && transactionsWithinARangeByInsertionOrder.get(1).getId() == 2;
        assertTrue(areTransactionInCorrectOrder);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverAndAmountRangeShouldThrowExceptionIfGivenReceiverNotPresent() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        this.chainblock.getByReceiverAndAmountRange(DEFAULT_RECEIVER, AMOUNTS[0], AMOUNTS[3]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getByReceiverAndAmountRangeShouldThrowExceptionIfNoTransactionWithinRange() {
        this.addTransactionsWithDifferentSendersAndReceivers(this.chainblock);
        this.chainblock.getByReceiverAndAmountRange(SENDERS_AND_RECEIVERS[1], AMOUNTS[3] + 1, AMOUNTS[3] + 10);
    }

    @Test
    public void getByReceiverAndAmountRangeShouldReturnTransactionOnlyWithGivenReceiver() {
        this.addTransactionsForGetByReceiverAndAmountRange(this.chainblock);
        Iterable<Transaction> transactionsWithGivenReceiver = this.chainblock.getByReceiverAndAmountRange(SENDERS_AND_RECEIVERS[1], AMOUNTS[0], AMOUNTS[3]);
        for (Transaction transaction : transactionsWithGivenReceiver) {
            assertEquals(SENDERS_AND_RECEIVERS[1], transaction.getReceiver());
        }
    }

    @Test
    public void getByReceiverAndAmountRangeShouldReturnTransactionsOrderedDescendingByAmount() {
        this.addTransactionsForGetByReceiverAndAmountRange(this.chainblock);
        Iterable<Transaction> orderedTransactionIterator = this.chainblock.getByReceiverAndAmountRange(SENDERS_AND_RECEIVERS[1], AMOUNTS[0], AMOUNTS[3]);

        boolean areOrdered = true;
        double currentBiggestAmount = Double.MAX_VALUE;
        for (Transaction transaction : orderedTransactionIterator) {
            if (transaction.getAmount() > currentBiggestAmount) {
                areOrdered = false;
            }
            currentBiggestAmount = transaction.getAmount();
        }
        assertTrue(areOrdered);
    }

    @Test
    public void getByReceiverAndAmountRangeShouldReturnTransactionCorrectlyForSameAmounts() {
        this.addTransactionsForGetByReceiverAndAmountRange(this.chainblock);
        Iterable<Transaction> orderedCollection = this.chainblock.getByReceiverAndAmountRange(SENDERS_AND_RECEIVERS[1], AMOUNTS[1], AMOUNTS[3]);

        boolean areOrdered = true;
        double currentBiggestAmount = Double.MAX_VALUE;
        int previousId = -1;
        for (Transaction transaction : orderedCollection) {
            double currentAmount = transaction.getAmount();
            int currentId = transaction.getId();
            if (currentBiggestAmount == currentAmount) {
                if (previousId > currentId) {
                    areOrdered = false;
                }
            }
            currentBiggestAmount = currentAmount;
            previousId = currentId;
        }
        assertTrue(areOrdered);
    }

    private void addTransactionsForGetByReceiverAndAmountRange(Chainblock chainblock) {
        this.addTransactionsWithDifferentSendersAndReceivers(chainblock);
        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 10, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[2], SENDERS_AND_RECEIVERS[1], AMOUNTS[1])
        );
        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 11, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[3], SENDERS_AND_RECEIVERS[1], AMOUNTS[2] + 10)
        );
        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 5, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[3], SENDERS_AND_RECEIVERS[1], AMOUNTS[1])
        );
    }

    private void addTransactionsForGetBySenderAndMinimumAmountDescending(Chainblock chainblock, double lowerBoundAmount) {
        this.addTransactionsWithTwoDifferentSendersAndReceivers(chainblock);
        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 10, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[0], SENDERS_AND_RECEIVERS[1], lowerBoundAmount - 10)
        );
    }

    private void addTransactionsForGetByStatusAndMaxAmount(Chainblock chainblock) {
        chainblock.add(this.defaultTransaction);
        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 2, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[2], SENDERS_AND_RECEIVERS[1], AMOUNT_UPPER_BOUND)
        );
        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 1, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[0], SENDERS_AND_RECEIVERS[1], AMOUNT_UPPER_BOUND)
        );

        chainblock.add(
                new TransactionImpl(DEFAULT_ID + 3, DEFAULT_TRANSACTION_STATUS, SENDERS_AND_RECEIVERS[1], SENDERS_AND_RECEIVERS[2], DEFAULT_AMOUNT + AMOUNT_UPPER_BOUND)
        );
    }

    private void addTransactionsWithTwoDifferentSendersAndReceivers(Chainblock chainblock) {
        for (int i = 0; i < 4; i++) {
            String senderAndReceiver = i % 2 == 0 ? SENDERS_AND_RECEIVERS[0] : SENDERS_AND_RECEIVERS[1];
            chainblock.add(new TransactionImpl(
                    i,
                    DEFAULT_TRANSACTION_STATUS,
                    senderAndReceiver,
                    senderAndReceiver,
                    AMOUNTS[i]
            ));
        }
    }

    private void addTransactionsWithDifferentSendersAndReceivers(Chainblock chainblock) {
        for (int i = 0; i < 4; i++) {
            TransactionStatus status = i % 2 == 0 ? TransactionStatus.FAILED : DEFAULT_TRANSACTION_STATUS;
            chainblock.add(new TransactionImpl(
                    i,
                    status,
                    SENDERS_AND_RECEIVERS[i],
                    SENDERS_AND_RECEIVERS[i],
                    AMOUNTS[i]
            ));
        }
    }

    private void addTransactionsWithDifferentStatusesAndAmounts(Chainblock chainblock) {
        double amount = DEFAULT_AMOUNT;
        for (int id = 0; id < 19; id++) {
            chainblock.add(new TransactionImpl(
                    id,
                    ALL_STATUSES[id % ALL_STATUSES.length],
                    DEFAULT_SENDER,
                    DEFAULT_RECEIVER,
                    amount
            ));
            if (id % 4 == 0) {
                amount = DEFAULT_AMOUNT;
            } else {
                amount *= 2;
            }
        }
    }
}