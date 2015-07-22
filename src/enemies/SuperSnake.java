package enemies;

import java.awt.Graphics;
import java.awt.Image;

import players.Player;
import map.Maps;
import map.Teleport;
import abilities.DrainLife;

public class SuperSnake extends Enemy {

	private static final long serialVersionUID = 1L;
	int contador;
	
	public SuperSnake(int HP){
		super(HP);
		name="Poisonous Snake";
		AD=35;	
		armor=10;
		MR=10;
	}
	
	public void attack(Player p, Maps map){
		if(isStunned()) return;
		if(p.getX()==(pos.getX()+face.getX()) && p.getY()==(pos.getY()+face.getY()))
			contador++;
		if(contador == 3){
			contador=0;
			new DrainLife(0,8,p,p,map);
		}
		super.attack(p, map);
	}
	
	@Override
	public long getTimeLeft() {
		return respawnTime-System.currentTimeMillis();
	}
	
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[17],10+x*ESCALA,10+y*ESCALA,null);
		/*g.setColor(new Color(160,120,120));
		g.fillRect(8+x*ESCALA, 8+y*ESCALA, ESCALA+4, ESCALA+4);*/
		super.paintSelf(g,x,y,ESCALA+4,ESCALA/4);
	}
	
	public void collectReward(Player p, Maps map){
		map.getMap()[98][1]=new Teleport(map,"Forest",50,50);
		map.getMap()[98][98]=new Teleport(map,"Forest",50,50);
		p.setExp(55);
		p.addGold(60);
		respawnTime=System.currentTimeMillis()+10000;
	}
}
