package com.joe.businesshouse;

import com.joe.businesshouse.game.GameSetup;

public class Main {
    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup(3, "J,H,L,H,E,L,H,L,H,J",
                "2,2,1, 4,4,2, 4,4,2, 2,2,1, 4,4,2, 4,4,2, 2,2,1");
        gameSetup.startGame();
        System.out.println(gameSetup.getWinner());
    }
}
