package com.joe.businesshouse.cell;

import com.joe.businesshouse.game.User;
import com.joe.businesshouse.visitor.UserCellVisitor;

public interface Cell {

    void accept(User user, UserCellVisitor userCellVisitor);
}
