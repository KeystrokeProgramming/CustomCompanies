package me.companies.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.companies.commands.CommandBalance;
import me.companies.commands.CommandResetBalance;
import me.companies.commands.CommandSetBalance;
import me.companies.commands.CompanyCreate;
import net.milkbowl.vault.economy.Economy;

public class Primal extends JavaPlugin implements Listener {

	public  Economy eco = null;
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		getCommand("balance").setExecutor(new CommandBalance(this));
		getCommand("setbal").setExecutor(new CommandSetBalance(this));
		getCommand("balreset").setExecutor(new CommandResetBalance(this));
		getCommand("company").setExecutor(new CompanyCreate(this));
	}
	public void onDisable() {

	}

	public String timePlayer(Long joindate) {
		Long now = System.currentTimeMillis();
		Long date = now - joindate; 

		long seconds = date / 1000 % 60;
		long minutes = date / (60 * 1000) % 60;
		long hours = date / (60 * 60 * 1000) % 24;
		long days = date / (24 * 60 * 60 * 1000);

		String fulldate = days+" d "+hours+" h "+minutes+" m "+seconds+" s";
		return fulldate;
	}

	//Add money to Company
	public void addMoney(Player p, int amount) {
		getConfig().getInt(p.getUniqueId().toString() +  ".Company.Value", + amount);
		saveConfig();
	}

	public int getCompanyValue(Player p) {
		return getConfig().getInt(p.getUniqueId().toString() + ".Company.Value");
	}

	//Creates Comapny when player types command (/company create <name>)
	public void createCompany(Player p, String name) {
		getConfig().set(p.getUniqueId().toString() + ".Company", 0);
		getConfig().set(p.getUniqueId().toString() + ".Company.Name", name);
		getConfig().set(p.getUniqueId().toString() + ".Company.Value", 0);
		getConfig().set(p.getUniqueId().toString() + ".Company.Employees", 0);
		saveConfig();
	}
	//Close Company when player types command (/company <close>)
	public void closeCompany(Player p) {
		getConfig().set(p.getUniqueId().toString(), null);
		getConfig().set(p.getUniqueId().toString() + ".Company", null);
		getConfig().set(p.getUniqueId().toString() + ".Company.Name", null);
		getConfig().set(p.getUniqueId().toString() + ".Company.Value", null);
		getConfig().set(p.getUniqueId().toString() + ".Company.Employees", null);
		saveConfig();
	}
	//Set Money
	public void setMoney(Player p, int amount) {
		getConfig().set(p.getUniqueId().toString() + ".Company.Value", getMoney(p) + amount);
		saveConfig();
	}

	//Reset Balance
	public void resetBalance(Player p, int amount) {
		getConfig().set(p.getUniqueId().toString() + ".Company.Value", amount);
		saveConfig();
	}
	//Get Money
	public int getMoney(Player ply) {
		return getConfig().getInt(ply.getUniqueId().toString() + ".Company.Value");
	}

	@EventHandler
	public void signPlace(SignChangeEvent e) {
		if(e.getLine(0).contains("[teleport]")) {
			e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&c[teleport]"));
		}
	}
}
