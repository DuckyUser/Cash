package ml.lbplugins.cash;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import ml.lbplugins.cash.manager.ClanPlaceholder;
import ml.lbplugins.cash.manager.Comandos;
import ml.lbplugins.cash.manager.MySQL;
import ml.lbplugins.cash.manager.PutCache;
import ml.lbplugins.cash.shop.SelectOption;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;
	public static HashMap<String, Integer> cash = new HashMap<String, Integer>();
	public ClanPlaceholder clanPlaceholder;

	@Override
	public void onEnable() {
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		instance = this;
		MySQL.open();
		MySQL.createTable();
		getCommand("cash").setExecutor(new Comandos());
		getCommand("shop").setExecutor(new Comandos());
		getServer().getPluginManager().registerEvents(new PutCache(), this);
		getServer().getPluginManager().registerEvents(new SelectOption(), this);
		getServer().getPluginManager().registerEvents(this, this);
		// if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
		// new MVdWPlaceholders(this).registerPlaceholders();
		// }
		clanPlaceholder = new ClanPlaceholder(this);
	}

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onDisable() {
		for (String c : cash.keySet()) {
			if (MySQL.contains(c)) {
				MySQL.updatevalor(c, cash.get(c));
			} else {
				MySQL.setPlayer(c, cash.get(c));
			}
		}
		MySQL.close();
	}

}
