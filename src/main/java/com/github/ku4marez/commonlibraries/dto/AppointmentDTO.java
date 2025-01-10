package com.github.ku4marez.commonlibraries.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppointmentDTO(
        String id,
        String doctorId,
        String patientId,
        LocalDateTime dateTime,
        String status,
        String reason
) {}
