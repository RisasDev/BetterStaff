package dev.risas.betterstaff.manager;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.StaffItemFile;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.StaffItems;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaffManager {

    public List<String> staff = new ArrayList<>();
    public List<String> staffmode = new ArrayList<>();
    public List<String> staffchat = new ArrayList<>();

    public HashMap<String, ItemStack[]> armor = new HashMap<>();
    public HashMap<String, ItemStack[]> inventory = new HashMap<>();

    public boolean isStaff(Player player) {
        return player.hasPermission("betterstaff.staff");
    }

    public void setStaff(Player player) {
        staff.add(player.getName());
    }

    public void removeStaff(Player player) {
        staff.remove(player.getName());
    }

    public boolean isStaffMode(Player player) {
        return staffmode.contains(player.getName());
    }

    public void setStaffMode(Player player) {
        staffmode.add(player.getName());
        BetterStaff.getInstance().getVanishManager().setVanish(player);
        this.enableStaffMode(player);
        player.setGameMode(GameMode.CREATIVE);
    }

    public void removeStaffMode(Player player) {
        staffmode.remove(player.getName());
        BetterStaff.getInstance().getVanishManager().removeVanish(player);
        this.disableStaffMode(player);
        player.setGameMode(GameMode.SURVIVAL);
    }

    public boolean isStaffChat(Player player) {
        return staffchat.contains(player.getName());
    }

    public void setStaffChat(Player player) {
        staffchat.add(player.getName());
    }

    public void removeStaffChat(Player player) {
        staffchat.remove(player.getName());
    }

    public void sendMessageAllStaffs(String message) {
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (isStaff(online)) {
                online.sendMessage(CC.translate(message));
            }
        }
    }

    public void getOnlineStaff(CommandSender sender) {
        sender.sendMessage(CC.translate("&6Staff's Online"));
        sender.sendMessage(CC.translate(""));
        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (BetterStaff.getInstance().getStaffManager().isStaff(online)) {
                sender.sendMessage(CC.translate("&f\u2738 &6" + online.getName()));
            }
        }
        sender.sendMessage(CC.translate(""));
    }

    public void saveArmor(Player player) {
        armor.put(player.getName(), player.getInventory().getArmorContents());
    }

    public void restoreArmor(Player player) {
        player.getInventory().setArmorContents(armor.get(player.getName()));
        armor.remove(player.getName());
    }

    public void saveInventory(Player player) {
        inventory.put(player.getName(), player.getInventory().getContents());
    }

    public void restoreInventory(Player player) {
        player.getInventory().setContents(inventory.get(player.getName()));
        player.updateInventory();
        inventory.remove(player.getName());
    }

    public void setStaffModeItems(Player player) {
        player.getInventory().clear();
        if (StaffItemFile.getConfig().getBoolean("PHASE.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("PHASE.SLOT"), StaffItems.getPhase());
        }
        if (StaffItemFile.getConfig().getBoolean("INSPECTOR.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("INSPECTOR.SLOT"), StaffItems.getInspector());
        }
        if (StaffItemFile.getConfig().getBoolean("WORLD-EDIT.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("WORLD-EDIT.SLOT"), StaffItems.getWorldEdit());
        }
        if (StaffItemFile.getConfig().getBoolean("FREEZE.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("FREEZE.SLOT"), StaffItems.getFreeze());
        }
        if (StaffItemFile.getConfig().getBoolean("MINER.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("MINER.SLOT"), StaffItems.getMiners());
        }
        if (StaffItemFile.getConfig().getBoolean("STAFF-ONLINE.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("STAFF-ONLINE.SLOT"), StaffItems.getOnlineStaff());
        }
        if (StaffItemFile.getConfig().getBoolean("VANISH-ON.ENABLE")) {
            if (BetterStaff.getInstance().getVanishManager().isVanish(player)) {
                player.getInventory().setItem(StaffItemFile.getConfig().getInt("VANISH-ON.SLOT"), StaffItems.getVanishOn());
            }
            else {
                player.getInventory().setItem(StaffItemFile.getConfig().getInt("VANISH-OFF.SLOT"), StaffItems.getVanishOff());
            }
        }
        if (StaffItemFile.getConfig().getBoolean("RANDOM-TELEPORT.ENABLE")) {
            player.getInventory().setItem(StaffItemFile.getConfig().getInt("RANDOM-TELEPORT.SLOT"), StaffItems.getRandomTeleport());
        }
        player.updateInventory();
    }

    public void enableStaffMode(Player player) {
        this.saveArmor(player);
        this.saveInventory(player);
        player.getInventory().setArmorContents(null);
        this.setStaffModeItems(player);
    }

    public void disableStaffMode(Player player) {
        player.getInventory().clear();
        this.restoreArmor(player);
        this.restoreInventory(player);
        player.updateInventory();
    }
}
