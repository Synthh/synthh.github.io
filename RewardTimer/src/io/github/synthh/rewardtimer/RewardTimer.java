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
		getLogger().info("onEnable has been invoked.");
		/*for (Player p : Bukkit.getServer().getOnlinePlayers()) {
		    playerList.put(p.getName(), playerData(p));
		}*/
		this.getCommand("rtstart").setExecutor(new RewardTimerCommandExecutor(this));
		this.getCommand("rtstop").setExecutor(new RewardTimerCommandExecutor(this));
		this.getCommand("rtsetxp").setExecutor(new RewardTimerCommandExecutor(this));
		this.getCommand("rtsettimer").setExecutor(new RewardTimerCommandExecutor(this));
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked.");
	}

	public boolean timer() {
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
					p.setExp(p.getExp() + 10);
				}
			}
		}
		, 12000, 12000);
		return true;
	}
	
	public boolean enabled(boolean b) {
        if(b == true ){
        	timer();
        	return true;
            //TODO
        } else {
            // enable plugin stuff
        }
		return false;   
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("rtstart")) {
			enabled(true);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("rtstop")) {
	    	enabled(false);
	    	return false;
	    }
	    
	    if (cmd.getName().equalsIgnoreCase("rtsetxp")) {
	    	if (args.length == 0){
	    		sender.sendMessage(ChatColor.RED + "Please choose an XP rate.");
	    		return false; 
	    		}
	    	
	    	if (args.length == 1){
	    	    int xprate = Integer.parseInt(args[0]);
	    	    if(xprate < 1) {
	    	    	sender.sendMessage(ChatColor.RED + "Please choose an XP rate above 0!");
	    	    	return false;
	    	    }
	    	    for (Player p: Bukkit.getServer().getOnlinePlayers()) {
		    	    if (p.hasPermission("RewardTimer.rtsetxp")) {
		    	        //TODO
		    	    	return true;
		    	    }
	    	    }
	    	}
	    	if (args.length == 0) {
	    		sender.sendMessage("Please specify a time in ticks! (1200 = 1 Minute");
	    	}
	    	if (args.length == 1){
	    		int time = Integer.parseInt(args[0]);
	    		if(time < 20) {
	    			sender.sendMessage(ChatColor.RED + "Please choose an XP rate above 0!");
	    			return false;
	    		}
	    	}
	    }
		return false;
	}   
}