package com.joe.businesshouse.game;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.cell.Cell;
import com.joe.businesshouse.visitor.BankUserCellVisitor;
import com.joe.businesshouse.visitor.UserCellVisitor;

import java.util.List;

public class Game {
    private final Bank bank;
    private final List<Integer> dices;
    private final List<User> users;
    private final List<Cell> board;
    private int currentPayer;

    public Game(Bank bank, List<Integer> dices, List<User> users, List<Cell> board) {
        this.bank = bank;
        this.dices = dices;
        this.users = users;
        this.board = board;
        this.currentPayer = 0;
    }

    public void startGame() {
        UserCellVisitor userCellVisitor = new BankUserCellVisitor(bank);
        System.out.println(bank);
        for (int each : dices) {
            User user = users.get(currentPayer);

            int boardIndex = (user.getBoardIdx() + each) % board.size();
            Cell cell = board.get(boardIndex);
            cell.accept(user, userCellVisitor);
            user.setBoardIdx(boardIndex);

            currentPayer = (currentPayer + 1) % users.size();
        }
    }

    public User getWinner() {
        return bank.richestPlayer();
    }
}
