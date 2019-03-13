package cardsuit;

public class Card {
    private CardRank cardRank;
    private CardSuit cardSuit;
    private int power;

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
        this.setPower();
    }

    private void setPower() {
        this.power = this.cardRank.getValue() + this.cardSuit.getValue();
    }

    private int getPower() {
        return this.power;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.cardRank, this.cardSuit, this.getPower());
    }
}
