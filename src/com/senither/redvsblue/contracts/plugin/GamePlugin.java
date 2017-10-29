package com.senither.redvsblue.contracts.plugin;

import com.senither.redvsblue.world.EmptyWorldGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class GamePlugin extends JavaPlugin {

    @Override
    public final void onEnable() {
        getServer().getScheduler().runTaskLater(this, this::onReady, 0L);
    }

    public abstract void onReady();

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new EmptyWorldGenerator();
    }
}
