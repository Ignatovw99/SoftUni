package kayzer.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "watches")
public class Watch extends BaseEntity{

    private String id;

    private String name;

    private String imageUrl;

    private BigDecimal price;

    private String description;

    private long views;

    public Watch() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "views", nullable = false)
    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
