package gamestore.domain.dtos.binding;

import gamestore.domain.validators.description.GameDescription;
import gamestore.domain.validators.gametitle.GameTitle;
import gamestore.domain.validators.price.GamePrice;
import gamestore.domain.validators.size.GameSize;
import gamestore.domain.validators.thumbnailurl.ThumbnailURL;
import gamestore.domain.validators.trailer.GameTrailer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EditGameDto {

    private Long id;

    private String title;

    private String trailer;

    private String thumbnailURL;

    private Double size;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;

    public EditGameDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @GameTitle
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @GameTrailer
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @ThumbnailURL
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @GameSize
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @GamePrice
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @GameDescription
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
