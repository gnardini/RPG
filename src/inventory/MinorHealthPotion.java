package inventory;

import java.awt.Graphics;

import players.Player;

public class MinorHealthPotion extends HealthPotion {

	
	public MinorHealthPotion(Player p){
		super(p);
		heal=2;
		quant=1;
	}
	
	public void buyNew(){
		quant++;
	}
	
	public void use(){
		if(quant>0){
			quant--;
			super.use();
		}
	}
	
	
	public boolean isMinorHealthPotion() {return true;}
	public boolean isMinorManaPotion() {return false;}
	
	@Override
	public boolean isEmpty() {
		return quant==0;
	}
	
	public void drawIt(Graphics g, int x, int y){
		if(quant>0){
			g.drawString(quant+"x Minor Health Potion", x, y);
		}
	}
	
}
