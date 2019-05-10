import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class InstockTests {
    private final String DEFAULT_PRODUCT_LABEL = "Label";
    private final double DEFAULT_PRODUCT_PRICE = 14.3;
    private final int DEFAULT_PRODUCT_QUANTITY = 200;

    private Product defaultProduct;
    private Instock stock;

    private Product secondProduct;
    private Product thirdProduct;
    private Product fourthProduct;

    @Before
    public void setUp() {
        this.defaultProduct = new Product(DEFAULT_PRODUCT_LABEL, DEFAULT_PRODUCT_PRICE, DEFAULT_PRODUCT_QUANTITY);
        this.stock = new Instock();
    }

    private void addMultipleProducts() {
        this.secondProduct = new Product(DEFAULT_PRODUCT_LABEL + "2", 10, 21);
        this.thirdProduct = new Product(DEFAULT_PRODUCT_LABEL + "3", 14, 212);
        this.fourthProduct = new Product(DEFAULT_PRODUCT_LABEL + "4", 21, 213);

        this.stock.add(this.secondProduct);
        this.stock.add(this.defaultProduct);
        this.stock.add(this.fourthProduct);
        this.stock.add(this.thirdProduct);
    }

    private void assertArrayAndIterable(Product[] products, Iterable<Product> iterable) {
        Iterator<Product> iterator = iterable.iterator();

        assertTrue(iterator.hasNext());
        int index = 0;
        while (iterator.hasNext()) {
            assertEquals(products[index++], iterator.next());
        }
    }

    @Test
    public void shouldAddProduct() {
        this.stock.add(this.defaultProduct);
        assertTrue(this.stock.contains(this.defaultProduct));
    }

    @Test
    public void countShouldNotBeZeroAfterProductAdd() {
        this.stock.add(this.defaultProduct);
        assertEquals(1, this.stock.getCount());
    }

    @Test
    public void countShouldBeEqualToProductsAdded() {
        this.stock.add(this.defaultProduct);
        this.stock.add(this.defaultProduct);
        this.stock.add(this.defaultProduct);
        assertEquals(3, this.stock.getCount());
    }

    @Test
    public void containsShouldReturnFalseIfProductNotContained() {
        assertFalse(this.stock.contains(this.defaultProduct));
    }

    @Test
    public void containsShouldReturnTrueIfProductLabelsMatch() {
        Product otherProduct = new Product(DEFAULT_PRODUCT_LABEL, 21.2 , 1);
        this.stock.add(this.defaultProduct);
        assertTrue(this.stock.contains(otherProduct));
    }

    @Test
    public void findShouldReturnCorrectProductByInsertionOrderWithSingleProduct() {
        this.stock.add(this.defaultProduct);
        Product actual = this.stock.find(1);
        assertEquals(this.defaultProduct, actual);
    }

    @Test
    public void findShouldReturnCorrectProductByInsertionOrderWithMultipleProducts() {
        this.addMultipleProducts();

        Product actual = this.stock.find(3);
        assertEquals(this.fourthProduct, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findShouldThrowExceptionWhenItCalledWithInvalidIndex() {
        this.stock.find(1);
    }

    @Test
    public void changeQuantityShouldChangeTheQuantityToExistingProduct() {
        this.stock.add(this.defaultProduct);
        this.stock.changeQuantity(DEFAULT_PRODUCT_LABEL, DEFAULT_PRODUCT_QUANTITY);
        int expected = 2 * DEFAULT_PRODUCT_QUANTITY;
        int actual = this.stock.find(1).getQuantity();
        assertEquals(expected, actual);
    }

    @Test
    public void changeQuantityShouldChangeTheQuantityToExistingProductMultipleTimes() {
        this.stock.add(this.defaultProduct);

        this.stock.changeQuantity(DEFAULT_PRODUCT_LABEL, DEFAULT_PRODUCT_QUANTITY);
        this.stock.changeQuantity(DEFAULT_PRODUCT_LABEL, DEFAULT_PRODUCT_QUANTITY);
        this.stock.changeQuantity(DEFAULT_PRODUCT_LABEL, DEFAULT_PRODUCT_QUANTITY);

        int expected = 4 * DEFAULT_PRODUCT_QUANTITY;
        int actual = this.stock.find(1).getQuantity();
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeQuantityShouldThrowExceptionIfProductIsNotPresent() {
        Product productNotPresentInStock = new Product(DEFAULT_PRODUCT_LABEL + "Other", 2, 3);
        this.stock.add(this.defaultProduct);
        this.stock.changeQuantity(productNotPresentInStock.getLabel(), DEFAULT_PRODUCT_QUANTITY);
    }

    @Test
    public void findByLabelShouldReturnTheProductWithGivenLabelIfExists() {
        this.stock.add(this.defaultProduct);
        assertEquals(this.defaultProduct, this.stock.findByLabel(this.defaultProduct.getLabel()));
    }

    @Test
    public void findByLabelShouldReturnTheCorrectProductForMultipleProducts() {
        this.addMultipleProducts();

        String expectedLabel = this.secondProduct.getLabel();
        assertEquals(this.secondProduct, this.stock.findByLabel(expectedLabel));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByLabelShouldThrowExceptionIfLabelDoesNotExist() {
        this.stock.findByLabel(DEFAULT_PRODUCT_LABEL);
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidResultForEmptyStock() {
        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(0);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollectionIfCountIsNegative() {
        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(-1);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnEmptyCollectionIfCountIsGreater() {
        this.addMultipleProducts();
        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(6);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstByAlphabeticalOrderShouldReturnValidCollectionIfCountIsInRange() {
        this.addMultipleProducts();
        Product[] sortedProductsByLabel = { this.defaultProduct, this.secondProduct, this.thirdProduct, this.fourthProduct };
        Iterable<Product> actual = this.stock.findFirstByAlphabeticalOrder(3);
        assertArrayAndIterable(sortedProductsByLabel, actual);
    }

    @Test
    public void findAllInPriceRangeShouldReturnEmptyCollectionForEmptyStock() {
        Iterable<Product> actual = this.stock.findAllInRange(14.2, 15.6);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllInPriceRangeShouldReturnEmptyCollectionForProductsNotInThatRange() {
        this.addMultipleProducts();
        Iterable<Product> actual = this.stock.findAllInRange(1.4, 3.6);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllInPriceRangeShouldReturnTheProductWhichHasPriceEqualsToUpperBound() {
        this.addMultipleProducts();
        Product[] productCorrespondingToDefaultPrice = {this.defaultProduct};
        Iterable<Product> actual = this.stock.findAllInRange(DEFAULT_PRODUCT_PRICE - 0.1, DEFAULT_PRODUCT_PRICE);
        assertArrayAndIterable(productCorrespondingToDefaultPrice, actual);
    }

    @Test
    public void findAllInPriceRangeShouldReturnCorrectProductsForGivenRange() {
        this.addMultipleProducts();
        Product[] productsInGivenPriceRangeInDescOrder = { this.fourthProduct, this.thirdProduct };
        Iterable<Product> actual = this.stock.findAllInRange(14.3, 21.0);
        assertArrayAndIterable(productsInGivenPriceRangeInDescOrder, actual);
    }

    @Test
    public void findAllByPriceShouldReturnEmptyCollectionForEmptyStock() {
        Iterable<Product> actual = this.stock.findAllByPrice(DEFAULT_PRODUCT_PRICE);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllByPriceShouldReturnEmptyCollectionForNoPriceMatch() {
        this.addMultipleProducts();
        Iterable<Product> actual = this.stock.findAllByPrice(1);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllByPriceShouldReturnAllProductsWithGivenPrice() {
        this.addMultipleProducts();
        Product otherProduct = new Product("Label5", DEFAULT_PRODUCT_PRICE, DEFAULT_PRODUCT_QUANTITY);
        this.stock.add(otherProduct);
        Product[] productsCorrespondingToDefaultPrice = { this.defaultProduct, otherProduct };
        Iterable<Product> actual = this.stock.findAllByPrice(DEFAULT_PRODUCT_PRICE);

        assertArrayAndIterable(productsCorrespondingToDefaultPrice, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsShouldThrowExceptionIfCountIsGreaterThanProducts() {
        this.addMultipleProducts();
        this.stock.findFirstMostExpensiveProducts(5);
    }

    @Test
    public void findFirstMostExpensiveProductsShouldReturnEmptyCollectionIfCountIsZero() {
        Iterable<Product> actual = this.stock.findFirstMostExpensiveProducts(0);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstMostExpensiveProductsShouldReturnEmptyCollectionIfCountIsNegative() {
        Iterable<Product> actual = this.stock.findFirstMostExpensiveProducts(-2);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findFirstMostExpensiveProductsShouldReturnProductsEqualToCount() {
        this.addMultipleProducts();
        Product[] topThreeMostExpensiveProducts = { this.fourthProduct, this.defaultProduct, this.thirdProduct };
        Iterable<Product> actual = this.stock.findFirstMostExpensiveProducts(3);
        assertArrayAndIterable(topThreeMostExpensiveProducts, actual);
    }

    @Test
    public void findFirstMostExpensiveProductsShouldReturnAllProductsIfItsCountIsEqualToInputCount() {
        this.addMultipleProducts();
        Product[] allProductsOrderedDescendingByPrice = { this.fourthProduct, this.defaultProduct, this.thirdProduct, this.secondProduct };
        Iterable<Product> actual = this.stock.findFirstMostExpensiveProducts(4); //There are four added products.
        assertArrayAndIterable(allProductsOrderedDescendingByPrice, actual);
    }

    @Test
    public void findAllByQuantityShouldReturnEmptyCollectionForEmptyStock() {
        Iterable<Product> actual = this.stock.findAllByQuantity(1);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllByQuantityShouldReturnEmptyCollectionIfNoProductWhitIdenticalQuantity() {
        this.addMultipleProducts();
        Iterable<Product> actual = this.stock.findAllByQuantity(1);
        assertFalse(actual.iterator().hasNext());
    }

    @Test
    public void findAllByQuantityShouldReturnProductsCorrespondingToInputQuantity() {
        this.addMultipleProducts();
        Product otherProduct = new Product("Label123", DEFAULT_PRODUCT_PRICE + 1, DEFAULT_PRODUCT_QUANTITY);
        this.stock.add(otherProduct);
        Product[] productsCorrespondingToQuantity = { this.defaultProduct, otherProduct };
        Iterable<Product> actual = this.stock.findAllByQuantity(DEFAULT_PRODUCT_QUANTITY);
        assertArrayAndIterable(productsCorrespondingToQuantity, actual);
    }

    @Test
    public void getIterableShouldWorkCorrectly() {
        this.addMultipleProducts();
        final int expectedProductsCount = this.stock.getCount();
        int currentProductIndex = 0;
        for (Product product : this.stock) {
            currentProductIndex++;
        }
        assertEquals(expectedProductsCount, currentProductIndex);
    }
}
