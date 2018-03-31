package com.emeraldquest.emeraldquest.commands;

import com.emeraldquest.emeraldquest.EmeraldQuest;
import org.bukkit.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BanCommand extends CommandAction {
    public boolean run(CommandSender sender, Command cmd, String label, String[] args, Player player) {
        if (args.length==1) {
            String playerName = args[0];
            if (EmeraldQuest.REDIS.exists("uuid:" + playerName)) {
                String uuid = EmeraldQuest.REDIS.get("uuid:" + playerName);
                EmeraldQuest.REDIS.sadd("banlist", uuid);
                Player sentout = Bukkit.getPlayer(playerName);
                if (sentout != null) {
		Location location = Bukkit.getServer().getWorld("world").getSpawnLocation();

    	    	location.setX(100350);
            	location.setY(69);
            	location.setZ(100540);
                sentout.teleport(location);
		sentout.sendMessage(ChatColor.RED + "You have been Banned to HACKER ISLAND!!!");
                }
                sender.sendMessage(ChatColor.GREEN + "Player " + playerName + " is now banned and will server time on HACKER ISLAND");

                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Can't find player " + playerName);
                return true;
            }

        }
        return false;
    }
}
