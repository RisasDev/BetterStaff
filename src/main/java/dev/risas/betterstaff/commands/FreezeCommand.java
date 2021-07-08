package dev.risas.betterstaff.commands;

import dev.risas.betterstaff.BetterStaff;
import dev.risas.betterstaff.files.ConfigFile;
import dev.risas.betterstaff.utilities.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    public FreezeCommand() {
        BetterStaff.getInstance().getCommand("freeze").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("betterstaff.freeze")) {
            sender.sendMessage(CC.noPermission());
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(CC.translate("&cUsage: /" + label + " <player>"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(CC.playerNotFound(args[0]));
            return true;
        }

        if (sender.equals(player)) {
            sender.sendMessage(CC.translate(ConfigFile.getConfig().getString("FREEZE-COMMAND.FROZEN-SELF")));
            return true;
        }

        if (BetterStaff.getInstance().getStaffManager().isStaff(player)) {
            sender.sendMessage(CC.translate(ConfigFile.getConfig().getString("FREEZE-COMMAND.FROZEN-STAFF")));
            return true;
        }

        if (BetterStaff.getInstance().getFreezeManager().isFrozen(player)) {
            BetterStaff.getInstance().getFreezeManager().removeFreeze(player);
            BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("FREEZE-COMMAND.UNFROZEN-PLAYER-STAFF")
                    .replace("{staff}", sender.getName())
                    .replace("{player}", player.getName()));
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("FREEZE-COMMAND.UNFROZEN-PLAYER")
                    .replace("{staff}", sender.getName())));
        }
        else {
            BetterStaff.getInstance().getFreezeManager().setFreeze(player);
            BetterStaff.getInstance().getStaffManager().sendMessageAllStaffs(ConfigFile.getConfig().getString("FREEZE-COMMAND.FROZEN-PLAYER-STAFF")
                    .replace("{staff}", sender.getName())
                    .replace("{player}", player.getName()));
            player.sendMessage(CC.translate(ConfigFile.getConfig().getString("FREEZE-COMMAND.FROZEN-PLAYER")
                    .replace("{staff}", sender.getName())));
        }
        return true;
    }
}
