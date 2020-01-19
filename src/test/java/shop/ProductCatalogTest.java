package shop;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductCatalogTest {

    private final ProductCatalog productCatalog = new ProductCatalog(
            new ProductMetadata("apple", "apples", 0.10d),
            new ProductMetadata("bottle of milk", "bottles of milk", 1.30d),
            new ProductMetadata("tin of soup", "tins of soup", 0.65d)
    );

    @Test
    public void findsProductMetadataByUsingSingular() {
        assertThat(productCatalog.findProductMetadata("apple"), equalTo(Optional.of(new ProductMetadata("apple", "apples", 0.10d))));
    }

    @Test
    public void findsProductMetadataByUsingPlural() {
        assertThat(productCatalog.findProductMetadata("apples"), equalTo(Optional.of(new ProductMetadata("apple", "apples", 0.10d))));
    }

    @Test
    public void doesNotFindProductMetadata() {
        assertThat(productCatalog.findProductMetadata("missing-product"), equalTo(Optional.empty()));
    }
}