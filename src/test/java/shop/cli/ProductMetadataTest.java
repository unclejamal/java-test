package shop.cli;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ProductMetadataTest {

    private ProductMetadata productMetadata = new ProductMetadata("apple", "apples", 0.10d);

    @Test
    public void matchesSingularName() {
        assertThat(productMetadata.matches("apple"), Matchers.is(true));
    }

    @Test
    public void matchesPluralName() {
        assertThat(productMetadata.matches("apples"), Matchers.is(true));
    }

    @Test
    public void doesNotMatchUnrelatedWord() {
        assertThat(productMetadata.matches("not-apple-so-should-fail-to-match"), Matchers.is(false));
    }
}