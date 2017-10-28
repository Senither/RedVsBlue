package com.senither.redvsblue.team;

import org.bukkit.ChatColor;

public enum TeamType {

    RED('c'), BLUE('b'), SPECTATOR('6', false);

    private final char color;
    private final boolean game;

    TeamType(char color, boolean game) {
        this.color = color;
        this.game = game;
    }

    TeamType(char color) {
        this(color, true);
    }

    public char getColor() {
        return color;
    }

    public ChatColor getChatColor() {
        return ChatColor.getByChar(color);
    }

    public boolean isGameGame() {
        return game;
    }
}
