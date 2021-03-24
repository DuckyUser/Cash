package ml.lbplugins.cash.manager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import ml.lbplugins.cash.Main;
import ml.lbplugins.cash.shop.MenuSHOP;
import ml.lbplugins.cash.shop.SelectOption;

public class Comandos implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof ConsoleCommandSender || sender instanceof Player) {

			if (cmd.getName().equalsIgnoreCase("shop")) {
				Player p = (Player)sender;
				if (Main.cash.get(p.getName())==null) {
					SelectOption.debug(p);
				}
				MenuSHOP.OpenSHOP(p);
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("cash")) {
				if (args.length == 0) {
					Player p = (Player) sender;
					if (Main.cash.get(p.getName())==null) {
						SelectOption.debug(p);
					}
					p.sendMessage("§aCash: §f" + Main.cash.get(p.getName()));
					return true;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("admin")) {
						if (!sender.hasPermission("cash.admin")) {
							return true;
						}
						sender.sendMessage("§4Cash admin");
						sender.sendMessage("");
						sender.sendMessage("§c/cash add <jogador> <valor> §f - Adicione cash a um jogador");
						sender.sendMessage("§c/cash remove <jogador> <valor> §f - Remova cash a um jogador");
						sender.sendMessage("§c/cash set <jogador> <valor> §f - Defina cash a um jogador");
						sender.sendMessage("§c/cash <jogador> §f - Verifique o cash a um jogador");
						sender.sendMessage("");
						return true;
					}
					String jogador = args[0];
					OfflinePlayer jog = Bukkit.getOfflinePlayer(jogador);
					if (Main.cash.containsKey(jog.getName())) {
						sender.sendMessage(
								"§aO jogador §f" + jog.getName() + "§a possui: " + Main.cash.get(jog.getName()));
					} else {
						if (MySQL.contains(jog.getName())) {
							sender.sendMessage(
									"§aO jogador §f" + jog.getName() + "§a possui: " + MySQL.get(jog.getName()));
							return true;
						}
						sender.sendMessage("§cO jogador §f" + jog.getName() + "§c não possui no banco de dados!");
						return true;
					}
				}
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("set")) {
						if (!sender.hasPermission("cash.admin")) {
							return true;
						}
						OfflinePlayer jog = Bukkit.getOfflinePlayer(args[1]);
						int qnt = Integer.valueOf(args[2]);
						if (Main.cash.containsKey(jog.getName())) {
							Main.cash.put(jog.getName(), qnt);
							sender.sendMessage("§aForam setados§f "+ qnt + "§a de cash para§f " + jog.getName());
						} else {
							if (MySQL.contains(jog.getName())) {
								MySQL.updatevalor(jog.getName(), qnt);
								sender.sendMessage("§aForam setados§f "+ qnt + "§a de cash para§f " + jog.getName());
								return true;
							}
							MySQL.setPlayer(jog.getName(), qnt);
							sender.sendMessage("§aForam setados§f "+ qnt + "§a de cash para§f " + jog.getName());
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("add")) {
						if (!sender.hasPermission("cash.admin")) {
							return true;
						}
						OfflinePlayer jog = Bukkit.getOfflinePlayer(args[1]);
						int qnt = Integer.valueOf(args[2]);
						if (Main.cash.containsKey(jog.getName())) {
							int valor = Main.cash.get(jog.getName()) + qnt;
							Main.cash.put(jog.getName(), valor);
							sender.sendMessage("§aForam adicionados§f "+ qnt + "§a de cash para§f " + jog.getName());
						} else {
							if (MySQL.contains(jog.getName())) {
								int valor2 = MySQL.get(jog.getName()) + qnt;
								MySQL.updatevalor(jog.getName(), valor2);
								sender.sendMessage("§aForam adicionados§f "+ qnt + "§a de cash para§f " + jog.getName());
								return true;
							}
							MySQL.setPlayer(jog.getName(), qnt);
							sender.sendMessage("§aForam adicionados§f "+ qnt + "§a de cash para§f " + jog.getName());
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("remove")) {
						if (!sender.hasPermission("cash.admin")) {
							return true;
						}
						OfflinePlayer jog = Bukkit.getOfflinePlayer(args[1]);
						int qnt = Integer.valueOf(args[2]);
						if (Main.cash.containsKey(jog.getName())) {
							int valor = Main.cash.get(jog.getName()) - qnt;
							Main.cash.put(jog.getName(), valor);
							sender.sendMessage("§aForam removidos§f "+ qnt + "§a de cash para§f " + jog.getName());
						} else {
							if (MySQL.contains(jog.getName())) {
								int valor2 = MySQL.get(jog.getName()) - qnt;
								MySQL.updatevalor(jog.getName(), valor2);
								sender.sendMessage("§aForam removidos§f "+ qnt + "§a de cash para§f " + jog.getName());
								return true;
							}
							sender.sendMessage("§cO jogador §f "+ jog.getName() + "§c não possui conta");
							return true;
						}
					}
				}
			}
		}

		return false;
	}

}
