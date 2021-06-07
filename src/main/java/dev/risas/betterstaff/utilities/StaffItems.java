package dev.risas.betterstaff.utilities;

import dev.risas.betterstaff.files.StaffItemFile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StaffItems {

    public static ItemStack getPhase() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("PHASE.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("PHASE.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("PHASE.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getInspector() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("INSPECTOR.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("INSPECTOR.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("INSPECTOR.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getWorldEdit() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("WORLD-EDIT.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("WORLD-EDIT.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("WORLD-EDIT.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getFreeze() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("FREEZE.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("FREEZE.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("FREEZE.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getMiners() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("MINER.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("MINER.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("MINER.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getOnlineStaff() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("STAFF-ONLINE.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("STAFF-ONLINE.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("STAFF-ONLINE.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getVanishOn() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("VANISH-ON.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("VANISH-ON.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("VANISH-ON.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getVanishOff() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("VANISH-OFF.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("VANISH-OFF.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("VANISH-OFF.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getRandomTeleport() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("RANDOM-TELEPORT.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("RANDOM-TELEPORT.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("RANDOM-TELEPORT.NAME")));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getClickCounts() {
        ItemStack item = new ItemStack(Material.valueOf(StaffItemFile.getConfig().getString("CLICK-COUNTS.ITEM").toUpperCase()),
                1, (short) StaffItemFile.getConfig().getInt("CLICK-COUNTS.DATA"));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(StaffItemFile.getConfig().getString("CLICK-COUNTS.NAME")));
        item.setItemMeta(meta);
        return item;
    }
}
