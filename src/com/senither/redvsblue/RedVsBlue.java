package com.senither.redvsblue;

import com.senither.redvsblue.listeners.PlayerListener;
import com.senither.redvsblue.team.TeamManager;
import com.senither.redvsblue.world.EmptyWorldGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class RedVsBlue extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getScheduler().runTaskLater(this, () -> {
            getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        }, 0L);
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

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new EmptyWorldGenerator();
    }
}
