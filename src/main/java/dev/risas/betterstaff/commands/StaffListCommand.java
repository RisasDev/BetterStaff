package dev.risas.betterstaff.commands;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StaffListCommand implements CommandExecutor {

    public StaffListCommand() {
        BetterStaff.getInstance().getCommand("stafflist").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("betterstaff.stafflist")) {
            sender.sendMessage(CC.noPermission());
            return true;
        }

        BetterStaff.getInstance().getStaffManager().getOnlineStaff(sender);
        return true;
    }
}
