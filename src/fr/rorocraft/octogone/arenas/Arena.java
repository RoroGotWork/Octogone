package fr.rorocraft.octogone.arenas;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.rorocraft.octogone.Octogone;
import fr.rorocraft.octogone.gamestate.GameState;

public class Arena {
	private Octogone plugin;
	private List<Player> players;
	private List<Location> positions;
	private List<Location> freePositions;
	private GameState gameState;
	private String name;
	private int timeRemaining;
	
	
	
	
	public Arena(Octogone plugin, String name, List<Location> positions) {
		this.plugin = plugin;
		this.name = name;
		this.positions = positions;
		this.freePositions = positions;
		this.players = new ArrayList<>();
		this.gameState = GameState.WAITING;
		this.timeRemaining = 30;
		
	}



	public List<Player> getPlayers() {
		return players;
	}



	public List<Location> getPositions() {
		return positions;
	}

	
	
	public List<Location> getFreePositions() {
		return freePositions;
	}


	

	public void setState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public boolean isState(GameState gameState) {
		return this.gameState == gameState;
	}
	
	
	// Le joueur rejoint la partie
	public void joinPlayerToArena(Player player, ArenaManager arenaManager) {
		
		// Vérification si le joueur est dans une partie/est dans une arène
		if(arenaManager.getArenaByPlayer(player) != null) return;
		if(!arenaManager.getPlayersInQueue().contains(player)) return;
		if(getFreePositions().size() == 0) return;
		
		Location position = getFreePositions().get(0);
		
		// Téléportation du joueur
		player.teleport(position);
		getFreePositions().remove(position);
		player.sendMessage(ChatColor.RED + "Tu as rejoint une arène");
		
		// Mise à jour du status du joueur
		arenaManager.getPlayersInQueue().remove(player);
		arenaManager.getFrozenPlayers().add(player);
		getPlayers().add(player);
		
		
		
	}
	
	// Commencement du compte à rebours
	public void startCountDown() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(getPlayers().size() < 2) cancel();
				
				
				timeRemaining --;
				
				// A modifier 
				if(timeRemaining == 0) {
					for(Player player : getPlayers()) {
						player.sendMessage(ChatColor.RED + "Lancement de la partie");
					}
					cancel();
				}
				
			}
		}.runTaskTimer(plugin, 20, 20);
	}
	
	public String getName() {
		return name;
		
	}

}
