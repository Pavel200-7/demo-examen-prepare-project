package com.demoexamen.demoexamen.host.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CounterpartyDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
}
