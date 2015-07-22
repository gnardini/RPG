package inventory;

import java.awt.Point;

import players.Player;

public abstract class HealthPotion extends Inventory implements Runnable {

	int heal;
	int quant;
	
	public HealthPotion(Player p){
		super(p);
	}
	
	public void use(){
		Thread t= new Thread(this,"Health Potion");
		t.start();
	}
	
	public void run() {
		for(int i=0 ; i<10 && p.HP.getX()<p.HP.getY() ; i++){
			try{
			p.HP=new Point((int)p.HP.getX()+heal,(int)p.HP.getY());
			Thread.sleep(1000);
			}catch(InterruptedException e){}
		}
		if( p.HP.getX()>p.HP.getY())
			p.HP.setLocation(p.HP.getY(), p.HP.getY());
	}
}
