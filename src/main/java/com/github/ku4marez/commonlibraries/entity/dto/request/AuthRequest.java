package com.github.ku4marez.commonlibraries.entity.dto.request;

import lombok.Builder;

@Builder
public record AuthRequest(String email, String password) {
}
