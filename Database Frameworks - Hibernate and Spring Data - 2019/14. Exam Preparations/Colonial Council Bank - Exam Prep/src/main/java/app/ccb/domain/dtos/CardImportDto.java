package app.ccb.domain.dtos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportDto implements Serializable {

    @XmlElement(name = "card-number")
    private String cardNumber;

    @XmlAttribute(name = "status")
    private String cardStatus;

    @XmlAttribute(name = "account-number")
    private String bankAccountNumber;

    public CardImportDto() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}