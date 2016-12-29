package me.companies.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.companies.main.Primal;



public class CommandResetBalance implements CommandExecutor{

	Primal plugin;
	public CommandResetBalance(Primal instance) {
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
		if(cmd.getLabel().equalsIgnoreCase("balreset")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Error:&c You have the incorrect usuage!"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: /balreset <player> <amount>"));
			} else if (args.length > 1){
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour target &a" + args[0] + " &cis not online!"));
				} else if (args.length < 3){
						plugin.resetBalance(target, Integer.parseInt(args[1]));
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "You just set " + target.getName() + "'s Account balance to " + args[1]));
					}
				}
			}
		
		return false;
	}
}
