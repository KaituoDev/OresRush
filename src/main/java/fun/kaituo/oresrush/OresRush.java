package fun.kaituo.oresrush;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fun.kaituo.GameUtils;
import fun.kaituo.event.PlayerChangeGameEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static fun.kaituo.GameUtils.unregisterGame;
import static fun.kaituo.GameUtils.world;

public class OresRush extends JavaPlugin implements Listener {
    List<Player> players;

    public static OresRushGame getGameInstance() {
        return OresRushGame.getInstance();
    }

    @EventHandler
    public void onStartButtonClicked(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK &&
            event.getClickedBlock().getType() == Material.OAK_BUTTON &&
            event.getClickedBlock().getLocation().equals(new Location(world, -3000, 41, -2998))) {
            event.getPlayer().sendMessage("button clicked");
            OresRushGame.getInstance().startGame();
        }
    }

    public void onEnable() {
        players = new ArrayList<>();
        Bukkit.getPluginManager().registerEvents(this, this);
        GameUtils.registerGame(getGameInstance());
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        HandlerList.unregisterAll((Plugin) this);
        if (players.size() > 0) {
            for (Player p : players) {
                p.teleport(new Location(world, 0.5, 89.0, 0.5));
                Bukkit.getPluginManager().callEvent(new PlayerChangeGameEvent(p, getGameInstance(), null));
            }
        }
        unregisterGame(getGameInstance());
    }
}
