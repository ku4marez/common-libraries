package com.github.ku4marez.commonlibraries.dto.request;

import lombok.Builder;

@Builder
public record RefreshTokenRequest(String refreshToken) {
}
