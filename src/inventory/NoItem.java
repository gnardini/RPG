package inventory;

import java.awt.Graphics;

import players.Player;

public class NoItem extends Inventory {

	public NoItem(Player p){
		super(p);
		name=" ";
	}
	
	
	public boolean isEmpty() {return true;}
	public boolean isMinorHealthPotion() {return false;}
	public boolean isMinorManaPotion() {return false;}
	public void use(){}
	public void buyNew(){}
	public void drawIt(Graphics g, int x, int y) {}
}
