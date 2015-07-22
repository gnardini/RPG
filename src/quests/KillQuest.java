package quests;

import players.Player;

public class KillQuest extends Quest{

	private String enemy;
	private int total,current;
	
	public KillQuest(Player p, String name, int exp, int gold, String enemy, int num){
		super(p,name,exp,gold);
		this.enemy=enemy;
		total=num;
		current=0;
	}
		
	public void enemyKilled(String name){
		if(name==enemy){
			current++;
			if(current>=total){
				current=total;
				questComplete();
			}
		}
	}
}
