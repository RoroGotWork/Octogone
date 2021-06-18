package fr.rorocraft.octogone.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {
	
	
	public static Location convertStringToLoc(String worldName, String locationsString) {
		String[] locationsArray = locationsString.split(",");
		
		if(locationsArray.length == 3) {
			return new Location(Bukkit.getWorld(worldName), Double.parseDouble(locationsArray[0]), Double.parseDouble(locationsArray[1]), Double.parseDouble(locationsArray[2]));
		} else if(locationsArray.length == 5) {
			return new Location(Bukkit.getWorld(worldName), Double.parseDouble(locationsArray[0]),Double.parseDouble(locationsArray[1]), Double.parseDouble(locationsArray[2]), Float.parseFloat(locationsArray[3]), Float.parseFloat(locationsArray[4]));
		}
		
		return null;
	}
	
	public String convertLocToString(Location location) {
		
		return location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
	}

}
