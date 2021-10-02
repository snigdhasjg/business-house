package com.joe.businesshouse.cell;

import com.joe.businesshouse.game.User;
import com.joe.businesshouse.visitor.CellVisitor;

public class Lottery implements Cell {
    private final int prizeMoney;
    private final int id;

    public Lottery(int id) {
        this.id = id;
        this.prizeMoney = 200;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    @Override
    public void accept(User user, CellVisitor cellVisitor) {
        cellVisitor.visit(user, this);
    }

    @Override
    public String toString() {
        return String.format("Lottery(%d)", id);
    }
}
