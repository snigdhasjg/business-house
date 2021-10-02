package com.joe.businesshouse.visitor;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.cell.Empty;
import com.joe.businesshouse.cell.Hotel;
import com.joe.businesshouse.cell.Jail;
import com.joe.businesshouse.cell.Lottery;
import com.joe.businesshouse.game.User;

public class BankCellVisitor implements CellVisitor {
    private final Bank bank;

    public BankCellVisitor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void visit(User user, Empty empty) {
        // nothing happens here
    }

    @Override
    public void visit(User user, Hotel hotel) {
        if (hotel.isNew()) {
            hotel.setOwner(user);
            bank.buyAsset(user, hotel.getHotelValue());
        } else if (hotel.isSameOwner(user)) {
            int preUpgradeValue = hotel.getHotelValue();
            if (hotel.upgrade()) {
                bank.buyAsset(user, hotel.getHotelValue() - preUpgradeValue);
            }
        } else {
            bank.transferMoney(user, hotel.getOwner(), hotel.getHotelRent());
        }
    }

    @Override
    public void visit(User user, Jail jail) {
        bank.transferMoney(user, -jail.getFineAmount());
    }

    @Override
    public void visit(User user, Lottery lottery) {
        bank.transferMoney(user, lottery.getPrizeMoney());
    }
}
