package dev.risas.betterstaff.listeners;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.files.StaffItemFile;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.StaffItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Project: BetterStaff
 * Date: 07/06/2021 @ 12:06
 * Class: StaffItemListener
 */
public class StaffItemListener implements Listener {
    @EventHandler
    private void onInspectorItem(PlayerInteractEntityEvent event) {
        if (BetterStaff.getInstance().getStaffManager().isStaffMode(event.getPlayer())
                && BetterStaff.getInstance().getStaffManager().isStaff(event.getPlayer())) {

            if (event.getRightClicked() instanceof Player) {
                Player player = event.getPlayer();
                Player target = (Player) event.getRightClicked();
                ItemStack item = player.getInventory().getItemInHand();

                if (StaffItems.getInspector().isSimilar(item)) {
                    player.openInventory(BetterStaff.getInstance().getInventoryManager().getInspector(target));
                }
            }
        }
    }

    @EventHandler
    private void onVanishItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)
                && BetterStaff.getInstance().getStaffManager().isStaff(player)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {

                ItemStack item = player.getInventory().getItemInHand();

                if (StaffItems.getVanishOn().isSimilar(item)) {
                    BetterStaff.getInstance().getVanishManager().removeVanish(player);
                    if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
                        player.getInventory().setItem(StaffItemFile.getConfig().getInt("VANISH-OFF.SLOT"), StaffItems.getVanishOff());
                    }
                    player.sendMessage(CC.translate(ConfigFile.getConfig().getString("VANISH-COMMAND.DISABLE")));
                }
                if (StaffItems.getVanishOff().isSimilar(item)) {
                    BetterStaff.getInstance().getVanishManager().setVanish(player);
                    if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
                        player.getInventory().setItem(StaffItemFile.getConfig().getInt("VANISH-ON.SLOT"), StaffItems.getVanishOn());
                    }
                    player.sendMessage(CC.translate(ConfigFile.getConfig().getString("VANISH-COMMAND.ENABLE")));
                }
            }
        }
    }

    @EventHandler
    private void onOnlineStaffItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)
                && BetterStaff.getInstance().getStaffManager().isStaff(player)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                ItemStack item = player.getInventory().getItemInHand();

                if (StaffItems.getOnlineStaff().isSimilar(item)) {
                    player.openInventory(BetterStaff.getInstance().getInventoryManager().getOnlineStaff(player));
                }
            }
        }
    }

    @EventHandler
    private void onMinersItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)
                && BetterStaff.getInstance().getStaffManager().isStaff(player)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                ItemStack item = player.getInventory().getItemInHand();

                if (StaffItems.getMiners().isSimilar(item)) {
                    player.openInventory(BetterStaff.getInstance().getInventoryManager().getMiners(player));
                }
            }
        }
    }

    @EventHandler
    private void onRandomTeleportItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)
                && BetterStaff.getInstance().getStaffManager().isStaff(player)) {

            if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                ItemStack item = player.getInventory().getItemInHand();

                if (StaffItems.getRandomTeleport().isSimilar(item)) {
                    List<Player> players = new ArrayList<>();
                    Random rand = new Random();

                    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                        if (!BetterStaff.getInstance().getStaffManager().isStaff(online)
                                || !online.hasPermission("*")
                                || !online.isOp()) {
                            players.add(online);
                        }
                    }

                    if (players.isEmpty()) {
                        player.sendMessage(CC.translate("&cNothing to teleport."));
                        return;
                    }

                    Player random = players.get(rand.nextInt(players.size()));

                    if (player != random) {
                        player.teleport(random);
                        player.sendMessage(CC.translate("&6Teleport to &e" + random.getName() + "&6."));
                    }
                }
            }
        }
    }

    @EventHandler
    private void onMinersClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("Mining")) {
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();

            if (item != null && !item.getType().equals(Material.AIR)) {

                Player miner = Bukkit.getPlayer(item.getItemMeta().getDisplayName().substring(2));

                if (miner != null) {
                    player.teleport(miner);
                    player.sendMessage(CC.translate("&6Teleport to &e" + miner.getName() + "&e."));
                }
                else {
                    player.sendMessage(CC.translate("&cMiner not found."));
                }
                player.closeInventory();
            }
        }
    }

    @EventHandler
    private void onFreezeItem(PlayerInteractEntityEvent event) {
        if (BetterStaff.getInstance().getStaffManager().isStaffMode(event.getPlayer())
                && BetterStaff.getInstance().getStaffManager().isStaff(event.getPlayer())) {

            if (event.getRightClicked() instanceof Player) {
                Player player = event.getPlayer();
                Player target = (Player) event.getRightClicked();
                ItemStack item = player.getInventory().getItemInHand();
                String version = BetterStaff.getInstance().getServer().getVersion();

                if (version.contains("1.7") || version.contains("1.8")) {
                    if (StaffItems.getFreeze().isSimilar(item)) {
                        player.performCommand("ss " + target.getName());
                    }
                }
                else {
                    if (event.getHand().equals(EquipmentSlot.HAND)) {
                        if (StaffItems.getFreeze().isSimilar(item)) {
                            player.performCommand("ss " + target.getName());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onOnlineStaffClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("Online Staff")) {
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();

            if (item != null && !item.getType().equals(Material.AIR)) {

                Player staff = Bukkit.getPlayer(item.getItemMeta().getDisplayName().substring(2));

                if (staff != null) {
                    player.teleport(staff);
                    player.sendMessage(CC.translate("&6Teleport to &e" + staff.getName() + "&6."));
                }
                else {
                    player.sendMessage(CC.translate("&cStaff not found."));
                }
                player.closeInventory();
            }
        }
    }
}
