package com.senither.redvsblue.maps;

import com.senither.redvsblue.RedVsBlue;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

    private final RedVsBlue plugin;
    private final File folder;
    private final File file;
    private final String fileName;

    private FileConfiguration fileConfiguration;

    private String name;

    private int maxTime;
    private int maxLives;

    private Location redSpawn;
    private Location blueSpawn;
    private Location spectatorSpawn;

    private List<String> builders;

    private boolean buildingAllowed;

    Map(RedVsBlue plugin, File folder, String fileName, boolean create) throws IOException {
        this.plugin = plugin;
        this.folder = folder;
        this.fileName = fileName;

        this.file = new File(folder, fileName);

        if (create) {
            file.createNewFile();
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        maxLives = 3;
        maxTime = 60;
        buildingAllowed = false;
        builders = new ArrayList<>();

        saveMap();
    }

    Map(RedVsBlue plugin, File folder, String fileName) {
        this.plugin = plugin;
        this.folder = folder;
        this.fileName = fileName;

        this.file = new File(folder, fileName);
        this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        name = fileConfiguration.getString("name", "Invalid Name");

        maxTime = fileConfiguration.getInt("max-time", 60);
        maxLives = fileConfiguration.getInt("max-lives", 3);

        redSpawn = loadLocationFrom("spawns.red");
        blueSpawn = loadLocationFrom("spawns.blue");
        spectatorSpawn = loadLocationFrom("spawns.spectator");

        builders = fileConfiguration.getStringList("builtBy");
        buildingAllowed = fileConfiguration.getBoolean("build", false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    public List<String> getBuilders() {
        return builders;
    }

    public void setBuilders(List<String> builders) {
        this.builders = builders;
    }

    public void addBuilder(String player) {
        this.builders.add(player);
    }

    public Location getRedSpawn() {
        return redSpawn;
    }

    public void setRedSpawn(Location redSpawn) {
        this.redSpawn = redSpawn;
    }

    public Location getBlueSpawn() {
        return blueSpawn;
    }

    public void setBlueSpawn(Location blueSpawn) {
        this.blueSpawn = blueSpawn;
    }

    public Location getSpectatorSpawn() {
        return spectatorSpawn;
    }

    public void setSpectatorSpawn(Location spectatorSpawn) {
        this.spectatorSpawn = spectatorSpawn;
    }

    public boolean isBuildingAllowed() {
        return buildingAllowed;
    }

    public void setBuildingAllowed(boolean buildingAllowed) {
        this.buildingAllowed = buildingAllowed;
    }

    private Location loadLocationFrom(String path) {
        World world = Bukkit.getWorld(fileConfiguration.getString(path + ".word"));
        Location location = new Location(
                world,
                fileConfiguration.getDouble(path + ".x"),
                fileConfiguration.getDouble(path + ".y"),
                fileConfiguration.getDouble(path + ".z")
        );

        location.setPitch(Float.parseFloat("" + fileConfiguration.getDouble(".pitch")));
        location.setYaw(Float.parseFloat("" + fileConfiguration.getDouble(".yaw")));

        return location;
    }

    private void setLocationFor(String path, Location location) {
        fileConfiguration.set(path + ".world", location == null ? null : location.getWorld().getName());
        fileConfiguration.set(path + ".x", location == null ? null : location.getX());
        fileConfiguration.set(path + ".y", location == null ? null : location.getY());
        fileConfiguration.set(path + ".z", location == null ? null : location.getZ());
        fileConfiguration.set(path + ".pitch", location == null ? null : location.getPitch());
        fileConfiguration.set(path + ".yaw", location == null ? null : location.getYaw());
    }

    public void saveMap() throws IOException {
        fileConfiguration.set("name", name);

        fileConfiguration.set("max-time", maxTime);
        fileConfiguration.set("max-lives", maxLives);

        setLocationFor("spawns.red", redSpawn);
        setLocationFor("spawns.blue", blueSpawn);
        setLocationFor("spawns.spectator", spectatorSpawn);

        fileConfiguration.set("buildBy", null);
        fileConfiguration.set("build", false);

        fileConfiguration.save(file);
    }
}
