package com.senither.redvsblue.listeners;

import com.senither.redvsblue.RedVsBlue;
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
        plugin.getTeamManager().addPlayerToSpectator(event.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.getTeamManager().removePlayer(event.getPlayer());
    }
}
