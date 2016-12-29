package me.goldeconomy.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.goldeconomy.main.Primal;



public class CommandBalance implements CommandExecutor{

	Primal plugin;

	public CommandBalance(Primal instance) {
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getLabel().equalsIgnoreCase("balance")) {
			if(args.length == 0) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have $" + plugin.getMoney(p) + "&a to Spend!"));
			}
		}
		return false;
	}
}
