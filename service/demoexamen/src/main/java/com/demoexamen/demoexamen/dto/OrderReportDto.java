package com.demoexamen.demoexamen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderReportDto {
    private Integer counterpartyNumber = null;
    private String counterpartyName = "";
    private Integer orderNumber = 0;
    private String positionName = "";
    private Integer positionCount = 0;
    private Double positionPrice = 0.0;
    private Double positionSum = null;
    private Double orderSum = null;
}
