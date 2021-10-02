package com.joe.businesshouse.cell;

import com.joe.businesshouse.game.User;
import com.joe.businesshouse.visitor.CellVisitor;

public interface Cell {

    void accept(User user, CellVisitor cellVisitor);
}
