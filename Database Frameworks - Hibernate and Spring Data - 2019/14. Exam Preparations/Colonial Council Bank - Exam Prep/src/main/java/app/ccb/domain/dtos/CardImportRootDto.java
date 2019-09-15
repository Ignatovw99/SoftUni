package app.ccb.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportRootDto implements Serializable {

    @XmlElement(name = "card")
    private List<CardImportDto> cardImportDtos;

    public CardImportRootDto() {
    }

    public List<CardImportDto> getCardImportDtos() {
        return cardImportDtos;
    }

    public void setCardImportDtos(List<CardImportDto> cardImportDtos) {
        this.cardImportDtos = cardImportDtos;
    }
}
