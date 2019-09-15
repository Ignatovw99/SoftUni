package gamestore.domain.entities;

import gamestore.constants.ValidationConstraints;
import gamestore.domain.validators.description.GameDescription;
import gamestore.domain.validators.gametitle.GameTitle;
import gamestore.domain.validators.price.GamePrice;
import gamestore.domain.validators.size.GameSize;
import gamestore.domain.validators.thumbnailurl.ThumbnailURL;
import gamestore.domain.validators.trailer.GameTrailer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private String title;

    private String trailer;

    private String thumbnailURL;

    private Double size;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;

    public Game() {
    }

    @Column(
            name = "title",
            unique = true,
            nullable = false,
            length = ValidationConstraints.GAME_TITLE_MAX_LENGTH
    )
    @GameTitle
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(
            name = "trailer",
            unique = true,
            nullable = false,
            length = ValidationConstraints.GAME_TRAILER_DEFAULT_LENGTH
    )
    @GameTrailer
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Column(name = "thumbnail_url")
    @ThumbnailURL
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Column(
            name = "size",
            nullable = false,
            precision = 10,
            scale = 1
    )
    @GameSize
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Column(
            name = "price",
            nullable = false,
            precision = 10,
            scale = 2
    )
    @GamePrice
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description", nullable = false)
    @GameDescription
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return title.equals(game.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
