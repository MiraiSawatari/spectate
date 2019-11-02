package work.ecom.test;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import com.destroystokyo.paper.event.player.PlayerStopSpectatingEntityEvent;
public class main extends JavaPlugin {

	HashMap<String, Location> now = new HashMap<String,Location>();
	HashMap<String, GameMode> now1 = new HashMap<String,GameMode>();
	HashMap<String, BossBar> test01 = new HashMap<String,BossBar>();
	@Override
	public void onEnable() {
		System.out.println("The Test01 Enabled");


	}

	@Override
	public void onDisable() {
		System.out.println("The Test01 Disabled");
	}

	@EventHandler
	public void onEndSpec(PlayerStopSpectatingEntityEvent e) {
e.setCancelled(true);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("spectate")) {
			BossBar test02=null;
			now.put(sender.getName(), Bukkit.getServer().getPlayer(sender.getName()).getLocation());
			now1.put(sender.getName(), Bukkit.getServer().getPlayer(sender.getName()).getGameMode());
			test02 = this.getServer().createBossBar("§e"+sender.getName()+"§ais Spectates you.", BarColor.RED, BarStyle.SOLID);


			test02.setProgress(1);
			test02.addPlayer(Bukkit.getServer().getPlayer(args[0]));
			test01.put(sender.getName(), test02);
			Bukkit.getServer().getPlayer(sender.getName()).setGameMode(GameMode.SPECTATOR);
			Bukkit.getServer().getPlayer(sender.getName()).setSpectatorTarget((Entity) Bukkit.getServer().getPlayer(args[0]));
			return true;
		}
		if (label.equalsIgnoreCase("unspectate")) {
			if(now!=null) {
			Location now2 =null;
			GameMode now3 = null;
			BossBar now4 = null;
			now2 = now.get(sender.getName());
			now3 = now1.get(sender.getName());
			now4 = test01.get(sender.getName());
			now4.removeAll();
			test01.remove(sender.getName());
			getServer().getPlayer(sender.getName()).setSpectatorTarget(null);
			getServer().getPlayer(sender.getName()).teleport(now2);
			getServer().getPlayer(sender.getName()).setGameMode(now3);

			return true;
		}
		}
		return true;
		}
}
