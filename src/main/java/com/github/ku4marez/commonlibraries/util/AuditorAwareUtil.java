package com.github.ku4marez.commonlibraries.util;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.function.Supplier;

public class AuditorAwareUtil implements AuditorAware<String> {

    private final Supplier<String> currentUserSupplier;

    public AuditorAwareUtil(Supplier<String> currentUserSupplier) {
        this.currentUserSupplier = currentUserSupplier;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(currentUserSupplier.get());
    }
}
