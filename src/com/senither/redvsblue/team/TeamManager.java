package com.senither.redvsblue.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class TeamManager {

    private final Map<TeamType, Team> teams = new HashMap<>();
    private final Scoreboard scoreboard;

    public TeamManager() {
        scoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        for (TeamType type : TeamType.values()) {
            teams.put(type, new Team(type, scoreboard));
        }
    }

    public void addPlayer(Player player) {
        // TODO: If the gave have already started, add the player to the spectator team.
        removePlayer(player);

        if (teams.get(TeamType.BLUE).getPlayers().size() > teams.get(TeamType.RED).getPlayers().size())
            addPlayer(TeamType.RED, player);
        else addPlayer(TeamType.BLUE, player);
    }

    public void addPlayer(TeamType type, Player player) {
        for (TeamType teamType : TeamType.values()) {
            if (teamType.name().equals(type.name())) {
                teams.get(type).addPlayer(player);
                continue;
            }

            if (teams.get(type).hasPlayer(player)) {
                teams.get(type).removePlayer(player);
            }
        }
    }

    public void removePlayer(Player player) {
        for (TeamType type : TeamType.values()) {
            if (teams.get(type).hasPlayer(player)) {
                teams.get(type).removePlayer(player);
            }
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
