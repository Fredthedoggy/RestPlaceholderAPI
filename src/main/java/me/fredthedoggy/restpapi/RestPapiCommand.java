package me.fredthedoggy.restpapi;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RestPapiCommand implements CommandExecutor {

    private final Restpapi restpapi;

    public RestPapiCommand(Restpapi restpapi) {
        this.restpapi = restpapi;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1 && args[0].equals("reload")) {
            restpapi.webServer.destroy();
            System.out.println("[RestPAPI] Disabled Webserver");
            this.restpapi.getLoader().loadWebServer();
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "RestPAPI Config is being reloaded.");
            }
            System.out.println("[RestPAPI] Reloading Config");
            return true;
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "Run /restpapi reload to Reload RestPAPI");
            } else {
                System.out.println("[RestPAPI] Run /restpapi reload to Reload RestPAPI");
            }
            return true;
        }
    }
}
