package com.joe.businesshouse.cell;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.game.User;

public abstract class Cell {
    private final int id;
    private final Bank bank;

    public Cell(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    protected Bank getBank() {
        return bank;
    }

    public abstract void visit(User user);

    @Override
    public String toString() {
        return String.format("%s(%d)", this.getClass().getSimpleName(), id);
    }
}
