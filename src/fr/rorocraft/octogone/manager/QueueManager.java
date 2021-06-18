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
		
		// R�cup�ration des ar�nes
		for(Arena arena : arenaManager.getArenas()) {
			
			// Si la partie n'a pas commenc�
			if(arena.isState(GameState.WAITING)) {
				
				//  R�cup�ration des joueur dans la file d'attente
				for(Player player : arenaManager.getPlayersInQueue()) {
					arena.joinPlayerToArena(player, arenaManager);
				}
				
				
			}
			
		}

	}

}
