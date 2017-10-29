package com.senither.redvsblue.states;

import com.senither.redvsblue.GameState;
import com.senither.redvsblue.RedVsBlue;
import com.senither.redvsblue.contracts.states.GameStateHandler;
import com.senither.redvsblue.team.TeamType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WaitingForPlayersHandler implements GameStateHandler {

    private int timeLeft = GameState.WAITING_FOR_PLAYERS.getFixedTime();

    @Override
    public void run(RedVsBlue plugin) {
        if (--timeLeft == 0) {
            timeLeft = GameState.WAITING_FOR_PLAYERS.getFixedTime();

            if (plugin.getTeamManager().getTeam(TeamType.SPECTATOR).getPlayers().size() > 1) {
                startGame(plugin);
                plugin.setGameState(GameState.PLAYING);
                return;
            }

            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                    "&6There must be at least two players on the server for the game to start!"
            ));
        }

        if (timeLeft % 10 == 0 || timeLeft <= 5) {
            sendMatchStarting();
        }
    }

    private void sendMatchStarting() {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                String.format("&aMatch starting in &4%s &asecond%s!", timeLeft, timeLeft == 1 ? "" : 's')
        ));
    }

    private void startGame(RedVsBlue plugin) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            plugin.getTeamManager().addBalancePlayer(player);
        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5# # # # # # # # # # # # # # # #"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5# # &6The match has started! &5# #"));
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&5# # # # # # # # # # # # # # # #"));

        plugin.setGameState(GameState.PLAYING);
    }
}
