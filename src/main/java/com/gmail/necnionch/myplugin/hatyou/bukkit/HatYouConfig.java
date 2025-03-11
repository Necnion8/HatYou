package com.gmail.necnionch.myplugin.hatyou.bukkit;

import com.gmail.necnionch.myplugin.hatyou.common.BukkitConfigDriver;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class HatYouConfig extends BukkitConfigDriver {

    private final Set<Material> handItemTypes = new HashSet<>();

    public HatYouConfig(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onLoaded(FileConfiguration config) {
        for (String typeName : config.getStringList("head-items")) {
            typeName = typeName.toUpperCase(Locale.ROOT);
            try {
                handItemTypes.add(Material.valueOf(typeName));
            } catch (IllegalArgumentException e) {
                getLogger().warning("Unknown item type: " + typeName);
            }
        }
        getLogger().info("Loaded " + handItemTypes.size() + " item types");
        return true;
    }

    public Set<Material> getHandItemTypes() {
        return handItemTypes;
    }

}
