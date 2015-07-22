package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import abilities.Proyectile;
import players.LifeObject;
import players.Player;
import setup.myConstants;

public class Teleport extends Physics implements myConstants {

	String name;
	int x, y;
	Maps map;
	
	public Teleport(Maps map, String name, int x, int y){
		this.name=name;
		this.map=map;
		this.x=x;
		this.y=y;
	}
	
	public void setMap(Player p, Maps map){
		map.setMap(name);
		map.getMap()[(int)p.pos.getX()][(int)p.pos.getY()].isEmpty(true,null);
		p.pos.setLocation(x, y);
		map.getMap()[x][y].isEmpty(false,p);
	}
	
	public boolean isEmpty(LifeObject lo) {
		if(lo.isEnemy()) return false;
		setMap((Player)lo,map);
		return false;
	}
	
	
	public boolean isEmpty() {return false;}
	public void snare(int ADdmg, int APdmg, Maps map, Player p, int dur) {}
	public LifeObject getLo() {return null;}
	public boolean hasEnemy() {return false;}
	public boolean hasNPC() {return false;}
	public void openMenu(Player p) {}
	public void isEmpty(boolean b, LifeObject lo) {}
	public boolean hasProy() {return false;}
	public void hasProy(boolean b, Proyectile py) {}

	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.setColor(Color.CYAN);
		g.fillRect(10+x*ESCALA, 10+y*ESCALA, ESCALA, ESCALA);
		g.setColor(Color.BLUE);
		g.fillOval(18+x*ESCALA, 10+y*ESCALA, ESCALA/2, ESCALA);

	}

}
