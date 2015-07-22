package map;

import abilities.Proyectile;
import players.LifeObject;
import players.Player;
import setup.myConstants;
import enemies.Enemy;

public abstract class Terrain extends Physics implements myConstants {
	LifeObject lo;
	Proyectile py;
	
	public boolean isEmpty(LifeObject lo) {
		return isEmpty();
	}
	
	public boolean isEmpty(){
		return empty;
	}
		
	public void isEmpty(boolean b, LifeObject lo) {
		empty=b;
		this.lo=lo;
		if(empty==false && hasProy() && lo.isEnemy()){
			py.targetHit();
			lo.lowerHP(py.getADdmg(), py.getAPdmg(), py.getMap(),py.getPlayer());
		}
	}
	
	public LifeObject getLo() {return lo;}
	
	@Override
	public boolean hasNPC() {
		return false;
	}
	public void openMenu(Player p) {}
	
	public boolean hasProy(){
		return proy;
	}
	
	public void hasProy(boolean b, Proyectile py){
		proy=b;
		this.py=py;
	}
	
	public boolean hasEnemy(){
		if(lo==null || !lo.isEnemy())
			return false;
		return true;
	}
	
	public void attack(int ADdmg, int APdmg, Maps map, Player p){
		if(lo!=null){
			lo.lowerHP(ADdmg, APdmg, map, p);
			if(lo.isEnemy())
				map.getInfo().setEnemyInfo((Enemy)lo, true);
		}
	}
	
}
