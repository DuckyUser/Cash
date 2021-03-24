package ml.lbplugins.cash.shop;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import ml.lbplugins.cash.Main;
import ml.lbplugins.cash.manager.MySQL;

public class SelectOption implements Listener {

	public static void debug(Player p) {
		if (MySQL.contains(p.getName())) {
			Main.cash.put(p.getName(), MySQL.get(p.getName()));
		} else {
			MySQL.setPlayer(p.getName(), 0);
		}

	}

	@EventHandler
	public void onClickBuy(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§8SHOP - Perm")) {
			e.setCancelled(true);
			if (e.getSlot() == 49) {
				MenuSHOP.OpenSHOP(p);
				return;
			}
			for (String itens : Main.getInstance().getConfig().getConfigurationSection("Perm.").getKeys(false)) {
				if (e.getSlot() == Main.getInstance().getConfig().getInt("Perm." + itens + ".Slot")) {
					int valor = Main.getInstance().getConfig().getInt("Perm." + itens + ".Valor");
					String nome = Main.getInstance().getConfig().getString("Perm." + itens + ".Nome").replace("&", "§");
					if (Main.cash.get(p.getName()) == null) {
						debug(p);
					}
					if (Main.cash.get(p.getName()) < valor) {
						p.sendMessage("§cVocê precisa de §f" + valor + "§c para comprar esse produto!");
						p.closeInventory();
						return;
					}
					int valorq = Main.cash.get(p.getName()) - valor;
					Main.cash.put(p.getName(), valorq);
					for (String cmd : Main.getInstance().getConfig().getStringList("Perm." + itens + ".Comandos")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@jogador", p.getName()));
					}
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("§aO jogador§f " + p.getName() + "§a comprou: " + nome);
					Bukkit.broadcastMessage("");
					p.sendMessage("§eVocê comprou §f" + nome + "§e por §f" + valor + "§e com sucesso!");
					p.closeInventory();
					Random r = new Random();
					Firework fire = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					FireworkMeta firem = fire.getFireworkMeta();
					FireworkEffect eff = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.AQUA)
							.trail(r.nextBoolean()).build();
					firem.addEffect(eff);
					return;
				}
			}

		}
		if (e.getInventory().getTitle().equalsIgnoreCase("§8SHOP - Coins")) {
			e.setCancelled(true);
			if (e.getSlot() == 49) {
				MenuSHOP.OpenSHOP(p);
				return;
			}
			for (String itens : Main.getInstance().getConfig().getConfigurationSection("Coins.").getKeys(false)) {
				if (e.getSlot() == Main.getInstance().getConfig().getInt("Coins." + itens + ".Slot")) {
					int valor = Main.getInstance().getConfig().getInt("Coins." + itens + ".Valor");
					String nome = Main.getInstance().getConfig().getString("Coins." + itens + ".Nome").replace("&",
							"§");
					if (Main.cash.get(p.getName()) == null) {
						debug(p);
					}
					if (Main.cash.get(p.getName()) < valor) {
						p.sendMessage("§cVocê precisa de §f" + valor + "§c para comprar esse produto!");
						p.closeInventory();
						return;
					}
					int valorq = Main.cash.get(p.getName()) - valor;
					Main.cash.put(p.getName(), valorq);
					for (String cmd : Main.getInstance().getConfig().getStringList("Coins." + itens + ".Comandos")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@jogador", p.getName()));
					}
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("§aO jogador§f " + p.getName() + "§a comprou: " + nome);
					Bukkit.broadcastMessage("");
					p.sendMessage("§eVocê comprou §f" + nome + "§e por §f" + valor + "§e com sucesso!");
					p.closeInventory();
					Random r = new Random();
					Firework fire = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					FireworkMeta firem = fire.getFireworkMeta();
					FireworkEffect eff = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.AQUA)
							.trail(r.nextBoolean()).build();
					firem.addEffect(eff);
					return;
				}
			}

		}
		if (e.getInventory().getTitle().equalsIgnoreCase("§8SHOP - VIP")) {
			e.setCancelled(true);
			if (e.getSlot() == 49) {
				MenuSHOP.OpenSHOP(p);
				return;
			}
			for (String itens : Main.getInstance().getConfig().getConfigurationSection("Itens.").getKeys(false)) {
				if (e.getSlot() == Main.getInstance().getConfig().getInt("Itens." + itens + ".Slot")) {
					int valor = Main.getInstance().getConfig().getInt("Itens." + itens + ".Valor");
					String nome = Main.getInstance().getConfig().getString("Itens." + itens + ".Nome").replace("&",
							"§");
					if (Main.cash.get(p.getName()) == null) {
						debug(p);
					}
					if (Main.cash.get(p.getName()) < valor) {
						p.sendMessage("§cVocê precisa de §f" + valor + "§c para comprar esse produto!");
						p.closeInventory();
						return;
					}
					int valorq = Main.cash.get(p.getName()) - valor;
					Main.cash.put(p.getName(), valorq);
					for (String cmd : Main.getInstance().getConfig().getStringList("Itens." + itens + ".Comandos")) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@jogador", p.getName()));
					}
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("§aO jogador§f " + p.getName() + "§a comprou: " + nome);
					Bukkit.broadcastMessage("");
					p.sendMessage("§eVocê comprou §f" + nome + "§e por §f" + valor + "§e com sucesso!");
					p.closeInventory();
					Random r = new Random();
					Firework fire = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
					FireworkMeta firem = fire.getFireworkMeta();
					FireworkEffect eff = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.AQUA)
							.trail(r.nextBoolean()).build();
					firem.addEffect(eff);
					return;
				}
			}

		}

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§8SHOP")) {
			e.setCancelled(true);
			if (e.getSlot() == 29) {
				MenuSHOP.OpenSHOPCoins(p);
				return;
			}
			if (e.getSlot() == 31) {
				MenuSHOP.OpenSHOPVIP(p);
				return;
			}
			if (e.getSlot() == 33) {
				MenuSHOP.OpenSHOPPerm(p);
				return;
			}
			if (e.getSlot() == 49) {
				p.closeInventory();
			}
		}
	}

}
