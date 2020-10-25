package dev.risas.betterstaff.files;

import dev.risas.betterstaff.BetterStaff;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class StaffItemFile extends YamlConfiguration {

    private static StaffItemFile config;
    private final Plugin plugin;
    private final File configFile;

    public static StaffItemFile getConfig() {
        if (StaffItemFile.config == null) {
            StaffItemFile.config = new StaffItemFile();
        }
        return StaffItemFile.config;
    }

    private Plugin main() {
        return BetterStaff.getInstance();
    }

    public StaffItemFile() {
        this.plugin = this.main();
        this.configFile = new File(this.plugin.getDataFolder(), "staff-items.yml");
        this.saveDefault();
        this.reload();
    }
    
    public void reload() {
        try {
            super.load(this.configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            super.save(this.configFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveDefault() {
        this.plugin.saveResource("staff-items.yml", false);
    }
}
