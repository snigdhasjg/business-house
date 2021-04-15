package com.joe.businesshouse.cell;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.game.User;

public class Empty extends Cell {

    public Empty(int id, Bank bank) {
        super(id,bank);
    }

    @Override
    public void visit(User user) {
        // No action
    }
}
