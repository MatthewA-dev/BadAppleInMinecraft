package matthewa.badappleinminecraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StartCommand.resetTask();
        sender.sendMessage("Stopped Video");
        return true;
    }
}
