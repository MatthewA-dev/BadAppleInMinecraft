package matthewa.badappleinminecraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class StartCommand implements CommandExecutor {
    public Runnable printer;

    public StartCommand() {
        this.printer = (() -> {
            try {
                BufferedReader reader = null;
                String fileName = VideoPlayer.files[VideoPlayer.index];
                File file = new File("plugins\\Video\\" + fileName);
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                reader = new BufferedReader(isr);
                StringBuilder txtbuild = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null)
                    txtbuild.append(line).append("\n");
                reader.close();
                String txt = txtbuild.toString();
                List<String> lines = Arrays.asList(txt.split("\n"));
                Collections.reverse(lines);
                for (int y = 0; y < lines.size(); y++) {
                    for (int x = 0; x < ((String)lines.get(y)).length(); x++) {
                        Location locMod = VideoPlayer.startLocation.clone();
                        locMod.add(x, y, 0.0D);
                        Material blockMaterial = getMatfromstr(Character.toString(((String)lines.get(y)).charAt(x)));
                        BukkitTask t = VideoPlayer.server.getScheduler().runTask(VideoPlayer.self, ());
                        do {

                        } while (t.isCancelled());
                    }
                }
                VideoPlayer.index++;
            } catch (ArrayIndexOutOfBoundsException e) {
                resetTask();
            } catch (IOException e) {
                System.out.println("An IOException occurred.");
                e.printStackTrace();
                resetTask();
            }
        });
    }

    public Material getMatfromstr(String str) {
        if (str.equals("))
        return Material.BLACK_WOOL;
        return Material.WHITE_WOOL;
    }

    public void placeBlock(Material mat, Location loc) {
        loc.getWorld().getBlockAt(loc).setType(mat);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player)sender;
        VideoPlayer.startLocation = player.getLocation();
        if (VideoPlayer.thread != null)
            resetTask();
        VideoPlayer.thread = VideoPlayer.server.getScheduler().runTaskTimerAsynchronously(VideoPlayer.self, this.printer, 0L, 2L);
        return true;
    }

    public static void resetTask() {
        VideoPlayer.thread.cancel();
        VideoPlayer.index = 0;
        VideoPlayer.thread = null;
    }
}
