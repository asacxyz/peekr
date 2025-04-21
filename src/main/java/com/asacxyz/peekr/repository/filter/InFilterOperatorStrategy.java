package com.asacxyz.peekr.repository.filter;

import java.util.List;
import java.util.stream.Collectors;

import com.asacxyz.peekr.model.Filter;

public class InFilterOperatorStrategy implements FilterOperatorStrategy {
    @Override
    public String getSQL(Filter filter) {
        String bindParameters = filter.values().stream()
                .map(t -> "?")
                .collect(Collectors.joining(","));

        return filter.name() + " IN (" + bindParameters + ") ";
    }

    @Override
    public List<Object> getParameters(Filter filter) {
        FilterCaster caster = new FilterCaster(filter);
        return caster.getCastedValues();
    }
}
