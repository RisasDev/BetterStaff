package dev.risas.betterstaff.manager;

import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FreezeManager {

    public List<String> freeze = new ArrayList<>();

    public boolean isFrozen(Player player) {
        return freeze.contains(player.getName());
    }

    public void setFreeze(Player player) {
        freeze.add(player.getName());
        this.frozenMessage(player);
    }

    public void removeFreeze(Player player) {
        freeze.remove(player.getName());
    }

    private void frozenMessage(Player player) {
        for (String lines : ConfigFile.getConfig().getStringList("FREEZE-COMMAND.FROZEN-MESSAGE")) {
            player.sendMessage(CC.translate(lines));
        }
    }
}
