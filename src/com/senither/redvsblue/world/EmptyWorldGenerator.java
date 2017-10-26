package com.senither.redvsblue.world;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmptyWorldGenerator extends ChunkGenerator {

    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList(new BlockPopulator[0]);
    }

    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        if (x == 0 && z == 0) {
            chunk.setBlock(0, 64, 0, Material.BEDROCK);
        }
        return chunk;
    }

    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0.0D, 66.0D, 0.0D);
    }
}
