package fr.rorocraft.octogone;


import org.bukkit.plugin.java.JavaPlugin;


import fr.rorocraft.octogone.arenas.ArenaManager;
import fr.rorocraft.octogone.manager.QueueManager;
import fr.rorocraft.octogone.utils.ConfigManager;


public class Octogone extends JavaPlugin {
	private ConfigManager arenaConfig;
	private ArenaManager arenaManager;
	
	
	@Override
	public void onEnable() {
		System.out.println("[Octogone] Le plugin est pret");
		
		
		
		arenaManager = new ArenaManager();
		
		arenaConfig = new ConfigManager(this);
		
		setupGame();
		
	}
	



	@Override
	public void onDisable() {
		System.out.println("Le plugin s'est éteint");
	}


	private void setupGame() {
		new QueueManager(arenaManager).runTaskTimer(this, 20 * 5, 20 * 5);	
	}


	public ConfigManager getArenaConfig() {
		return arenaConfig;
	}
	
	public ArenaManager getArenaManager() {
		return arenaManager;
	}

}
