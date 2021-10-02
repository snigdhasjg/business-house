package com.joe.businesshouse.bank;

import com.joe.businesshouse.game.User;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class Bank {
    private int amountThatBankHas; //TODO create a bank user
    private final Map<User, AccountBook> userDB;

    public Bank(int amountThatBankHas) {
        this.amountThatBankHas = amountThatBankHas;
        this.userDB = new HashMap<>();
    }

    public void registerUser(User user, int openingAmount) {
        AccountBook accountBook = new AccountBook();
        accountBook.addLiquidCash(openingAmount);
        userDB.put(user, accountBook);
    }

    public void buyAsset(User user, int amount) {
        AccountBook accountBook = userDB.get(user);
        if (isNull(accountBook)) {
            throw new IllegalArgumentException("User doesn't have account in the bank");
        }
        //TODO check for negative balance
        amountThatBankHas += amount;
        accountBook.addLiquidCash(-amount);
        accountBook.addAssetValue(amount);
    }

    public void transferMoney(User user, int amount) {
        AccountBook accountBook = userDB.get(user);
        if (isNull(accountBook)) {
            throw new IllegalArgumentException("User doesn't have account in the bank");
        }
        //TODO check for negative balance
        amountThatBankHas -= amount;
        accountBook.addLiquidCash(amount);
    }

    public void transferMoney(User from, User to, int amount) {
        AccountBook accountBookOfFrom = userDB.get(from);
        AccountBook accountBookOfTo = userDB.get(to);
        if (isNull(accountBookOfFrom) || isNull(accountBookOfTo)) {
            throw new IllegalArgumentException("User doesn't have account in the bank");
        }
        //TODO check for negative balance
        accountBookOfFrom.addLiquidCash(-amount);
        accountBookOfTo.addLiquidCash(amount);
    }

    public User richestPlayer() {
        return userDB.entrySet()
                .stream()
                .max(Comparator.comparingInt(o -> o.getValue().totalValue()))
                .orElseThrow()
                .getKey();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bank -> Cash: ").append(amountThatBankHas).append("\n");

        userDB.forEach((user, accountBook) -> sb.append(user).append(" -> ").append(accountBook).append("\n"));

        return sb.toString();
    }
}
