package com.demoexamen.demoexamen.host.controller;

import com.demoexamen.demoexamen.host.controller.data.OrderTotalDto;
import com.demoexamen.demoexamen.infrastructure.OrderRepository;
import com.demoexamen.demoexamen.infrastructure.projection.OrderTotalProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository repository;

    @GetMapping("/total")
    public String getOrdersTotal(Model model) {
        List<OrderTotalDto> dto = repository.getTotal().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        model.addAttribute("dto", dto);
        return "order-total";
    }

    private OrderTotalDto mapToDto(OrderTotalProjection projection) {
        return OrderTotalDto.builder()
                .orderId(projection.getOrderId())
                .counterpartyName(projection.getCounterpartyName())
                .productName(projection.getProductName())
                .count(projection.getCount())
                .price(projection.getPrice())
                .build();
    }
}
