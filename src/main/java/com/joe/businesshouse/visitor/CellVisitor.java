package com.joe.businesshouse.visitor;

import com.joe.businesshouse.cell.Empty;
import com.joe.businesshouse.cell.Hotel;
import com.joe.businesshouse.cell.Jail;
import com.joe.businesshouse.cell.Lottery;
import com.joe.businesshouse.game.User;

public interface CellVisitor {
    void visit(User user, Empty empty);

    void visit(User user, Hotel hotel);

    void visit(User user, Jail jail);

    void visit(User user, Lottery lottery);
}
