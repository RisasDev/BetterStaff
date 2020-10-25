package dev.risas.betterstaff;

import dev.risas.betterstaff.commands.*;
import dev.risas.betterstaff.listeners.ChatListener;
import dev.risas.betterstaff.listeners.FreezeListener;
import dev.risas.betterstaff.listeners.StaffListener;
import dev.risas.betterstaff.manager.FreezeManager;
import dev.risas.betterstaff.manager.InventoryManager;
import dev.risas.betterstaff.manager.StaffManager;
import dev.risas.betterstaff.manager.VanishManager;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.Description;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BetterStaff extends JavaPlugin {

    @Getter
    private static BetterStaff instance;
    private FreezeManager freezeManager;
    private StaffManager staffManager;
    private VanishManager vanishManager;
    private InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadCommands();
        this.loadListeners();
        this.loadManagers();

        Bukkit.getConsoleSender().sendMessage(CC.translate("&7&m---*------[&r &aENABLE &7&m]------*---"));
        Bukkit.getConsoleSender().sendMessage(CC.translate(""));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &7\u25B6 &6Name&7: &f" + Description.getName()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &7\u25B6 &6Author&7: &f" + Description.getAuthor()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(" &7\u25B6 &6Version&7: &f" + Description.getVersion()));
        Bukkit.getConsoleSender().sendMessage(CC.translate(""));
        Bukkit.getConsoleSender().sendMessage(CC.translate("&7&m---*------[&r &aENABLE &7&m]------*---"));
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadCommands() {
        new BetterStaffCommand();
        new FreezeCommand();
        new StaffModeCommand();
        new StaffListCommand();
        new StaffChatCommand();
        new VanishCommand();
    }

    private void loadListeners() {
        new ChatListener();
        new FreezeListener();
        new StaffListener();
    }

    private void loadManagers() {
        freezeManager = new FreezeManager();
        staffManager = new StaffManager();
        vanishManager = new VanishManager();
        inventoryManager = new InventoryManager();
    }
}
