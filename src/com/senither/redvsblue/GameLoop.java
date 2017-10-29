package com.senither.redvsblue;

import com.senither.redvsblue.contracts.plugin.GameLoopRunnable;

public class GameLoop extends GameLoopRunnable {

    private final RedVsBlue plugin;

    public GameLoop(RedVsBlue plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void runEverySecond() {
        plugin.getGameState().getHandler().run(plugin);
    }

    @Override
    protected void runEveryFiveTicks() {
        // TODO: Update team stats for scoreboards here...
    }
}
