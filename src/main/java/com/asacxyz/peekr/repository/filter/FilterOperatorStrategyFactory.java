package com.asacxyz.peekr.repository.filter;

import java.util.Map;

import com.asacxyz.peekr.model.FilterOperator;

public class FilterOperatorStrategyFactory {
    private static final Map<FilterOperator, FilterOperatorStrategy> instances = Map.of(
            FilterOperator.EQUALS, new EqualsFilterOperatorStrategy(),
            FilterOperator.IN, new InFilterOperatorStrategy());

    public static final FilterOperatorStrategy getInstance(FilterOperator operator) {
        return FilterOperatorStrategyFactory.instances.get(operator);
    }
}
