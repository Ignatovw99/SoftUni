package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class TeamImportJsonDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private PictureImportJsonDto picture;

    public TeamImportJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureImportJsonDto getPicture() {
        return picture;
    }

    public void setPicture(PictureImportJsonDto picture) {
        this.picture = picture;
    }
}
