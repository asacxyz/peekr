package com.asacxyz.peekr.repository.filter;

import java.util.List;

import com.asacxyz.peekr.model.Filter;

public interface FilterOperatorStrategy {
    String getSQL(Filter filter);

    List<Object> getParameters(Filter filter);
}
