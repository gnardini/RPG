package inventory;

import java.awt.Point;

import players.Player;

public abstract class ManaPotion extends Inventory implements Runnable {

	int heal;
	int quant;
	
	public ManaPotion(Player p){
		super(p);
	}
	
	public void use(){
		Thread t= new Thread(this,"Mana Potion");
		t.start();
	}
	
	public void run() {
		for(int i=0 ; i<10 && p.mana.getX()<p.mana.getY() ; i++){
			try{
			p.mana=new Point((int)p.mana.getX()+heal,(int)p.mana.getY());
			Thread.sleep(1000);
			}catch(InterruptedException e){}
		}
		if( p.mana.getX()>p.mana.getY())
			p.mana.setLocation(p.mana.getY(), p.mana.getY());
	}

}
