package dev.risas.betterstaff.commands;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

    public StaffChatCommand() {
        BetterStaff.getInstance().getCommand("staffchat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.noConsole());
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("betterstaff.staffchat")) {
            player.sendMessage(CC.noPermission());
            return true;
        }

        if (!BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("STAFFCHAT-COMMAND.NOT-STAFF")));
            return true;
        }

        if (BetterStaff.getInstance().getStaffManager().isStaffChat(player)) {
            BetterStaff.getInstance().getStaffManager().removeStaffChat(player);
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("STAFFCHAT-COMMAND.DISABLE")));
        }
        else {
            BetterStaff.getInstance().getStaffManager().setStaffChat(player);
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("STAFFCHAT-COMMAND.ENABLE")));
        }
        return true;
    }
}
