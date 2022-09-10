package fun.kaituo.oresrush;

import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.ProtocolLibrary;
import fun.kaituo.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static fun.kaituo.GameUtils.world;

public class OresRushGame extends Game implements Listener {
    private ArrayList<Player> redTeam;
    private ArrayList<Player> blueTeam;
    private Scoreboard mainScoreboard;
    private Scoreboard sidebarScoreboard;
    //private
    
    private static final OresRushGame instance = new OresRushGame((OresRush)Bukkit.getPluginManager().getPlugin("OresRush"));
    
    
    private OresRushGame(OresRush plugin) {
        this.plugin = plugin;
        players = plugin.players;
        initializeGame(plugin, "MyGame", "§eMyGame", new Location(world, 0, 89, 0), new BoundingBox(0, 0, 0, 0, 0, 0));
        initializeButtons(new Location(world, 0, 0, 0), BlockFace.NORTH, new Location(world, 0, 0, 0), BlockFace.EAST);
        initializeGameRunnable();
        redTeam = new ArrayList<>();
        blueTeam = new ArrayList<>();
        mainScoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        sidebarScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        if (mainScoreboard.getTeam("orushred") == null) {
            mainScoreboard.registerNewTeam("orushred");
            mainScoreboard.getTeam("orushred").setColor(ChatColor.RED);
        }
        if (mainScoreboard.getTeam("orushblue") == null) {
            mainScoreboard.registerNewTeam("orushblue");
            mainScoreboard.getTeam("orushblue").setColor(ChatColor.BLUE);
        }
    }
    
    public static OresRushGame getInstance() {
        return instance;
    }
    
    
    @Override
    protected void initializeGameRunnable() {
        gameRunnable = () -> {
            players.addAll(getPlayersNearHub(10, 10, 10));
            for (String playerName : mainScoreboard.getTeam("orushred").getEntries()) {
                Player player = Bukkit.getPlayer(playerName);
                if (player != null) {
                    redTeam.add(player);
                }
            }
            for (String playerName : mainScoreboard.getTeam("orushblue").getEntries()) {
                Player player = Bukkit.getPlayer(playerName);
                if (player != null) {
                    blueTeam.add(player);
                }
            }
            //generateWorld();
        };
    }
    
    @Override
    protected void savePlayerQuitData(Player p) throws IOException {
    
    }
    
    
    @Override
    protected void rejoin(Player player) {
    
    }
    
    /*
    private boolean generateWorld() {
    
    }
     */
}
