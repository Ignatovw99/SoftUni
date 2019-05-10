import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Instock implements ProductStock {
    private List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.contains(product);
    }

    @Override
    public void add(Product product) {
        this.products.add(product);
    }

    @Override
    public void changeQuantity(String label, int quantity) {
        Product product = this.findByLabel(label);
        int newQuantity = product.getQuantity() + quantity;
        product.setQuantity(newQuantity);
    }

    /**
     * The index is one-based.
     * @param index (index - 1) is the product, which we want to return.
     * @return the n-th product by insertion order.
     */
    @Override
    public Product find(int index) {
        return this.products.get(index - 1);
    }

    @Override
    public Product findByLabel(String label) {
        return this.products
                .stream()
                .filter(product -> product.getLabel().equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        List<Product> result = new ArrayList<>();

        if (count <= 0 || count > this.getCount()) {
            return result;
        }

        this.products
                .stream()
                .sorted(Product::compareTo)
                .limit(count)
                .forEach(result::add);

        return result;
    }

    @Override
    public Iterable<Product> findAllInRange(double low, double high) {
        return this.getProductsInRangeOrderedDescendingByPrice(low, high)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.findAllInRange(price - 0.0000001, price);
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > this.getCount()) { throw new IllegalArgumentException(); }
        List<Product> products = new ArrayList<>();
        if (count <= 0) { return products; }
        products = this.getProductsInRangeOrderedDescendingByPrice(0, Double.MAX_VALUE)  //It will return all products in stock
                .limit(count)
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.products
                .stream()
                .filter(product -> product.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }

    private Stream<Product> getProductsInRangeOrderedDescendingByPrice(double low, double high) {
        return this.products
                .stream()
                .filter(product -> {
                    double productPrice = product.getPrice();
                    return productPrice > low && productPrice <= high;
                })
                .sorted((firstProduct, secondProduct) -> Double.compare(secondProduct.getPrice(), firstProduct.getPrice()));
    }
}
