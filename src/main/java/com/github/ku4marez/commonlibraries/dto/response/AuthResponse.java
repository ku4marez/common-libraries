package com.github.ku4marez.commonlibraries.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(String accessToken, String refreshToken) {
}
