package me.companies.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.companies.main.Primal;



public class CommandSetBalance implements CommandExecutor{

	Primal plugin;
	public CommandSetBalance(Primal instance) {
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
		if(cmd.getLabel().equalsIgnoreCase("setbal")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error:&c You have the incorrect usuage!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: /setbal <player> <amount>"));
			} else if (args.length > 1){
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour target &a" + args[0] + " &cis not online!"));
				} else if (args.length < 3){
						plugin.setMoney(target, Integer.parseInt(args[1]));
						plugin.saveConfig();
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "You just added $" + args[1] + " to " + target.getName() + "'s Account!"));
					}
				}
			}
		
		return false;
	}
}
