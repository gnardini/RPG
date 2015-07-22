package inventory;

import java.awt.Graphics;

import players.Player;

public class MinorManaPotion extends ManaPotion {

	
	
	public MinorManaPotion(Player p){
		super(p);
		heal=4;
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
	
	
	public boolean isMinorHealthPotion() {return false;}
	public boolean isMinorManaPotion() {return true;}
	
	@Override
	public boolean isEmpty() {
		return quant==0;
	}
	
	public void drawIt(Graphics g, int x, int y){
		if(quant>0){
			g.drawString(quant+"x Minor Mana Potion", x, y);
		}
	}
}
