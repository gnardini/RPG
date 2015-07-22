package players;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;

import setup.myConstants;
import map.Maps;

public abstract class LifeObject implements myConstants, Serializable{

	private static final long serialVersionUID = 1L;
	protected Point face;
	public Point HP;
	protected String name;
	public Point pos;
	protected int AD;
	protected int AP;
	protected int armor;
	protected int MR;
	protected boolean stunned;
	protected boolean snared;
	
	public LifeObject(int HP) {
		this.HP = new Point(HP, HP);
		face=new Point(1,0);
		stunned=false;
		snared=false;
	}
	
	public void move(Point dir, Maps map, LifeObject lo){
		face=dir;
		if(lo.isSnared()) return;
		Point p = new Point((int)(dir.getX()+pos.getX()),(int)(dir.getY()+pos.getY()));
		int x=(int)p.getX(), y=(int)p.getY();
		if(x<0 || x>=DIM || y<0 || y>=DIM )
			return;
		if(!map.getMap()[x][y].isEmpty(lo))
			return;
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(true,null);
		pos.translate((int)dir.getX(), (int)dir.getY());
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(false,lo);
	}
	
	public void lowerHP(int ADdmg, int APdmg, Maps map, Player p){
		ADdmg-=getArmor();
		APdmg-=getMR();
		if(ADdmg<0) ADdmg=1;
		if(APdmg<0) APdmg=1;
		int dmg=ADdmg+APdmg;
		HP.setLocation(HP.getX()-dmg,HP.getY());
		if((int)HP.getX()<=0){
			HP.setLocation(0, HP.getY());
			die(p, map);
		}
	}
	
	public abstract void die(Player p, Maps map);
	
	public boolean isAlive(){
		return HP.getX()>0;
	}
	
	public int getX(){
		return (int)pos.getX();
	}
	
	public int getY(){
		return (int)pos.getY();
	}
	
	public String getName(){
		return name;
	}
	
	public int getArmor() {
		return armor;
	}

	public int getMR() {
		return MR;
	}
	
	public void stunned(boolean b){
		stunned=b;
	}
	
	public void snared(boolean b){
		snared=b;
	}
	
	public boolean isSnared(){
		return snared==true;
	}
	public boolean isStunned(){
		return stunned==true;
	}
	public abstract boolean isEnemy();
	public abstract void paintSelf(Graphics g, int x, int y, Image[] img);
	
}
