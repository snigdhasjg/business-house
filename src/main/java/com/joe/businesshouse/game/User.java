package com.joe.businesshouse.game;

import java.util.Objects;

public class User {
    private final int id;
    private int boardIdx;

    public User(final int id) {
        this.id = id;
        this.boardIdx = -1;
    }

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    @Override
    public String toString() {
        return "Player-" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
