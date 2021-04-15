package com.joe.businesshouse.cell;

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

    public int getValue() {
        return value;
    }

    public int getRent() {
        return rent;
    }
}
