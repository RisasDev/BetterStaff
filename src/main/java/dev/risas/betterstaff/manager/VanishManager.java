package dev.risas.betterstaff.manager;

import dev.risas.betterstaff.BetterStaff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishManager {

    public List<String> vanish = new ArrayList<>();

    public boolean isVanish(Player player) {
        return vanish.contains(player.getName());
    }

    public void setVanish(Player player) {
        vanish.add(player.getName());
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (BetterStaff.getInstance().getStaffManager().isStaff(online)) {
                online.showPlayer(player);
            } else {
                online.hidePlayer(player);
            }
        }
    }

    public void removeVanish(Player player) {
        vanish.remove(player.getName());
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.showPlayer(player);
        }
    }
}
