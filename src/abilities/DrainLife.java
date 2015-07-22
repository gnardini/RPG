package abilities;

import players.LifeObject;
import players.Player;
import map.Maps;


public class DrainLife implements Runnable {

	Player p;
	LifeObject rec;
	int AD,AP;
	Maps map;
	
	public DrainLife(int ADdmg, int APdmg, Player p, LifeObject rec, Maps map){
		this.p=p;
		this.rec=rec;
		this.map=map;
		AD=ADdmg;
		AP=APdmg;
		Thread t=new Thread(this,"DrainLife");
		t.start();
	}
	
	@Override
	public void run() {
		try{	
			int contador=0;
			map.getInfo().setInfo("You are POISONED!", true);
			while(contador<5){
				contador++;
				rec.lowerHP(AD, AP, map,p);
				Thread.sleep(800);
			}
			map.getInfo().setInfo("", false);
		}catch(InterruptedException e){}
		
	}
}
