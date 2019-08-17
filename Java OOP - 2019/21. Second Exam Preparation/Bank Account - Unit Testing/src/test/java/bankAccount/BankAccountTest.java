package bankAccount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BankAccountTest {
    private static final String INITIAL_NAME = "Name";
    private static final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(123);
    private static final BigDecimal NEGATIVE_AMOUNT_OF_MONEY = BigDecimal.valueOf(-13);
    private static final String NAME_WITH_LENGTH_LESS_THAN_MINIMUM = "As";
    private static final String NAME_WITH_LENGTH_GREATER_THAN_MAX = "ASdeadsdecsedefesdecfewcwffdscsdewqw";
    private static final BigDecimal POSITIVE_AMOUNT_OF_MONEY = BigDecimal.valueOf(132);
    private static final BigDecimal MONEY_TO_WITHDRAW = BigDecimal.valueOf(14.3);
    private static final BigDecimal MONEY_MORE_THAN_INITIAL_BALANCE = BigDecimal.valueOf(143);

    private static final String EXPECTED_NAME = INITIAL_NAME;
    private static final BigDecimal EXPECTED_BALANCE = INITIAL_BALANCE;
    private static final BigDecimal EXPECTED_BALANCE_AFTER_DEPOSIT = INITIAL_BALANCE.add(POSITIVE_AMOUNT_OF_MONEY);
    private static final BigDecimal EXPECTED_BALANCE_AFTER_WITHDRAW = INITIAL_BALANCE.subtract(MONEY_TO_WITHDRAW);

    private BankAccount bankAccount;

    @Before
    public void setUp() {
        this.bankAccount = new BankAccount(INITIAL_NAME, INITIAL_BALANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingBankAccountWithNameLengthLessThanMinimumShouldThrowException() {
        new BankAccount(NAME_WITH_LENGTH_LESS_THAN_MINIMUM, INITIAL_BALANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingBankAccountWithNameLengthGreaterThanMaximumLimitShouldThrowException() {
        new BankAccount(NAME_WITH_LENGTH_GREATER_THAN_MAX, INITIAL_BALANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructingBankAccountWithNegativeBalanceShouldThrowException() {
        new BankAccount(INITIAL_NAME, NEGATIVE_AMOUNT_OF_MONEY);
    }

    @Test
    public void getNameShouldWorkCorrectly() {
        assertEquals(EXPECTED_NAME, this.bankAccount.getName());
    }

    @Test
    public void getBalanceShouldWorkCorrectly() {
        assertEquals(EXPECTED_BALANCE, this.bankAccount.getBalance());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void depositNegativeAmountOfMoneyShouldThrowException() {
        this.bankAccount.deposit(NEGATIVE_AMOUNT_OF_MONEY);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void depositNoMoneyShouldThrowException() {
        this.bankAccount.deposit(BigDecimal.ZERO);
    }

    @Test
    public void depositPositiveAmountOfMoneyShouldIncreaseBalance() {
        this.bankAccount.deposit(POSITIVE_AMOUNT_OF_MONEY);
        assertEquals(EXPECTED_BALANCE_AFTER_DEPOSIT, this.bankAccount.getBalance());
    }

    @Test
    public void withdrawLessAmountOfMoneyThanBalanceShouldWorkCorrectly() {
        this.bankAccount.withdraw(MONEY_TO_WITHDRAW);
        assertEquals(EXPECTED_BALANCE_AFTER_WITHDRAW, this.bankAccount.getBalance());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withdrawNegativeAmountOfMoneyShouldThrowException() {
        this.bankAccount.withdraw(NEGATIVE_AMOUNT_OF_MONEY);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void withdrawWhenWishedAmountOfMoneyIsGreaterThanBalanceShouldThrowException() {
        this.bankAccount.withdraw(MONEY_MORE_THAN_INITIAL_BALANCE);
    }

    @Test
    public void withdrawShouldReturnAmountOfWithdrawnMoney() {
        assertEquals(MONEY_TO_WITHDRAW, this.bankAccount.withdraw(MONEY_TO_WITHDRAW));
    }
}