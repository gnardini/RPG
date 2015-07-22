package quests;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;

import map.NPC;
import players.Player;

public class QuestGiver extends NPC{
	
	Quest q;
	
	public QuestGiver(Quest q){
		this.q=q;
	}
	
	public void openMenu(Player p) {
		Map<String,Quest> quests= p.getQuests();
		if(!quests.containsKey(q.getName())){
			quests.put(q.getName(), q);
		}else{
			Quest current=quests.get(q.getName());
			if(current.isComplete()){
				current.giveRewards();
			}
		}
	}

	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[12],10+x*ESCALA,10+y*ESCALA,null);
	}
}
