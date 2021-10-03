package com.joe.businesshouse.visitor;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.cell.Cell;
import com.joe.businesshouse.cell.Empty;
import com.joe.businesshouse.cell.Hotel;
import com.joe.businesshouse.cell.Jail;
import com.joe.businesshouse.cell.Lottery;
import com.joe.businesshouse.exception.UserNotFoundException;
import com.joe.businesshouse.game.User;

import static java.util.Objects.isNull;

public class BankUserCellVisitor implements UserCellVisitor {
    private final Bank bank;

    public BankUserCellVisitor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void visit(User user, Empty empty) {
        // nothing happens here
        logVisit(user, empty);
    }

    @Override
    public void visit(User user, Hotel hotel) {
        try {
            if (isNull(hotel.getOwner())) {
                hotel.setOwner(user);
                bank.buyAsset(user, hotel.getHotelValue());
            } else if (hotel.getOwner().equals(user)) {
                int preUpgradeValue = hotel.getHotelValue();
                if (hotel.upgrade()) {
                    bank.buyAsset(user, hotel.getHotelValue() - preUpgradeValue);
                }
            } else {
                bank.transferMoney(user, hotel.getOwner(), hotel.getHotelRent());
            }
            logVisit(user, hotel);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void visit(User user, Jail jail) {
        try {
            bank.transferMoney(user, -jail.getFineAmount());
            logVisit(user, jail);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void visit(User user, Lottery lottery) {
        try {
            bank.transferMoney(user, lottery.getPrizeMoney());
            logVisit(user, lottery);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void logVisit(User user, Cell cell) {
        System.out.printf("%s visited by %s\n", cell, user);
        System.out.println(bank);
    }
}
