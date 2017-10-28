package com.senither.redvsblue.team;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final TeamType type;
    private final List<String> players;
    private final org.bukkit.scoreboard.Team team;

    Team(TeamType type, Scoreboard scoreboard) {
        this.type = type;
        this.players = new ArrayList<>();

        team = scoreboard.registerNewTeam(type.name());
        team.setPrefix(type.getChatColor() + "");
    }

    public void addPlayer(Player player) {
        if (players.contains(player.getName())) {
            return;
        }

        players.add(player.getName());
        team.addEntry(player.getName());
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getName());
        team.removeEntry(player.getName());
    }

    public List<String> getPlayers() {
        return players;
    }
}
