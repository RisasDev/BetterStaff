package dev.risas.betterstaff.listeners;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.files.StaffItemFile;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.DiscordUtils;
import dev.risas.betterstaff.utilities.StaffItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StaffListener implements Listener {

    public StaffListener() {
        Bukkit.getPluginManager().registerEvents(this, BetterStaff.getInstance());
    }

    @EventHandler
    private void onStaffJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            BetterStaff.getInstance().getStaffManager().setStaff(player);
            if (ConfigFile.getConfig().getBoolean("STAFF.STAFFMODE-JOIN")) {
                player.performCommand("staffmode");
            }
            if (ConfigFile.getConfig().getBoolean("STAFF.JOIN-MESSAGE.ENABLE")) {
                BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("STAFF.JOIN-MESSAGE.MESSAGE")
                        .replace("{staff}", player.getName()));
            }
            if (BetterStaff.getInstance().getConfig().getBoolean("WEBHOOK.STATUS")){
                if (BetterStaff.getInstance().getStaffManager().isStaff(player)) {
                    try {
                        DiscordUtils wh = new DiscordUtils(BetterStaff.getInstance().getConfig().getString("WEBHOOK.LINK"));
                        wh.setAvatarUrl(BetterStaff.getInstance().getConfig().getString("WEBHOOK.IMAGE"));
                        wh.setUsername(BetterStaff.getInstance().getConfig().getString("WEBHOOK.USERNAME"));
                        wh.setTts(false);
                        wh.addEmbed(new DiscordUtils.EmbedObject()
                                .setTitle(BetterStaff.getInstance().getConfig().getString("STAFF.JOIN-MESSAGE.WEBHOOK.TITLE"))
                                .setColor(Color.RED)
                                .addField("Staff:", player.getName(), true)
                                .setDescription(BetterStaff.getInstance().getConfig().getString("STAFF.JOIN-MESSAGE.WEBHOOK.FIELD"))
                                .setThumbnail("https://minotar.net/avatar/" + player.getName()));
                        wh.execute();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

    @EventHandler
    private void onStaffQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            BetterStaff.getInstance().getStaffManager().removeStaff(player);
            if (ConfigFile.getConfig().getBoolean("STAFF.QUIT-MESSAGE.ENABLE")) {
                BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("STAFF.QUIT-MESSAGE.MESSAGE")
                        .replace("{staff}", player.getName()));
            }
        }
        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            BetterStaff.getInstance().getStaffManager().removeStaffMode(player);
        }
        if (BetterStaff.getInstance().getStaffManager().isStaffChat(player)) {
            BetterStaff.getInstance().getStaffManager().removeStaffChat(player);
        }
        if (BetterStaff.getInstance().getConfig().getBoolean("WEBHOOK.STATUS")){

            if (BetterStaff.getInstance().getStaffManager().isStaff(player)) {
                try{
                    DiscordUtils wh = new DiscordUtils(BetterStaff.getInstance().getConfig().getString("WEBHOOK.LINK"));
                    wh.setAvatarUrl(BetterStaff.getInstance().getConfig().getString("WEBHOOK.IMAGE"));
                    wh.setUsername(BetterStaff.getInstance().getConfig().getString("WEBHOOK.USERNAME"));
                    wh.setTts(false);
                    wh.addEmbed(new DiscordUtils.EmbedObject()
                            .setTitle(BetterStaff.getInstance().getConfig().getString("STAFF.QUIT-MESSAGE.WEBHOOK.TITLE"))
                            .setColor(Color.RED)
                            .addField("Staff:", player.getName(), true)
                            .setDescription(BetterStaff.getInstance().getConfig().getString("STAFF.QUIT-MESSAGE.WEBHOOK.FIELD"))
                            .setThumbnail("https://minotar.net/avatar/" + player.getName()));
                    wh.execute();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    private void onStaffDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onStaffHit(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();
        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }

    @EventHandler
    private void onStaffPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffPickup(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onStaffDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            event.getDrops().clear();
        }
    }
}
