package com.joe.businesshouse.cell;

import com.joe.businesshouse.game.User;
import com.joe.businesshouse.visitor.UserCellVisitor;

public class Empty implements Cell {

    private final int id;

    public Empty(int id) {
        this.id = id;
    }

    @Override
    public void accept(User user, UserCellVisitor userCellVisitor) {
        userCellVisitor.visit(user, this);
    }

    @Override
    public String toString() {
        return String.format("Empty(%d)", id);
    }
}
