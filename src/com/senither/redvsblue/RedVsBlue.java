package com.senither.redvsblue;

import com.senither.redvsblue.contracts.plugin.GamePlugin;
import com.senither.redvsblue.listeners.PlayerListener;
import com.senither.redvsblue.team.TeamManager;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class RedVsBlue extends GamePlugin {

    private TeamManager teamManager;

    @Override
    public void onReady() {
        teamManager = new TeamManager();

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        for (Objective obj : teamManager.getScoreboard().getObjectives()) {
            obj.unregister();
        }
        for (Team team : teamManager.getScoreboard().getTeams()) {
            team.unregister();
        }
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
}
