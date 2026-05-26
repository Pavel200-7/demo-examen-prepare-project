package com.demoexamen.demoexamen.mapper;

import com.demoexamen.demoexamen.dto.OrderReportDto;
import com.demoexamen.demoexamen.infrastructure.data.OrderReportProjection;
import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class OrderReportMapper {

    public List<OrderReportDto> mapToDto(List<OrderReportProjection> projections) {
        if (projections == null || projections.isEmpty()) {
            return Collections.emptyList();
        }

        List<OrderReportDto> res = new ArrayList<>();

        int counterpartyNumber = 1;
        String currentCounterparty = projections.get(0).getCounterpartyName();
        String currentOrder = projections.get(0).getOrderNumber().toString();
        double currentOrderSum = 0;

        for (int i = 0; i < projections.size(); i++) {
            OrderReportProjection p = projections.get(i);

            if (!currentCounterparty.equals(p.getCounterpartyName())) {
                counterpartyNumber++;
                currentCounterparty = p.getCounterpartyName();
                currentOrderSum = 0;
            }

            boolean newOrder = !currentOrder.equals(p.getOrderNumber().toString());
            if (newOrder) {
                currentOrder = p.getOrderNumber().toString();
                currentOrderSum = 0;
            }

            double positionSum = p.getPositionCount() * p.getPositionPrice();
            currentOrderSum += positionSum;

            OrderReportDto dto = OrderReportDto.builder()
                    .counterpartyNumber(counterpartyNumber)
                    .counterpartyName(p.getCounterpartyName())
                    .orderNumber(p.getOrderNumber())
                    .positionName(p.getPositionName())
                    .positionCount(p.getPositionCount())
                    .positionPrice(p.getPositionPrice())
                    .positionSum(positionSum)
                    .orderSum(currentOrderSum)
                    .build();

            res.add(dto);
        }

        return res;
    }
}
