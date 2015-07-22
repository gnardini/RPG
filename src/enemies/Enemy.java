package enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import players.LifeObject;
import players.Player;
import map.Maps;
import setup.myConstants;

public abstract class Enemy extends LifeObject implements myConstants {
	
	private static final long serialVersionUID = 1L;
	protected long respawnTime;

	private int[] dx={0,0,-1,1};
	private int[] dy={1,-1,0,0};
	
	public Enemy(int HP) {
		super(HP);
	}
	
	
	@Override
	public void die(Player p, Maps map) {
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(true, this);
		collectReward(p, map);
	}
	
	public abstract void collectReward(Player p, Maps map);
	public abstract long getTimeLeft();
	
	
	public void move(Player p, Maps map, int[][] dist){
		Point dir = new Point(0,0);
		int x=12+(int)(pos.getX()-p.getX()), px =(int) p.getX(), y=12+(int)(pos.getY()-p.getY()), py=(int)p.getY();
		if(Math.abs((int)pos.getX()-px)>11 || Math.abs((int)pos.getY()-py)>11 || x<1 || x>23 || y<1 || y>23){
			HP.setLocation(HP.getY(), HP.getY());
			return;
		}
		int i,c,random;
		Random r = new Random();
		random=r.nextInt(4);
		for(c=0; c<4; c++)
		{
			i=(c+random)%4;
			if(dist[x+dir.x][y+dir.y]>dist[x+dx[i]][y+dy[i]]) dir.move(dx[i],dy[i]);
		}
		/*
		if(Math.abs(x-px) > Math.abs(y-py)){
			if(x>px) dir=new Point(-1,0);
			else if(x<px) dir=new Point(1,0);
			else if(y>py) dir=new Point(0,-1);
			else if(y<py) dir=new Point(0,1);
			else dir=new Point(0,0);
		}else if (Math.abs(x-px) < Math.abs(y-py)){
			if(y>py) dir=new Point(0,-1);
			else if(y<py) dir=new Point(0,1);
			else if(x>px) dir=new Point(-1,0);
			else if(x<px) dir=new Point(1,0);
			else dir=new Point(0,0);
		}else{
			if(canMoveInX(map, x,px,y)){
				if(x>px) dir=new Point(-1,0);
				else if(x<px) dir=new Point(1,0);
			}else if(canMoveInY(map,y,py,x)){
				if(y>py) dir=new Point(0,-1);
				else if(y<py) dir=new Point(0,1);
			}else dir=new Point(0,0);
		}*/
		super.move(dir, map, this);
	}
	
	public boolean canMoveInX(Maps map, int x, int px, int y){
		int dir;
		if(x>px) dir=-1;
		else if(x<px) dir=1;
		else return false;
		if(map.getMap()[x+dir][y].isEmpty())
			return true;
		return false;
	}
	
	public boolean canMoveInY(Maps map, int y, int py, int x){
		int dir;
		if(y>py) dir=-1;
		else if(y<py) dir=1;
		else return false;
		if(map.getMap()[x][y+dir].isEmpty())
			return true;
		return false;
	}
	
	public void attack(Player p, Maps map){
		if(isStunned()) return;
		if(p.getX()==(pos.getX()+face.getX()) && p.getY()==(pos.getY()+face.getY()))
			p.lowerHP(AD,0, map, p);
	}
	
	public boolean isEnemy(){
		return true;
	}
	
	public void paintSelf(Graphics g, int x, int y,int len,int big){
		g.setColor(Color.white);
		g.fillRect(10+x*ESCALA, 6-big+y*ESCALA, len, ESCALA/8);
		g.setColor(Color.RED);
		g.fillRect(10+x*ESCALA, 6-big+y*ESCALA, (int)(HP.getX()/HP.getY()*len), ESCALA/8);
	}
	
}
