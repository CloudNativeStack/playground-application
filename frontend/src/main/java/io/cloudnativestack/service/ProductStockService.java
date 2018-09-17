package io.cloudnativestack.service;

import io.cloudnativestack.client.ProductClientApi;
import io.cloudnativestack.client.StockClientApi;
import io.cloudnativestack.domain.ProductWithStock;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockService {

    @Autowired
    ProductClientApi productClientApi;

    @Autowired
    StockClientApi stockClientApi;

    public List<ProductWithStock> findAllProductsWithStock() {
        List<Product> products = productClientApi.findAllProduct();
        List<ProductWithStock> result = Lists.newArrayList();
        products.forEach(p -> result.add(buildProductStockView(p)));
        return result;
    }

    private ProductWithStock buildProductStockView(Product product) {
        ProductWithStock productWithStock = ProductWithStock.builder()
                .productRef(product.getRef())
                .productTitle(product.getTitle())
                .productDescription(product.getDescription())
                .build();

        Long productQuantity = stockClientApi.getProductQuantity(product.getRef());
        productWithStock.setQuantity(productQuantity);
        return productWithStock;
    }
}
