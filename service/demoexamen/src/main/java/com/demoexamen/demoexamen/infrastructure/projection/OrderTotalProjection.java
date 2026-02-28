package com.demoexamen.demoexamen.infrastructure.projection;

public interface OrderTotalProjection {
    Long getOrderId();
    String getCounterpartyName();
    String getProductName();
    Integer getCount();
    Double getPrice();
}
