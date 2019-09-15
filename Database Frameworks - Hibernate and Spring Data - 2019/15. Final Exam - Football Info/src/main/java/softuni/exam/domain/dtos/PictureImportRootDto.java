package softuni.exam.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureImportRootDto implements Serializable {

    @XmlElement(name = "picture")
    private List<PictureImportXmlDto> pictureImportXmlDtos;

    public PictureImportRootDto() {
    }

    public List<PictureImportXmlDto> getPictureImportXmlDtos() {
        return pictureImportXmlDtos;
    }

    public void setPictureImportXmlDtos(List<PictureImportXmlDto> pictureImportXmlDtos) {
        this.pictureImportXmlDtos = pictureImportXmlDtos;
    }
}
