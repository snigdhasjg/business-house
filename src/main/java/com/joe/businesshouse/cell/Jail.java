package com.joe.businesshouse.cell;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.game.User;

public class Jail extends Cell {
    public Jail(int id, Bank bank) {
        super(id, bank);
    }

    @Override
    public void visit(User user) {
        getBank().transferMoney(user, -150);
    }
}
