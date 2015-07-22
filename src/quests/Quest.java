package quests;

import players.Player;

public abstract class Quest {

	private boolean complete;
	private String name;
	private int exp,gold;
	private Player p;
	
	public Quest(Player p, String name, int exp, int gold){
		this.p=p;
		this.name=name;
		this.exp=exp;
		this.gold=gold;
	}
	
	public String getName(){
		return name;
	}
	
	public void giveRewards(){
		p.addGold(gold);
		p.setExp(exp);
	}

	public abstract void enemyKilled(String name);
	
	public void questComplete(){
		complete=true;
	}
	
	public boolean isComplete(){
		return complete;
	}
}
