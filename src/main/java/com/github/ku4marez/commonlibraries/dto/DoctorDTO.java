package com.github.ku4marez.commonlibraries.dto;

import lombok.Builder;

@Builder
public record DoctorDTO(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String specialty,
        String licenseNumber
) {}
