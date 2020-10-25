package dev.risas.betterstaff.utilities;

import dev.risas.betterstaff.files.ConfigFile;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> translate(List<String> message) {
        List<String> messages = new ArrayList<>();
        for (String string : message) {
            messages.add(CC.translate(string));
        }
        return messages;
    }

    public static String noPermission() {
        return CC.translate(ConfigFile.getConfig().getString("NO-PERMISSION"));
    }

    public static String noConsole() {
        return CC.translate(ConfigFile.getConfig().getString("NO-CONSOLE"));
    }

    public static String playerNotFound(String player) {
        return CC.translate(ConfigFile.getConfig().getString("PLAYER-NOT-FOUND")
                .replace("{player}", player));
    }
}
