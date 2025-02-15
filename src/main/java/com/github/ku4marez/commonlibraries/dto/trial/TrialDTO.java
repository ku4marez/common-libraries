package com.github.ku4marez.commonlibraries.dto.trial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrialDTO {
    private String id;
    private String name;
    private Long enrolledPatients;
    private LocalDate startDate;
}
