package com.asacxyz.peekr.repository.filter;

import java.util.List;

import com.asacxyz.peekr.model.Filter;

public class EqualsFilterOperatorStrategy implements FilterOperatorStrategy {
    @Override
    public String getSQL(Filter filter) {
        return filter.name() + " = ?";
    }

    @Override
    public List<Object> getParameters(Filter filter) {
        FilterCaster caster = new FilterCaster(filter);
        return caster.getCastedValues();
    }
}
