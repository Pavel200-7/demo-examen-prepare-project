package com.demoexamen.demoexamen.controller;

import com.demoexamen.demoexamen.domain.Counterparty;
import com.demoexamen.demoexamen.dto.CounterpartyDto;
import com.demoexamen.demoexamen.infrastructure.CounterpartyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/counterparty")
@RequiredArgsConstructor
@Slf4j
public class CounterpartyController {

    private final CounterpartyRepository repository;
    private final ObjectMapper mapper;

    @GetMapping
    public String page(Model model) {
        return "counterparty-import-page";
    }

    @PostMapping("/import")
    public String importData(@RequestParam MultipartFile file,
                             RedirectAttributes attributes) {
        if (file == null) {
            attributes.addFlashAttribute("message", "Файл должен быть выбран.");
            return "redirect:/counterparty";
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.toLowerCase().endsWith(".json")) {
            attributes.addFlashAttribute("message", "Файл должен формата json быть выбран.");
            return "redirect:/counterparty";
        }

        try {
            List<CounterpartyDto> dtos = mapper.readValue(file.getInputStream(),
                    new TypeReference<>() {});

            List<Counterparty> entity = dtos.stream()
                    .filter(dto -> !repository.existsByName(dto.getName()))
                    .map(this::toEntity)
                    .map(repository::save)
                    .collect(Collectors.toUnmodifiableList());

            int saved = entity.size();
            int skipped = dtos.size() - saved;

            String mes = "Было добавлено " + saved + "контрагентов и пропущено " + skipped;
            attributes.addFlashAttribute("message", mes);
        } catch (Exception e) {
            attributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/counterparty";
    }

    private Counterparty toEntity(CounterpartyDto dto) {
        return Counterparty.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getProne())
                .build();
    }

}
