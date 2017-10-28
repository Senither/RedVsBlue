package com.senither.redvsblue.team;

import org.bukkit.ChatColor;

public enum TeamType {

    RED('c'), BLUE('b'), SPECTOR('6');

    private final char color;

    TeamType(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }

    public ChatColor getChatColor() {
        return ChatColor.getByChar(color);
    }
}
