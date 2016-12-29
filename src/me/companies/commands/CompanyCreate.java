package me.companies.commands;

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
			if(args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error:&c You have the incorrect usuage!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: /company help"));
			} else if (args.length == 1) {
				if(args[0].equalsIgnoreCase("create")) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error:&c You have the incorrect usuage!"));
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: /company help"));
				}else if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage("test /company help");
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
				}
			}else if (args.length == 2) {
				if(isInt(args[1]) == true) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "Your Company Must be a name. Numeric vaules are NOT Allowed!"));
				}else {
					plugin.createCompany(p, args[1]);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have created a Company!"));

				}
			}
		}
		return false;
	}
}