package com.demoexamen.demoexamen.host.controller;

import com.demoexamen.demoexamen.domain.entity.Counterparty;
import com.demoexamen.demoexamen.host.controller.data.CounterpartyDto;
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

    @GetMapping("/page")
    public String getPage(Model model) {
        return "counterparty-upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        if (file == null) {
            redirectAttributes.addFlashAttribute("message", "you should upload file");
            return "redirect:/counterparty/page";
        }

        try {
            String fileName = file.getOriginalFilename();
            if (fileName == null || !fileName.endsWith(".json")) {
                redirectAttributes.addFlashAttribute("message", "Uploading file must be json");
                return "redirect:/counterparty/page";
            }

            List<CounterpartyDto> counterpartyDto = mapper.readValue(
                    file.getInputStream(),
                    new TypeReference<List<CounterpartyDto>>() {}
            );

            List<Counterparty> counterparties = counterpartyDto.stream()
                    .map(this::mapToEntity)
                    .filter(c -> !repository.existsByName(c.getName()))
                    .map(repository::save)
                    .collect(Collectors.toList());

            int created = counterparties.size();
            int skipped = counterpartyDto.size() - created;

            redirectAttributes.addFlashAttribute("message",
                    String.format("Создано %d, пропущено (уже есть) %d", created, skipped));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/counterparty/page";
        }
        return "redirect:/counterparty/page";
    }

    private Counterparty mapToEntity(CounterpartyDto dto) {
        return Counterparty.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }
}
