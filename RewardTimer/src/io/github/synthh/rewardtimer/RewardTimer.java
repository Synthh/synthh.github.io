package io.github.synthh.rewardtimer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RewardTimer extends JavaPlugin {

	@Override
	public void onEnable() {
		timer();
		getLogger().info("onEnable has been invoked.");
		/*
		 * RewardTimerCommandExecutor rtExecutor = new
		 * RewardTimerCommandExecutor(this);
		 * this.getCommand("rtstart").setExecutor(rtExecutor);
		 * this.getCommand("rtstop").setExecutor(rtExecutor);
		 * this.getCommand("rtsetxp").setExecutor(rtExecutor);
		 * this.getCommand("rtsettimer").setExecutor(rtExecutor);
		 */
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked.");
	}

	private boolean enabled = false;

	public void enabled() {
		enabled = !enabled;
	}

	public void timer() {
		this.getServer().getScheduler()
				.scheduleSyncRepeatingTask(this, new Runnable() {
					public void run() {
						if (!enabled)
							return;
						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
							p.setExp((float) (p.getExp() + 0.8));
						}
					}
				}, 120, 120);
	}

	/*
	 * public boolean enabled(boolean b) { if(b == true ){
	 * 
	 * return true; //TODO } else { // enable plugin stuff return false; } }
	 */

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (cmd.getName().equalsIgnoreCase("rttoggle")) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.hasPermission("RewardTimer.rttoggle")) {
					enabled();
					String state = isEnabled() ? "&cdisabled" : "&aenabled";
					this.enabled();
					sender.sendMessage(ChatColor.translateAlternateColorCodes(
							'&', "RT has been " + state + "."));
					return true;
				}
				return false;
			}

			/*if (cmd.getName().equalsIgnoreCase("rtsetxp")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Please choose an XP rate.");
					return false;
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
			}
			if (cmd.getName().equalsIgnoreCase("rtsettimer")) {
				if (args.length == 0) {
					sender.sendMessage("Please specify a time in ticks! (1200 = 1 Minute");
					return false;
				}
				if (args.length == 1) {
					int time = Integer.parseInt(args[0]);
					if (time < 20) {
						sender.sendMessage(ChatColor.RED
								+ "Please choose an interval above 20!");
						return false;
					}
				}
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					if (p.hasPermission("RewardTimer.rtsettimer")) {
						// TODO
						return true;
					}
				}*/
			}
		return false;
	}
}