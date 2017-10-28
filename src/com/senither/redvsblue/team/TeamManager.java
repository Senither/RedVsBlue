package com.senither.redvsblue.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class TeamManager {

    private static final Map<TeamType, Team> teams = new HashMap<>();
    private static final Scoreboard scoreboard;

    static {
        scoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        for (TeamType type : TeamType.values()) {
            teams.put(type, new Team(type, scoreboard));
        }
    }

    public static void addPlayer(TeamType type, Player player) {
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

    public static void removePlayer(Player player) {
        for (TeamType type : TeamType.values()) {
            if (teams.get(type).hasPlayer(player)) {
                teams.get(type).removePlayer(player);
            }
        }
    }

    public static Scoreboard getScoreboard() {
        return scoreboard;
    }
}
