package softuni.exam.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamImportXmlDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "picture")
    private PictureImportXmlDto picture;

    public TeamImportXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureImportXmlDto getPicture() {
        return picture;
    }

    public void setPicture(PictureImportXmlDto picture) {
        this.picture = picture;
    }
}
