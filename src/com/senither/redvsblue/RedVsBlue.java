package com.senither.redvsblue;

import com.senither.redvsblue.contracts.plugin.GamePlugin;
import com.senither.redvsblue.listeners.PlayerListener;
import com.senither.redvsblue.team.TeamManager;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class RedVsBlue extends GamePlugin {

    private TeamManager teamManager;
    private GameState gameState;

    @Override
    public void onReady() {
        teamManager = new TeamManager();
        gameState = GameState.WAITING_FOR_PLAYERS;

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new GameLoop(this), 20L, 1L);
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

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
