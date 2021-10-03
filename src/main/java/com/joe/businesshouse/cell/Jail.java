package com.joe.businesshouse.cell;

import com.joe.businesshouse.game.User;
import com.joe.businesshouse.visitor.UserCellVisitor;

public class Jail implements Cell {
    private final int fineAmount;
    private final int id;

    public Jail(int id) {
        this.id = id;
        this.fineAmount = 150;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    @Override
    public void accept(User user, UserCellVisitor userCellVisitor) {
        userCellVisitor.visit(user, this);
    }

    @Override
    public String toString() {
        return String.format("Jail(%d)", id);
    }
}
