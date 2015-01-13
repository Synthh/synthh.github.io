package io.github.synthh.rewardtimer;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
 
public class RewardTimer extends JavaPlugin implements Listener {
	
	public void onEnable() {
            getConfig().options().copyDefaults(true);
            saveConfig();
            Bukkit.getServer().getPluginManager().registerEvents(this, this);
            timer();
    }
	
	@EventHandler
    /*public void onPlayerJoin(PlayerJoinEvent e) {
            Player p = e.getPlayer();
    }*/
	
	public enabled(boolean b) {
        if(b == true ){
            
        } else {
            // enable plugin stuff
        }
        
    }
        public void timer() {
                this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
						public void run() {
                                for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                                        p.setExp(p.getExp() + 10);
                                        }
                                }
                        }
                , 12000, 12000);
        }
        
        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
               
                if (cmd.getName().equalsIgnoreCase("xptstart")) {
                	enabled(true);
                	return true;;
                }
               
                if (cmd.getName().equalsIgnoreCase("xptstop")) {
                	
                }
               
                if (cmd.getName().equalsIgnoreCase("xptsetxp")) {
                	if (args.length == 0){
                		sender.sendMessage(ChatColor.RED + "Please choose an XP rate.");
                		return false; 
                		}
                	if (args.length == 1){
                	    int amount = Integer.parseInt(args[0]);
                	    if(amount < 1) {
                	    	sender.sendMessage(ChatColor.RED + "Please choose an XP rate above 0!");
                	    	return false; 
                	    }
                	    else {
                	    	
                	    }
                	}
                }
               
                if (cmd.getName().equalsIgnoreCase("xptsettimer")) {
                	if (args.length == 0) {
                		sender.sendMessage("Please specify a time in ticks! (1200 = 1 Minute");
                	}
                	
                }
                return true;
        }
}