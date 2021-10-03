package com.joe.businesshouse.cell;

import java.util.Optional;

public enum HotelType {
    silver(200, 50),
    gold(300, 150),
    platinum(500, 300);

    private final int value;
    private final int rent;

    HotelType(int value, int rent) {
        this.value = value;
        this.rent = rent;
    }

    public Optional<HotelType> next() {
        switch (this) {
            case silver:
                return Optional.of(gold);
            case gold:
                return Optional.of(platinum);
            default:
                return Optional.empty();
        }
    }

    public int getValue() {
        return value;
    }

    public int getRent() {
        return rent;
    }

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
