package io.cloudnativestack.client;

import feign.Param;
import feign.RequestLine;

public interface StockClientApi {

    @RequestLine("GET /stocks/{productRef}")
    Long getProductQuantity(@Param("productRef") String productRef);
}
