package dev.risas.betterstaff.listeners;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    public ChatListener() {
        Bukkit.getPluginManager().registerEvents(this, BetterStaff.getInstance());
    }

    @EventHandler
    private void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("CHAT.FROZEN")
                    .replace("{player}", player.getName())
                    .replace("{message}", event.getMessage())));
            BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("CHAT.FROZEN")
                    .replace("{player}", player.getName())
                    .replace("{message}", event.getMessage()));
        }
        if (BetterStaff.getInstance().getStaffManager().isStaffChat(player) && BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            event.setCancelled(true);
            BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("CHAT.STAFFCHAT")
                    .replace("{staff}", player.getName())
                    .replace("{message}", event.getMessage()));
        }
    }
}
