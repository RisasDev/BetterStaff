package dev.risas.betterstaff.manager;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.Time;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class InventoryManager {

    public Inventory getOnlineStaff(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, "Online Staff");

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            if (BetterStaff.getInstance().getStaffManager().isStaff(online)) {
                if (online.equals(player)) { continue; }
                inv.addItem(this.onlineStaff(online));
            }
        }
        return inv;
    }

    public Inventory getMiners(Player player) {
        Inventory inv = Bukkit.getServer().createInventory(null, 54, "Mining");

        for (Player miner : Bukkit.getServer().getOnlinePlayers()) {
            if (miner.getLocation().getBlockY() <= 20) {
                if (miner.equals(player)) { continue; }
                inv.addItem(this.miners(miner));
            }
        }
        return inv;
    }

    public Inventory getInspector(Player target) {
        Inventory inv = Bukkit.getServer().createInventory(null, 54, "Inventory of " + target.getName());

        inv.setContents(target.getInventory().getContents());
        for (int i = 36; i < 45; i++) {
            inv.setItem(i, this.fill());
        }
        inv.setItem(45, target.getInventory().getHelmet() == null ? this.none() : target.getInventory().getHelmet());
        inv.setItem(46, target.getInventory().getChestplate() == null ? this.none() : target.getInventory().getChestplate());
        inv.setItem(47, target.getInventory().getLeggings() == null ? this.none() : target.getInventory().getLeggings());
        inv.setItem(48, target.getInventory().getBoots() == null ? this.none() : target.getInventory().getBoots());
        inv.setItem(49, this.fill());
        inv.setItem(50, this.utilities(target));
        inv.setItem(51, this.location(target));
        inv.setItem(52, this.effects(target));
        inv.setItem(53, this.information(target));
        return inv;
    }

    private ItemStack onlineStaff(Player player) {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&6" + player.getName()));
        meta.setLore(CC.translate(Arrays.asList("", "&7&oClick to teleport to " + player.getName())));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack miners(Player player) {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&6" + player.getName()));
        meta.setLore(CC.translate(Arrays.asList(
                "",
                "&eHeight&7: &f" + player.getLocation().getBlockY(),
                "&eDiamonds Mining&7: &f" + player.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE),
                "",
                "&7&oClick to teleport to " + player.getName())));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack utilities(Player player) {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&6Utilities"));
        meta.setLore(CC.translate(Arrays.asList(
                "",
                "&eGamemode&7: &f" + player.getGameMode(),
                "&eOperator&7: " + (player.isOp() ? "&aYes" : "&cNo"),
                "&eFly&7: " + (player.isFlying() ? "&aYes" : "&cNo"),
                "")));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack location(Player player) {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&6Location"));
        meta.setLore(CC.translate(Arrays.asList(
                "",
                "&eWorld&7: &f" + player.getLocation().getWorld().getName(),
                "&eX&7: &f" + Math.round(player.getLocation().getX()),
                "&eY&7: &f" + Math.round(player.getLocation().getY()),
                "&eZ&7: &f" + Math.round(player.getLocation().getZ()),
                "")));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack effects(Player player) {
        ItemStack item = new ItemStack(Material.POTION, 1);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        for (PotionEffect effect : player.getActivePotionEffects()) {
            String name = effect.getType().getName();
            int duration = effect.getDuration();
            int amplifier = effect.getAmplifier();
            lore.add("&e" + name + " " + (amplifier + 1) + "&7: &f" + Time.formatIntMin(duration / 20));
        }
        lore.add(" ");
        meta.setDisplayName(CC.translate("&6Active Effects"));
        meta.setLore(CC.translate(lore));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack information(Player player) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        ItemStack item = new ItemStack(Material.ITEM_FRAME, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&6" + player.getName()));
        meta.setLore(CC.translate(Arrays.asList(
                "",
                "&eHealth&7: &f" + decimalFormat.format(player.getHealth() / 2),
                "&eFood&7: &f" + decimalFormat.format(player.getFoodLevel() / 2),
                "&eXP&7: &f" + player.getExp(),
                "")));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack none() {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&cNone"));
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack fill() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(CC.translate("&7 "));
        item.setItemMeta(meta);
        return item;
    }
}
