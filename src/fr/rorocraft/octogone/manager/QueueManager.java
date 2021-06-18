package fr.rorocraft.octogone.manager;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.octogone.arenas.Arena;
import fr.rorocraft.octogone.arenas.ArenaManager;
import fr.rorocraft.octogone.gamestate.GameState;

public class QueueManager extends  BukkitRunnable{
	
	private ArenaManager arenaManager;

	public QueueManager(ArenaManager arenaManager) {
		this.arenaManager = arenaManager;
	}

	@Override
	public void run() {
		if(arenaManager.getPlayersInQueue().size() == 0) return;
		
		// Récupération des arènes
		for(Arena arena : arenaManager.getArenas()) {
			
			// Si la partie n'a pas commencé
			if(arena.isState(GameState.WAITING)) {
				
				//  Récupération des joueur dans la file d'attente
				for(Player player : arenaManager.getPlayersInQueue()) {
					arena.joinPlayerToArena(player, arenaManager);
				}
				
				
			}
			
		}

	}

}
