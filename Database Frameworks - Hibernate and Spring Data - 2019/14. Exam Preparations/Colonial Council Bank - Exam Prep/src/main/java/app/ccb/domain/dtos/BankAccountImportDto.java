package app.ccb.domain.dtos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountImportDto implements Serializable {

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlAttribute(name = "client")
    private String clientFullName;

    @XmlElement(name = "balance")
    private BigDecimal balance;

    public BankAccountImportDto() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
