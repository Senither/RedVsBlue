package com.senither.redvsblue.contracts.plugin;

public abstract class GameLoopRunnable implements Runnable {

    private int ticks = 0;

    @Override
    public void run() {
        ticks++;

        if (ticks % 5 == 0) runEveryFiveTicks();
        if (ticks >= 20) {
            runEverySecond();
            ticks = 0;
        }
    }

    protected abstract void runEverySecond();

    protected abstract void runEveryFiveTicks();
}
