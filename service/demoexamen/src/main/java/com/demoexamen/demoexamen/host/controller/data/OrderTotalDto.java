package com.demoexamen.demoexamen.host.controller.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderTotalDto {
    private Long orderId;
    private String counterpartyName;
    private String productName;
    private Integer count;
    private Double price;
}
