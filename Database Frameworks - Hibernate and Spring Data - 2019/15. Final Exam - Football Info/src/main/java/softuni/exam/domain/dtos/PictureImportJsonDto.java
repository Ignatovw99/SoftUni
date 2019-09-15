package softuni.exam.domain.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PictureImportJsonDto implements Serializable {

    @Expose
    private String url;

    public PictureImportJsonDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
