package com.joe.businesshouse;

import com.joe.businesshouse.bank.Bank;
import com.joe.businesshouse.cell.Cell;
import com.joe.businesshouse.cell.Empty;
import com.joe.businesshouse.cell.Hotel;
import com.joe.businesshouse.cell.Jail;
import com.joe.businesshouse.cell.Lottery;
import com.joe.businesshouse.game.Game;
import com.joe.businesshouse.game.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(5000);
        Game game = new Game(bank,
                getListOfDiceInput("2,2,1, 4,4,2, 4,4,2, 2,2,1, 4,4,2, 4,4,2, 2,2,1"),
                createAndRegisterUser(3, bank),
                initiateBoard("J,H,L,H,E,L,H,L,H,J"));
        game.startGame();
        System.out.printf("Winner is: %s%n", game.getWinner());
    }

    private static List<Integer> getListOfDiceInput(String diceDiagram) {
        return Arrays.stream(diceDiagram.split(","))
                .map(e -> Integer.parseInt(e.strip()))
                .collect(Collectors.toList());
    }

    private static List<Cell> initiateBoard(String cellDiagram) {
        AtomicInteger i = new AtomicInteger(1);

        Map<String, Supplier<Cell>> cellMap = new HashMap<>();
        cellMap.put("J", () -> new Jail(i.getAndIncrement()));
        cellMap.put("L", () -> new Lottery(i.getAndIncrement()));
        cellMap.put("H", () -> new Hotel(i.getAndIncrement()));
        cellMap.put("E", () -> new Empty(i.getAndIncrement()));

        return Arrays.stream(cellDiagram.split(","))
                .map(e -> cellMap.get(e.strip()).get())
                .collect(Collectors.toList());
    }

    private static List<User> createAndRegisterUser(int noOfPlayer, Bank bank) {
        return IntStream.range(1, noOfPlayer + 1)
                .mapToObj(e -> {
                    User user = new User(e);
                    bank.registerUser(user, 1000);
                    return user;
                })
                .collect(Collectors.toList());
    }
}
