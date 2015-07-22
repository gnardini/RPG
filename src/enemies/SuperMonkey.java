package enemies;

import java.awt.Graphics;
import java.awt.Image;

import map.Maps;
import players.Player;

public class SuperMonkey extends Enemy {

	private static final long serialVersionUID = 1L;

	public SuperMonkey(int HP){
		super(HP);
		name="Angry Gorila";
		AD=80;	
		armor=22;
		MR=19;
	}
	
	@Override
	public long getTimeLeft() {
		return respawnTime-System.currentTimeMillis();
	}
	
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[7],10+x*ESCALA,10+y*ESCALA,null);
		/*g.setColor(new Color(60,20,20));
		g.fillRect(8+x*ESCALA, 8+y*ESCALA, ESCALA+4, ESCALA+4);*/
		super.paintSelf(g,x,y,ESCALA+4,ESCALA/2);
	}
	
	public void collectReward(Player p, Maps map){
		p.setExp(100);
		p.addGold(125);
		respawnTime=System.currentTimeMillis()+10000;
	}

}
