package inventory;

import java.awt.Graphics;

import players.Player;

public abstract class Inventory {
	String name;
	Player p;
	
	public Inventory(Player pl){
		p=pl;
	}
	
	public String getName(){
		return name;
	}

	public abstract boolean isMinorHealthPotion();
	public abstract boolean isMinorManaPotion();
	public abstract boolean isEmpty();
	public abstract void use();
	public abstract void buyNew();
	public abstract void drawIt(Graphics g, int x, int y);
}
