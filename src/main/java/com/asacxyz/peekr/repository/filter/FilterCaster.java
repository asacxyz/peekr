package com.asacxyz.peekr.repository.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.asacxyz.peekr.model.Filter;

public class FilterCaster {
    private final Filter filter;

    public FilterCaster(Filter filter) {
        this.filter = filter;
    }

    public List<Object> getCastedValues() {
        return this.filter.values().stream()
                .map(v -> this.getCastedValue(v))
                .collect(Collectors.toList());
    }

    private Object getCastedValue(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return value;
        }
    }
}
