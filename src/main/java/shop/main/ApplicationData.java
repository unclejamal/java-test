package shop.main;

import shop.ProductCatalog;
import shop.discount.DiscountingProcess;

public class ApplicationData {
    public final ProductCatalog productCatalog;
    public final DiscountingProcess discountingProcess;

    public ApplicationData(ProductCatalog productCatalog, DiscountingProcess discountingProcess) {
        this.productCatalog = productCatalog;
        this.discountingProcess = discountingProcess;
    }
}
