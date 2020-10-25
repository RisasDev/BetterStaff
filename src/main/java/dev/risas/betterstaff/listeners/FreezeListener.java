package dev.risas.betterstaff.listeners;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FreezeListener implements Listener {

    public FreezeListener() {
        Bukkit.getPluginManager().registerEvents(this, BetterStaff.getInstance());
    }

    @EventHandler
    private void onFrozenQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            BetterStaff.getInstance().getFreezeManager().removeFreeze(player);
            BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("FREEZE-COMMAND.FROZEN-PLAYER-DISCONNECT")
                .replace("{player}", player.getName()));
        }
    }

    @EventHandler
    private void onFrozenMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            player.teleport(player.getLocation());
        }
    }

    @EventHandler
    private void onStaffInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }

    @EventHandler
    private void onFrozenBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou can't break blocks while you're frozen."));
        }
    }

    @EventHandler
    private void onFrozenPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou can't place blocks while you're frozen."));
        }
    }

    @EventHandler
    private void onFrozenPearl(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)
                && player.getInventory().getItemInHand().getType().equals(Material.ENDER_PEARL)) {
            event.setCancelled(true);
            player.updateInventory();
            player.sendMessage(CC.translate("&cYou can't throw enderpearl while you're frozen."));
        }
    }

    @EventHandler
    public void onFrozenHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {

            Player player = (Player) event.getEntity();
            Player target = (Player) event.getDamager();

            if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
                event.setCancelled(true);
                target.sendMessage(CC.translate("&c" + player.getName() + " is currently frozen."));
            }
            if (BetterStaff.getInstance().getFreezeManager().isFrozen(target)) {
                event.setCancelled(true);
                target.sendMessage(CC.translate("&cYou can't damage players while you're frozen."));
            }
        }
    }

    @EventHandler
    private void onFrozenDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
                event.setCancelled(true);
            }
        }
    }
}
