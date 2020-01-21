package shop;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;

public class ProductCatalog {

    private final Set<ProductMetadata> productMetadatas;

    public ProductCatalog(ProductMetadata... productMetadatas) {
        this.productMetadatas = new HashSet<>(asList(productMetadatas));
    }

    public Optional<ProductMetadata> findProductMetadata(String product) {
        return productMetadatas.stream()
                .filter(productMetadata -> productMetadata.matches(product))
                .findFirst();
    }
}
