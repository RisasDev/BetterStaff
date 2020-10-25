package dev.risas.betterstaff.commands;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.files.StaffItemFile;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.StaffItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    public VanishCommand() {
        BetterStaff.getInstance().getCommand("vanish").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.noConsole());
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("betterstaff.vanish")) {
            player.sendMessage(CC.noPermission());
            return true;
        }

        if (!BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("VANISH-COMMAND.NOT-STAFF")));
            return true;
        }

        if (BetterStaff.getInstance().getVanishManager().isVanish(player)) {
            BetterStaff.getInstance().getVanishManager().removeVanish(player);
            if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
                player.getInventory().setItem(StaffItemFile.getConfig().getInt("VANISH-OFF.SLOT"), StaffItems.getVanishOff());
            }
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("VANISH-COMMAND.DISABLE")));
        }
        else {
            BetterStaff.getInstance().getVanishManager().setVanish(player);
            if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
                player.getInventory().setItem(StaffItemFile.getConfig().getInt("VANISH-ON.SLOT"), StaffItems.getVanishOn());
            }
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("VANISH-COMMAND.ENABLE")));
        }
        return true;
    }
}
