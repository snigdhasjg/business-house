package com.joe.businesshouse.bank;

import com.joe.businesshouse.exception.UserNotFoundException;
import com.joe.businesshouse.game.User;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Bank {
    private int amountThatBankHas; //TODO create a bank user
    private final Map<User, AccountBook> userDB;

    public Bank(int amountThatBankHas) {
        this.amountThatBankHas = amountThatBankHas;
        this.userDB = new HashMap<>();
    }

    public void registerUser(User user, int openingAmount) {
        AccountBook accountBook = new AccountBook(openingAmount);
        userDB.put(user, accountBook);
    }

    public void buyAsset(User user, int amount) throws UserNotFoundException {
        AccountBook accountBook = findUserAccount(user);
        //TODO check for negative balance
        amountThatBankHas += amount;
        accountBook.addLiquidCash(-amount);
        accountBook.addAssetValue(amount);
    }

    public void transferMoney(User user, int amount) throws UserNotFoundException {
        AccountBook accountBook = findUserAccount(user);
        //TODO check for negative balance
        amountThatBankHas -= amount;
        accountBook.addLiquidCash(amount);
    }

    public void transferMoney(User from, User to, int amount) throws UserNotFoundException {
        AccountBook accountBookOfPayer = findUserAccount(from);
        AccountBook accountBookOfReceiver = findUserAccount(to);
        //TODO check for negative balance
        accountBookOfPayer.addLiquidCash(-amount);
        accountBookOfReceiver.addLiquidCash(amount);
    }

    private AccountBook findUserAccount(User user) throws UserNotFoundException {
        return Optional.ofNullable(userDB.get(user))
                .orElseThrow(() -> new UserNotFoundException(user));
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
        sb.append("Bank -> Cash: ").append(amountThatBankHas).append('\n');

        userDB.forEach((user, accountBook) -> sb.append(user).append(" -> ").append(accountBook).append('\n'));

        return sb.toString();
    }
}
