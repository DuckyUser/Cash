package ml.lbplugins.cash.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import ml.lbplugins.cash.Main;

public class MenuSHOP {

	public static void OpenSHOP(Player p) {

		Inventory inv = Bukkit.createInventory(null, 6 * 9, "§8SHOP");

		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
		ItemMeta glassm = glass.getItemMeta();
		glassm.setDisplayName("§8-");
		glass.setItemMeta(glassm);

		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(3, glass);
		inv.setItem(5, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		inv.setItem(9, glass);
		inv.setItem(17, glass);
		inv.setItem(53, glass);
		inv.setItem(52, glass);
		inv.setItem(46, glass);
		inv.setItem(45, glass);
		inv.setItem(44, glass);
		inv.setItem(36, glass);

		ItemStack f = new ItemStack(Material.ARROW);
		ItemMeta fm = f.getItemMeta();
		fm.setDisplayName("§cFechar");
		f.setItemMeta(fm);

		inv.setItem(49, f);

		ItemStack c = new ItemStack(Material.GOLD_INGOT);
		ItemMeta cm = c.getItemMeta();
		cm.setDisplayName("§aCoins");
		c.setItemMeta(cm);

		ItemStack vip = new ItemStack(Material.NETHER_STAR);
		ItemMeta vipm = vip.getItemMeta();
		vipm.setDisplayName("§6VIPs");
		vip.setItemMeta(vipm);

		ItemStack perm = new ItemStack(Material.PAPER);
		ItemMeta permm = perm.getItemMeta();
		permm.setDisplayName("§bPermissões");
		perm.setItemMeta(permm);

		@SuppressWarnings("deprecation")
		ItemStack ca = new ItemStack(Material.getMaterial(397), 1, (short) 3);
		SkullMeta cam = (SkullMeta) ca.getItemMeta();
		cam.setOwner(p.getName());
		cam.setDisplayName("§b" + p.getName());
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§aCash:§f " + Main.cash.get(p.getName()));
		cam.setLore(lore);
		ca.setItemMeta(cam);

		inv.setItem(4, ca);
		inv.setItem(29, c);
		inv.setItem(31, vip);
		inv.setItem(33, perm);

		p.openInventory(inv);

	}

	
	@SuppressWarnings("deprecation")
	public static void OpenSHOPPerm(Player p) {

		Inventory inv = Bukkit.createInventory(null, 6 * 9, "§8SHOP - Perm");

		for (String itens : Main.getInstance().getConfig().getConfigurationSection("Perm.").getKeys(false)) {

			String nome = Main.getInstance().getConfig().getString("Perm." + itens + ".Nome").replace("&", "§");
			int id = Main.getInstance().getConfig().getInt("Perm." + itens + ".ID");
			int slot = Main.getInstance().getConfig().getInt("Perm." + itens + ".Slot");
			ArrayList<String> lore = new ArrayList<String>();
			for (String lo : Main.getInstance().getConfig().getStringList("Perm." + itens + ".Lore")) {
				lore.add(lo.replace("&", "§"));
			}

			ItemStack i = new ItemStack(Material.getMaterial(id));
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(nome);
			im.setLore(lore);
			i.setItemMeta(im);

			inv.setItem(slot, i);

		}
		ItemStack fechar = new ItemStack(Material.ARROW);
		ItemMeta fecharm = fechar.getItemMeta();
		fecharm.setDisplayName("§cFechar");
		fechar.setItemMeta(fecharm);
		
		inv.setItem(49, fechar);
		
		p.openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	public static void OpenSHOPCoins(Player p) {

		Inventory inv = Bukkit.createInventory(null, 6 * 9, "§8SHOP - Coins");

		for (String itens : Main.getInstance().getConfig().getConfigurationSection("Coins.").getKeys(false)) {

			String nome = Main.getInstance().getConfig().getString("Coins." + itens + ".Nome").replace("&", "§");
			int id = Main.getInstance().getConfig().getInt("Coins." + itens + ".ID");
			int slot = Main.getInstance().getConfig().getInt("Coins." + itens + ".Slot");
			ArrayList<String> lore = new ArrayList<String>();
			for (String lo : Main.getInstance().getConfig().getStringList("Coins." + itens + ".Lore")) {
				lore.add(lo.replace("&", "§"));
			}

			ItemStack i = new ItemStack(Material.getMaterial(id));
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(nome);
			im.setLore(lore);
			i.setItemMeta(im);

			inv.setItem(slot, i);

		}
		ItemStack fechar = new ItemStack(Material.ARROW);
		ItemMeta fecharm = fechar.getItemMeta();
		fecharm.setDisplayName("§cFechar");
		fechar.setItemMeta(fecharm);
		
		inv.setItem(49, fechar);
		
		p.openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	public static void OpenSHOPVIP(Player p) {

		Inventory inv = Bukkit.createInventory(null, 6 * 9, "§8SHOP - VIP");

		for (String itens : Main.getInstance().getConfig().getConfigurationSection("Itens.").getKeys(false)) {

			String nome = Main.getInstance().getConfig().getString("Itens." + itens + ".Nome").replace("&", "§");
			int id = Main.getInstance().getConfig().getInt("Itens." + itens + ".ID");
			int slot = Main.getInstance().getConfig().getInt("Itens." + itens + ".Slot");
			ArrayList<String> lore = new ArrayList<String>();
			for (String lo : Main.getInstance().getConfig().getStringList("Itens." + itens + ".Lore")) {
				lore.add(lo.replace("&", "§"));
			}

			ItemStack i = new ItemStack(Material.getMaterial(id));
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(nome);
			im.setLore(lore);
			i.setItemMeta(im);

			inv.setItem(slot, i);

		}
		ItemStack fechar = new ItemStack(Material.ARROW);
		ItemMeta fecharm = fechar.getItemMeta();
		fecharm.setDisplayName("§cFechar");
		fechar.setItemMeta(fecharm);
		
		inv.setItem(49, fechar);
		
		p.openInventory(inv);
	}

}
