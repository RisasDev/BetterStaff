package dev.risas.betterstaff.commands;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.files.StaffItemFile;
import dev.risas.betterstaff.utilities.CC;
import dev.risas.betterstaff.utilities.Description;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetterStaffCommand implements CommandExecutor {

    public BetterStaffCommand() {
        BetterStaff.getInstance().getCommand("betterstaff").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("betterstaff.plugin")) {
            sender.sendMessage(CC.noPermission());
            return true;
        }

        if (args.length < 1) {
            this.getUsage(sender, label);
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {

            if (!sender.hasPermission("betterstaff.help")) {
                sender.sendMessage(CC.noPermission());
                return true;
            }
            this.getHelp(sender);
        }
        else if (args[0].equalsIgnoreCase("reload")) {

            if (!sender.hasPermission("betterstaff.reload")) {
                sender.sendMessage(CC.noPermission());
                return true;
            }

            ConfigFile.getConfig().reload();
            StaffItemFile.getConfig().reload();
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (BetterStaff.getInstance().getStaffManager().isStaffMode(online)) {
                    BetterStaff.getInstance().getStaffManager().setStaffModeItems(online);
                }
            }
            sender.sendMessage(CC.translate("&aSuccessfully reload all plugin files."));
        }
        else {
            this.getUsage(sender, label);
        }
        return true;
    }

    private void getUsage(CommandSender sender, String label) {
        sender.sendMessage(CC.translate("&7&m---*------[&r &6" + Description.getName() + " &7&m]------*---"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/" + label + " help"));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/" + label + " reload"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&7&m---*------[&r &6" + Description.getVersion() + " &7&m]------*---"));
    }

    private void getHelp(CommandSender sender) {
        sender.sendMessage(CC.translate("&7&m---*------[&r &6" + Description.getName() + " &7&m]------*---"));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/freeze <player> &e- &fFreeze a player."));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/staffchat &e- &fToggle your staffchat."));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/stafflist &e- &fView all staffs online."));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/staffmode &e- &fToggle your staffmode."));
        sender.sendMessage(CC.translate(" &7\u25B6 &6/vanish &e- &fToggle your vanish."));
        sender.sendMessage(CC.translate(""));
        sender.sendMessage(CC.translate("&7&m---*------[&r &6" + Description.getVersion() + " &7&m]------*---"));
    }
}
