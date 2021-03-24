package ml.lbplugins.cash.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ml.lbplugins.cash.Main;

public class PutCache implements Listener {

	@EventHandler
	public void onJOin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Main.cash.containsKey(p.getName())) {
			return;
		}
		if (MySQL.contains(p.getName())) {
			int qnt = MySQL.get(p.getName());
			Main.cash.put(p.getName(), qnt);
		} else {
			Main.cash.put(p.getName(), 0);
		}
	}

}
