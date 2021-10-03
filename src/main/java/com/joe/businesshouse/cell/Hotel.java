package com.joe.businesshouse.cell;

import com.joe.businesshouse.game.User;
import com.joe.businesshouse.visitor.UserCellVisitor;

import java.util.concurrent.atomic.AtomicBoolean;

public class Hotel implements Cell {
    private final int id;
    private HotelType hotelType = HotelType.silver;
    private User owner = null;

    public Hotel(int id) {
        this.id = id;
    }

    @Override
    public void accept(User user, UserCellVisitor userCellVisitor) {
        userCellVisitor.visit(user, this);
    }

    public User getOwner() {
        return owner;
    }

    public int getHotelValue() {
        return hotelType.getValue();
    }

    public int getHotelRent() {
        return hotelType.getRent();
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean upgrade() {
        AtomicBoolean isUpgradable = new AtomicBoolean(false);
        hotelType.next().ifPresent(e -> {
            hotelType = e;
            isUpgradable.set(true);
        });
        return isUpgradable.get();
    }

    @Override
    public String toString() {
        return String.format("%s Hotel(%d)", hotelType, id);
    }
}
