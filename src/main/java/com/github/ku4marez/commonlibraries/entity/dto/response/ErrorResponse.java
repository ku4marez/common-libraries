package com.github.ku4marez.commonlibraries.entity.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponse(String errorCode, String message, int status) {
}
