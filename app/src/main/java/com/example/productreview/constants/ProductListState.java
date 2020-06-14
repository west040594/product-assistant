package com.example.productreview.constants;

import java.util.stream.Stream;

public enum ProductListState {
    ALL("all"),
    ENERGY_DRINKS("energy-drinks"),
    BEER("beer"),
    JUICE("juice");

    private String name;

    ProductListState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductListState of(String name) {
        return Stream.of(values())
                .filter(state -> state.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(ALL);
    }
}
