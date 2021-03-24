package ml.lbplugins.cash.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import ml.lbplugins.cash.Main;


public class MySQL {

	public static Connection con = null;

	public static void open() {

		String url = "jdbc:mysql://" + Main.getInstance().getConfig().getString("MySQL.Host") + ":"
				+ Main.getInstance().getConfig().getString("MySQL.Porta") + "/"
				+ Main.getInstance().getConfig().getString("MySQL.Database");
		String user = Main.getInstance().getConfig().getString("MySQL.Usuario");
		String password = Main.getInstance().getConfig().getString("MySQL.Senha");

		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("[MySQL-Cash] SUCESSO ao Conectar ao MySQL");
		} catch (SQLException e) {
			System.out.println("[MySQL-Cash] FALHA ao Conectar ao MySQL!");
			Bukkit.getServer().getPluginManager().disablePlugin(Main.getInstance());
		}
	}

	public static void close() {
		if (con != null) {
			try {
				con.close();
				System.out.println("MySQL] SUCESSO ao Desconectar ao MySQL!");
			} catch (Exception e) {
				System.out.println("MySQL] FALHA ao Desconectar ao MySQL!");
			}
		}
	}

	public static void createTable() {
		if (con != null) {

			PreparedStatement stm = null;

			try {
				stm = con.prepareStatement(
						"CREATE TABLE IF NOT EXISTS `Cash`(`player` VARCHAR(24), `Valor` INTEGER)");
				stm.executeUpdate();
				System.out.println("[MySQL] SUCESSO ao criar Tabela do MySQL!");
			} catch (SQLException e) {
				System.out.println("[MySQL] FALHA ao criar Tabela do MySQL!");
				e.printStackTrace();
			}
		}
	}

	public static boolean contains(String player) {

		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("SELECT * FROM `Cash` WHERE `player` = ?");
			stm.setString(1, player);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("[MySQL] FALHA ao MySQL - Reportar erro (1)!");
			return false;
		}
	}

	public static void setPlayer(String player, int valor) {

		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement(
					"INSERT INTO `Cash`(`player`, `Valor`) VALUES (?,?)");
			stm.setString(1, player);
			stm.setInt(2, valor);
			stm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[MySQL] FALHA ao MySQL - Reportar erro (2)!");
			e.printStackTrace();
		}
	}

	public static void updatevalor(String p, int valor) {
		if (contains(p)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("UPDATE `Cash` SET `Valor` = ? WHERE `player` = ?");
				stm.setInt(1, valor);
				stm.setString(2, p);
				stm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("[MySQL] FALHA ao MySQL - Reportar erro (3)!");
				e.printStackTrace();
			}
		} else {

		}
	}


	public static int get(String player) {
		if (contains(player)) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("SELECT * FROM `Cash` WHERE `player` = ?");
				stm.setString(1, player);
				ResultSet rs = stm.executeQuery();
				while (rs.next()) {
						return rs.getInt("Valor");
				}
				return 0;
			} catch (SQLException e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static void delete(String player) {
		if (contains(player)) {

			PreparedStatement stm = null;

			try {
				stm = con.prepareStatement("DELETE FROM `Cash` WHERE `player` = ?");
				stm.setString(1, player);
				stm.executeUpdate();
			} catch (SQLException e) {
				System.out.println("[MySQL] FALHA ao MySQL - Reportar erro (4)!");
				e.printStackTrace();
			}
		}

}
}