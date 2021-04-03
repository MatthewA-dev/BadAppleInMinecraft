package matthewa.badappleinminecraft;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class VideoPlayer extends JavaPlugin {
    public static Location startLocation;

    public static BukkitTask thread = null;

    public static Server server;

    public static Plugin self;

    public static String[] files;

    public static int index;

    public static List<Character> digits = Arrays.asList(new Character[] { Character.valueOf('0'), Character.valueOf('1'), Character.valueOf('2'), Character.valueOf('3'), Character.valueOf('4'), Character.valueOf('5'), Character.valueOf('6'), Character.valueOf('7'), Character.valueOf('8'), Character.valueOf('9') });

    public void onEnable() {
        File videofolder = new File("plugins\\Video");
        files = videofolder.list();
        Comparator<String> list = new Comparator<String>() {
            public int compare(String o1, String o2) {
                StringBuilder b = new StringBuilder();
                for (String str : new String[] { o1, o2 }) {
                    char[] arrayOfChar;
                    int i;
                    byte b1;
                    for (arrayOfChar = str.toCharArray(), i = arrayOfChar.length, b1 = 0; b1 < i; ) {
                        Character chr = Character.valueOf(arrayOfChar[b1]);
                        if (VideoPlayer.digits.contains(chr)) {
                            b.append(chr);
                            b1++;
                        }
                    }
                    b.append(" ");
                }
                String[] strs = b.toString().split(" ");
                if (Integer.parseInt(strs[0]) < Integer.parseInt(strs[1]))
                    return -1;
                return 1;
            }
        };
        files = (String[])Arrays.<String>stream(files).sorted(list).toArray(x$0 -> new String[x$0]);
        self = (Plugin)this;
        server = getServer();
        getCommand("StartVideo").setExecutor(new StartCommand());
        getCommand("StopVideo").setExecutor(new StopCommand());
    }

    public void onDisable() {}
}
