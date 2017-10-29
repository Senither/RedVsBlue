package com.senither.redvsblue.maps;

import com.senither.redvsblue.RedVsBlue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private final RedVsBlue plugin;
    private final File folder;

    private final List<Map> maps = new ArrayList<>();

    public MapManager(RedVsBlue plugin) {
        this.plugin = plugin;

        this.folder = new File(plugin.getDataFolder(), "maps");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        loadMaps();
    }

    private void loadMaps() {
        maps.clear();

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                maps.add(new Map(plugin, folder, file.getName()));
            }
        }
    }

    public Map createNewMap(String name) throws IOException {
        File file = new File(folder, name.toLowerCase().replace(" ", "_") + ".yml");
        if (!file.exists()) {
            return new Map(plugin, folder, file.getName(), true);
        }
        return null;
    }
}
