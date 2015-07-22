package map;

import java.awt.Graphics;
import java.awt.Image;

import players.LifeObject;
import players.Player;
import setup.myConstants;
import abilities.Proyectile;

public abstract class NPC extends Physics implements myConstants {
	
	protected String name;
	
	public boolean isEmpty() {return false;}
	public boolean isEmpty(LifeObject lo) {return false;}
	public boolean hasEnemy() {return false;}
	public void isEmpty(boolean b, LifeObject lo) {}
	public boolean hasProy() {return false;	}
	public void hasProy(boolean b, Proyectile py) {}

	@Override
	public boolean hasNPC() {
		return true;
	}
	
	@Override
	public abstract void paintSelf(Graphics g, int x, int y, Image[] img);

	public LifeObject getLo() {return null;}
	public void snare(int ADdmg, int APdmg, Maps map,Player p, int dur){}
}
