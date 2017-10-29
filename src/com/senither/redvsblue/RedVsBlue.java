package com.senither.redvsblue;

import com.senither.redvsblue.contracts.plugin.GamePlugin;
import com.senither.redvsblue.listeners.PlayerListener;
import com.senither.redvsblue.team.TeamManager;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class RedVsBlue extends GamePlugin {

    @Override
    public void onReady() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        for (Objective obj : TeamManager.getScoreboard().getObjectives()) {
            obj.unregister();
        }
        for (Team team : TeamManager.getScoreboard().getTeams()) {
            team.unregister();
        }
    }
}
