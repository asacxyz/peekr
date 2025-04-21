package com.asacxyz.peekr.model;

import java.util.List;

public record Filter(String name, FilterOperator operator, List<String> values) {
}
