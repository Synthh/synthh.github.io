package io.github.synthh.rewardtimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RewardTimerCommandExecutor implements CommandExecutor {
	private final RewardTimer plugin;

	public RewardTimerCommandExecutor(RewardTimer plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("rttoggle")) {

		}
		if (cmd.getName().equalsIgnoreCase("rtsetxp")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Please choose an XP rate.");
				return false;
			}
		}
		if (args.length == 1) {
			int xprate = Integer.parseInt(args[0]);
			if (xprate < 1) {
				sender.sendMessage(ChatColor.RED
						+ "Please choose an XP rate above 0!");
				return false;
			}
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.hasPermission("RewardTimer.rtsetxp")) {
					// TODO

					return true;
				}
			}
		}
		if (args.length == 0) {
			sender.sendMessage("Please specify a time in ticks! (1200 = 1 Minute");
		}
		if (args.length == 1) {
			int time = Integer.parseInt(args[0]);
			if (time < 20) {
				sender.sendMessage(ChatColor.RED
						+ "Please choose an XP rate above 0!");
				return false;
			}
		}
		return false;
	}
}