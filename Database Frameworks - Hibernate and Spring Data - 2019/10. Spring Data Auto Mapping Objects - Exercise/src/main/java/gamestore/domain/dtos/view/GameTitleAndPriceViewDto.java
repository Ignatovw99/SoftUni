package gamestore.domain.dtos.view;

import gamestore.domain.validators.gametitle.GameTitle;
import gamestore.domain.validators.price.GamePrice;

import java.math.BigDecimal;

public class GameTitleAndPriceViewDto {

    private String title;

    private BigDecimal price;

    public GameTitleAndPriceViewDto() {
    }

    @GameTitle
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @GamePrice
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + this.getPrice();
    }
}
