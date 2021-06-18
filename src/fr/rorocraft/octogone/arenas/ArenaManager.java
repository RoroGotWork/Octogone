package fr.rorocraft.octogone.arenas;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class ArenaManager {
	
	private List<Arena> arenas;
	private List<Player> playersInQueue;
	private List<Player> frozenPlayers;
	
	public ArenaManager() {
		arenas = new ArrayList<>();
		playersInQueue = new ArrayList<>();
		frozenPlayers = new ArrayList<>();
	}

	public List<Arena> getArenas() {
		return arenas;
	}
	
	public Arena getArenaByPlayer(Player p) {
		for(Arena arena : getArenas()) {
			if(arena.getPlayers().contains(p)) {
				return arena;
			}
		}
		
		return null;
	}

	public List<Player> getPlayersInQueue() {
		return playersInQueue;
	}
	
	public List<Player> getFrozenPlayers(){
		return frozenPlayers;
	}
	
	
	

}
