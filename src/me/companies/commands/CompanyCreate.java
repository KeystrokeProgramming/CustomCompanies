package me.companies.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.companies.main.Primal;



public class CompanyCreate implements CommandExecutor{

	Primal plugin;
	public CompanyCreate(Primal instance) {
		plugin = instance;
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getLabel().equalsIgnoreCase("company")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You must be a player, and in online game to use this command");
			}
			if(args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error:&c You have the incorrect usuage!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: /company help"));
			} else if (args.length == 1) {
				if(args[0].equalsIgnoreCase("create")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error:&c You have the incorrect usuage!"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: /company help"));
				}else if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l-------[Companies]-------"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l/company create <name> - Creates Company Name"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l/company close - Closes Company"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l/company balance - Shows your Company Balance"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l/company info <name> - Shows Company Information"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&l---------------------"));
					
				}else if (args[0].equalsIgnoreCase("balance")) {
					if (!plugin.getConfig().contains(p.getUniqueId().toString() + ".Company.Value")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4ERROR:&c You don't own a Company!"));
					}else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Your company value is $" + plugin.getCompanyValue(p) + ""));
					}
				}else if(args[0].equalsIgnoreCase("close")) {
					if (plugin.getConfig().contains(p.getUniqueId().toString() + ".Company.Value")) {
						plugin.closeCompany(p);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have sucessfully closed your company!"));
					}
				}else if(args[0].equalsIgnoreCase("info")) {
					if (!plugin.getConfig().contains(p.getUniqueId().toString() + ".Company.Value")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4ERROR:&c You don't own a Company!"));
					}else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l------[Company Info]------"));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCompany Name: " + plugin.getCompanyName(p)));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCompany Balance:" + plugin.getCompanyValue(p)));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aNumber of Employees: " + plugin.getCompanyEmployees(p)));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aNames of Employees: " + plugin.getCompanyEmployeeNames(p)));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l------[Company Info]------"));
					}
				}
			}else if (args.length == 2) {
				if(args[1].equalsIgnoreCase("add")){
					if (!plugin.getConfig().contains(p.getUniqueId().toString() + ".Company.Value")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4ERROR:&c You don't own a Company!"));
					}else {
						Bukkit.broadcastMessage("test");
					}
					return true;
				}
					if(isInt(args[1]) == true) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Your Company Must be a name. Numeric vaules are NOT Allowed!"));
						return true;
					}else {
						plugin.createCompany(p, args[1]);
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have created a Company!"));
						return true;

					}
				}else if(args.length == 3) {
					if(isInt(args[1]) == true) {
						plugin.economy.getBalance(p);
						if(plugin.economy.getBalance(p) <= Integer.parseInt(args[3])){
							Bukkit.broadcastMessage("TEst");
					}else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4ERROR:&c You don't have enough money to put that into your Company Balance"));
					}
				}
			}else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "You must add money using numbers!"));
				return true;
			}
		}
		return false;
	}
}