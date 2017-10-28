package com.senither.redvsblue.listeners;

import com.senither.redvsblue.RedVsBlue;
import com.senither.redvsblue.team.TeamManager;
import com.senither.redvsblue.team.TeamType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final RedVsBlue plugin;

    public PlayerListener(RedVsBlue plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        TeamManager.addPlayer(TeamType.BLUE, event.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        TeamManager.removePlayer(event.getPlayer());
    }
}
