package randy.epicquest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import randy.filehandlers.SaveLoader;

public class EpicSystem {
	
	public static List<EpicPlayer> playerList = new ArrayList<EpicPlayer>();
	public static List<EpicSign> signList = new ArrayList<EpicSign>();
	private static ArrayList<Location> blockedList = new ArrayList<Location>();
	public static HashMap<Location, EpicPlayer> furnaceList = new HashMap<Location, EpicPlayer>();
	
	static int questLimit = 10;
	static int time = 0;
	static int startTime = 0;
	static int dailyLimit = 10;
	static int saveTime = 0;
	static int maxPartySize = 5;
	
	public static void setQuestLimit(int limit){ questLimit = limit; }
	public static void setTime(int newTime){ time = newTime; startTime = newTime; }
	public static void setDailyLimit(int limit){ dailyLimit = limit; }
	public static void setPlayerList(List<EpicPlayer> newPlayerList) { playerList = newPlayerList; }
	public static void setSignList(List<EpicSign> newSignList) { signList = newSignList; }
	public static void setSaveTime(int time){ saveTime = time; }
	public static void setBlockList(ArrayList<Location> newBlockList) { blockedList = newBlockList; }
	public static void setMaxPartySize(int size){ maxPartySize = size; }
	
	public static int getQuestLimit(){ return questLimit; }
	public static int getTime() { return time; }
	public static int getDailyLimit() { return dailyLimit; }
	public static List<EpicPlayer> getPlayerList() { return playerList; }
	public static List<EpicSign> getSignList() { return signList; }
	public static int getSaveTime(){ return saveTime; }
	public static int getStartTime() { return startTime; }
	public static ArrayList<Location> getBlockList(){ return blockedList; }
	public static int getMaxPartySize() { return maxPartySize; }
	
	public static void modifyTime(int newTime) { time += newTime; }
	public static void modifySaveTime(int newSaveTime) { saveTime += newSaveTime; }
	
	public static EpicPlayer getEpicPlayer(String name){
		
		//Search list for the player
		for(int i = 0; i < playerList.size(); i++){
			if(playerList.get(i).getPlayerName().equals(name)){ return playerList.get(i); }
		}
		
		//Player is not yet loaded or doesn't exist
		SaveLoader.loadPlayer(name);
		if(playerList.get(playerList.size() - 1).getPlayerName().equals(name)){ return playerList.get(playerList.size() - 1); }
		
		//Player doesn't exist
		return null;
	}
	
	public static EpicPlayer getEpicPlayer(Player player){
		
		String name = player.getName();
		
		return getEpicPlayer(name);
	}
	
	public static void addFirstStart(String playerName){
		
		File savefile = new File("plugins" + File.separator + "EpicQuest" + File.separator + "Players" + File.separator + playerName + ".yml");

		if(savefile.exists()){
			List<EpicPlayer> playerlist = EpicSystem.playerList;
			boolean playerfound = false;
			for(int i = 0; i < playerlist.size(); i++){
				if(playerlist.get(i).getPlayerName().equalsIgnoreCase(playerName)){
					playerfound = true;
					break;
				}
			}
			if(!playerfound)
				SaveLoader.loadPlayer(playerName); 
		}else{
			playerList.add(new EpicPlayer(playerName));
		}
	}
}
