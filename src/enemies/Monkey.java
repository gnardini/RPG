package enemies;

import java.awt.Graphics;
import java.awt.Image;

import players.Player;
import setup.myConstants;
import map.Maps;

public class Monkey extends Enemy implements myConstants {

	private static final long serialVersionUID = 1L;

	public Monkey(int HP){
		super(HP);
		name="Kitten";
		AD=50;
		AP=0;
		armor=17;
		MR=15;
	}
	
	public long getTimeLeft(){
		return 0;
	}
	
	public void collectReward(Player p, Maps map){
		p.setExp(65);
		p.addGold(80);
	}
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[13],10+x*ESCALA,10+y*ESCALA,null);
		/*g.setColor(new Color(100,0,0));
		g.fillRoundRect(12+x*ESCALA, 12+y*ESCALA, ESCALA-4, ESCALA-4,ESCALA/8,ESCALA/8);*/	
		super.paintSelf(g,x,y,ESCALA,0);
	}

}
