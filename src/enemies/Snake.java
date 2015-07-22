package enemies;

import java.awt.Graphics;
import java.awt.Image;

import map.Maps;
import players.Player;
import setup.myConstants;

public class Snake extends Enemy implements myConstants {

	private static final long serialVersionUID = 1L;

	public Snake(int HP){
		super(HP);
		name="Sand Snake";
		AD=20;
		AP=0;
		armor=0;
		MR=0;
	}
	
	public long getTimeLeft(){
		return 0;
	}
	
	public void collectReward(Player p, Maps map){
		p.enemyDied("Snake");
		p.setExp(35);
		p.addGold(35);
	}
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[10],10+x*ESCALA,10+y*ESCALA,null);
		/*g.setColor(Color.MAGENTA);
		g.fillRect(12+x*ESCALA, 12+y*ESCALA, ESCALA-4, ESCALA-4);	*/
		super.paintSelf(g,x,y,ESCALA,0);
	}
}
