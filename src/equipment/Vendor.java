package equipment;

import java.awt.Graphics;
import java.awt.Image;

import map.NPC;
import players.LifeObject;
import players.Player;

public abstract class Vendor extends NPC {

	String[] lista;
	String[] msg;
	
	@Override
	public boolean isEmpty(LifeObject lo) {
		return false;
	}
	
	@Override
	public abstract void openMenu(Player p);
	
	public void paintSelf(Graphics g, int x, int y, Image[] img){
		g.drawImage(img[12],10+x*ESCALA,10+y*ESCALA,null);
		/*
		g.setColor(Color.ORANGE);
		g.fillRect(10+x*ESCALA, 10+y*ESCALA, ESCALA, ESCALA);
		g.setColor(Color.CYAN);
		g.fillRoundRect(13+x*ESCALA, 13+y*ESCALA, ESCALA-6, ESCALA-6,4,4);*/
	}
}
