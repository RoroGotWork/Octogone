package fr.rorocraft.octogone.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.rorocraft.octogone.Octogone;
import fr.rorocraft.octogone.arenas.Arena;

public class ConfigManager {
	private YamlConfiguration config;
	private File file;
	
	
	public ConfigManager(Octogone octogone) {
		try {
			createArenaFile(octogone);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		
	}
	
	 public YamlConfiguration getConfig() {
			return config;
			 
		 }
	 
	 public void save() throws IOException {
			config.save(file);
		
	 }

	
	
	private void createArenaFile(Octogone octogone) throws IOException {
		
		if(!octogone.getDataFolder().exists()) {
			octogone.getDataFolder().mkdir();
			
			System.out.println("[Octogone] Dossier crée");
		}
		 file = new File(octogone.getDataFolder() + File.separator + "arenas.yml");
		
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("[Octogone] Fichier de config crée");
		}
		
		setupArenaConfig(octogone);
	}


	private void setupArenaConfig(Octogone plugin) {
	config = YamlConfiguration.loadConfiguration(file);
	
	
	if(getConfig().get("arenas") == null) return;
	
	// Récupérations des arènes
			for(String arenaName : getConfig().getConfigurationSection("arenas").getKeys(false)) {
				
				String worldName = getConfig().getString("arenas." + arenaName + ".world");
				List<Location> locations = new ArrayList<>();
				
				// Récupération des position
			for(String positions : getConfig().getConfigurationSection("arenas." + arenaName + ".positions").getKeys(false)) {
				
				
				
				// Ajout des locations 
				String position = getConfig().getString("arenas." + arenaName + ".positions." + positions);
				locations.add(LocationUtils.convertStringToLoc(worldName, position ));
				
				System.out.println("[Octogone] Une arène trouvée");
				

				
			}
			
			
			Arena arena  = new Arena(plugin, arenaName, locations);
			plugin.getArenaManager().getArenas().add(arena);
			
			
			}
		
	}
	
	
	
	
	
}
