package map;

import players.Player;
import quests.KillQuest;
import quests.QuestGiver;
import setup.Info;
import setup.myConstants;
import enemies.Enemies;
import enemies.EnemiesForest;
import enemies.EnemiesFreila;


public class Maps implements myConstants {
	
	private int respawnX, respawnY;
	private Physics[][] map;
	private String name;
	private Player p;
	private Enemies en;
	Info info;
	
	public Maps(String name, Info current, Player p){
		this.name=name;
		this.p=p;
		map= new Physics[DIM][DIM];
		info=current;
		MapReader.readMap(name, this);
		map[1][1]=new QuestGiver(new KillQuest(p,"Plague Control",200,700,"Snake", 10 ));
		en=new EnemiesFreila(p,this);
	}
	
	public Physics[][] getMap(){
		return map;
	}
	
	public void setMap(String name){
		en.setEnd(true);
		this.name=name;
		MapReader.readMap(name, this);
		setEnemies();
		setQuestGivers();
	}
	
	private void setEnemies(){
		if(name.equals("Freila")) en=new EnemiesFreila(p,this);
		else if(name.equals("Forest")) en=new EnemiesForest(p,this);
	}
	
	private void setQuestGivers(){
		if(name.equals("Freila")) map[1][1]=new QuestGiver(new KillQuest(p,"Plague Control",200,700,"Snake", 10 ));
		else if(name.equals("Forest")) ;
	}
	
	public void setRespawnX(int x){
		respawnX=x;
	}
	
	public void setRespawnY(int y){
		respawnY=y;
	}
	
	public int getRespawnLocationX(){
		return respawnX;
	}
	
	public int getRespawnLocationY(){
		return respawnY;
	}
	
	public Info getInfo(){
		return info;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
