package dev.risas.betterstaff.commands;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModeCommand implements CommandExecutor {

    public StaffModeCommand() {
        BetterStaff.getInstance().getCommand("staffmode").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.noConsole());
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("betterstaff.staffmode")) {
            player.sendMessage(CC.noPermission());
            return true;
        }

        if (!BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("STAFFMODE-COMMAND.NOT-STAFF")));
            return true;
        }

        if (BetterStaff.getInstance().getStaffManager().isStaffMode(player)) {
            BetterStaff.getInstance().getStaffManager().removeStaffMode(player);
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("STAFFMODE-COMMAND.DISABLE")));
        }
        else {
            BetterStaff.getInstance().getStaffManager().setStaffMode(player);
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("STAFFMODE-COMMAND.ENABLE")));
        }
        return true;
    }
}
