package bookShop;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String author, String title, double price) {
        this.setAuthor(author);
        this.setTitle(title);
        this.setPrice(price);
    }

    private void setTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }
        this.title = title;
    }

    private String getTitle() {
        return this.title;
    }

    private void setAuthor(String author) {
        String[] authorTokens = author.split("\\s+");
        if (authorTokens.length == 2 && Character.isDigit(authorTokens[1].charAt(0))) {
            throw new IllegalArgumentException("Author not valid!");
        }
        this.author = author;
    }

    private String getAuthor() {
        return this.author;
    }

    private void setPrice(double price) {
        this.validatePrice(price);
        this.price = price;
    }

    protected double getPrice() {
        return this.price;
    }

    private void validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price not valid!");
        }
    }

    @Override
    public String toString() {
        return String.format("Type: %s%n" +
                "Title: %s%n" +
                "Author: %s%n" +
                "Price: %.1f%n",
                this.getClass().getSimpleName(),
                this.getTitle(),
                this.getAuthor(),
                this.getPrice());
    }
}
