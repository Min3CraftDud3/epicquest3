package randy.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import randy.epicquest.EpicPlayer;
import randy.epicquest.EpicSystem;

public class PartyMessage implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		EpicPlayer epicPlayer = EpicSystem.getEpicPlayer(player.getName());
		
		if(epicPlayer.partyChat){
			epicPlayer.getParty().sendMessage(epicPlayer, event.getMessage());
			event.setCancelled(true);
		}
	}
}
