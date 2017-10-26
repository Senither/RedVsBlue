package com.senither.redvsblue;

import com.senither.redvsblue.world.EmptyWorldGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class RedVsBlue extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new EmptyWorldGenerator();
    }
}
