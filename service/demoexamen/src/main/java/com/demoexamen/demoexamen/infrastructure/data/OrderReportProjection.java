package com.demoexamen.demoexamen.infrastructure.data;

import java.util.UUID;

public interface OrderReportProjection {
    public String getCounterpartyName();
    public Integer getOrderNumber();
    public String getPositionName();
    public Integer getPositionCount();
    public Double getPositionPrice();
}
