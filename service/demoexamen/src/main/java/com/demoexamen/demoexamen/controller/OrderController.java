package com.demoexamen.demoexamen.controller;

import com.demoexamen.demoexamen.dto.OrderReportDto;
import com.demoexamen.demoexamen.infrastructure.OrderRepository;
import com.demoexamen.demoexamen.infrastructure.data.OrderReportProjection;
import com.demoexamen.demoexamen.mapper.OrderReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repository;
    private final OrderReportMapper mapper;

    @GetMapping
    public String page(Model model) {
        List<OrderReportProjection> reportProjections = repository.getInfo();
        List<OrderReportDto> reportDtos = mapper.mapToDto(reportProjections);
        model.addAttribute("rows", reportDtos);
        return "order-report-page.html";
    }

    @PostMapping("/report")
    public String getReport(Model model, @RequestParam String counterpartyName) {
        List<OrderReportProjection> reportProjections = repository.getInfoByCounterpartyName(counterpartyName);
        List<OrderReportDto> reportDtos = mapper.mapToDto(reportProjections);
        model.addAttribute("rows", reportDtos);
        return "order-report-page.html";
    }

}
