package com.joe.businesshouse.visitor;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.cell.Empty;
import com.joe.businesshouse.cell.Hotel;
import com.joe.businesshouse.cell.Jail;
import com.joe.businesshouse.cell.Lottery;
import com.joe.businesshouse.exception.UserNotFoundException;
import com.joe.businesshouse.game.User;

import static java.util.Objects.isNull;

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
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void visit(User user, Jail jail) {
        try {
            bank.transferMoney(user, -jail.getFineAmount());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void visit(User user, Lottery lottery) {
        try {
            bank.transferMoney(user, lottery.getPrizeMoney());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
