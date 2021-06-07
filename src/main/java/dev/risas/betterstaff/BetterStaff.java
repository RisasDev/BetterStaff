package dev.risas.betterstaff;

import dev.risas.betterstaff.commands.*;
import dev.risas.betterstaff.listeners.ChatListener;
import dev.risas.betterstaff.listeners.FreezeListener;
import dev.risas.betterstaff.listeners.StaffItemListener;
import dev.risas.betterstaff.listeners.StaffListener;
import dev.risas.betterstaff.manager.FreezeManager;
import dev.risas.betterstaff.manager.InventoryManager;
import dev.risas.betterstaff.manager.StaffManager;
import dev.risas.betterstaff.manager.VanishManager;
import lombok.Getter;
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

        saveDefaultConfig();
        reloadConfig();
        this.loadCommands();
        this.loadListeners();
        this.loadManagers();

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
        new StaffItemListener();
    }

    private void loadManagers() {
        freezeManager = new FreezeManager();
        staffManager = new StaffManager();
        vanishManager = new VanishManager();
        inventoryManager = new InventoryManager();
    }
}
