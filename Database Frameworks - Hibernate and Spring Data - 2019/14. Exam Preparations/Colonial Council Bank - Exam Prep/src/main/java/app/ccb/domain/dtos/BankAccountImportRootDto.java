package app.ccb.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountImportRootDto implements Serializable {

    @XmlElement(name = "bank-account")
    private List<BankAccountImportDto> bankAccountImportDtos;

    public BankAccountImportRootDto() {
    }

    public List<BankAccountImportDto> getBankAccountImportDtos() {
        return bankAccountImportDtos;
    }

    public void setBankAccountImportDtos(List<BankAccountImportDto> bankAccountImportDtos) {
        this.bankAccountImportDtos = bankAccountImportDtos;
    }
}
