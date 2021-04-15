package com.joe.businesshouse.cell;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.game.User;

import static java.util.Objects.isNull;

public class Hotel extends Cell {
    private HotelType hotelType = HotelType.silver;
    private User owner = null;

    public Hotel(int id, Bank bank) {
        super(id, bank);
    }

    @Override
    public void visit(User user) {
        if (isNull(owner)) {
            owner = user;
            getBank().buyAsset(user, hotelType.getValue());
            return;
        }
        if (owner.equals(user)) {
            int preUpgradeValue = hotelType.getValue();
            if (upgrade()) {
                getBank().buyAsset(user, hotelType.getValue() - preUpgradeValue);
            }
            return;
        }
        getBank().transferMoney(user, owner, hotelType.getRent());
    }

    private boolean upgrade() {
        if (hotelType.equals(HotelType.silver)) {
            hotelType = HotelType.gold;
            return true;
        } else if (hotelType.equals(HotelType.gold)) {
            hotelType = HotelType.platinum;
            return true;
        }
        return false;
    }
}
