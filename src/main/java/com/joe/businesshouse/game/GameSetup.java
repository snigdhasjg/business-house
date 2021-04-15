package com.joe.businesshouse.game;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.cell.Cell;
import com.joe.businesshouse.cell.Empty;
import com.joe.businesshouse.cell.Hotel;
import com.joe.businesshouse.cell.Jail;
import com.joe.businesshouse.cell.Lottery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameSetup {
    private final Bank bank = new Bank(5000);
    private final List<Integer> listOfDiceInput;
    private final List<User> users;
    private final List<Cell> board;
    private int currentPayer;

    public GameSetup(int numberOfPlayer, String cellMap, String diceMap) {
        this.users = createAndRegisterUser(numberOfPlayer);
        this.board = initiateBoard(cellMap);
        this.listOfDiceInput = getListOfDiceInput(diceMap);
        this.currentPayer = 0;

    }

    public void startGame() {
        System.out.println(bank);
        for (int each : listOfDiceInput) {
            User user = users.get(currentPayer);

            int boardIndex = (user.getBoardIdx() + each) % board.size();
            Cell cell = board.get(boardIndex);
            cell.visit(user);
            user.setBoardIdx(boardIndex);

            System.out.printf("%s visited by %s\n", cell, user);
            System.out.println(bank);

            currentPayer = (currentPayer + 1) % users.size();
        }
    }

    public User getWinner() {
        return bank.userThatHasMaximumAmount();
    }

    private List<Integer> getListOfDiceInput(String diceDiagram) {
        return Arrays.stream(diceDiagram.split(","))
                .map(e -> Integer.parseInt(e.strip()))
                .collect(Collectors.toList());
    }

    private List<Cell> initiateBoard(String cellDiagram) {
        AtomicInteger i = new AtomicInteger(1);

        Map<String, Supplier<Cell>> cellMap = new HashMap<>();
        cellMap.put("J", () -> new Jail(i.getAndIncrement(), bank));
        cellMap.put("L", () -> new Lottery(i.getAndIncrement(), bank));
        cellMap.put("H", () -> new Hotel(i.getAndIncrement(), bank));
        cellMap.put("E", () -> new Empty(i.getAndIncrement(), bank));

        return Arrays.stream(cellDiagram.split(","))
                .map(e -> cellMap.get(e.strip()).get())
                .collect(Collectors.toList());
    }

    private List<User> createAndRegisterUser(int noOfPlayer) {
        return IntStream.range(1, noOfPlayer + 1)
                .mapToObj(e -> {
                    User user = new User(e);
                    bank.registerUser(user, 1000);
                    return user;
                })
                .collect(Collectors.toList());
    }
}
