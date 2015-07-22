package abilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import players.Player;
import map.Maps;
import setup.myConstants;

public class ConstantProyectile extends Proyectile implements Runnable, myConstants{

	int ADdmg,APdmg, range, speed, dur;
	Point dir;
	boolean active=true;
	
	public ConstantProyectile(Maps m, int AD,int AP, Point dir, Player pl, int range, int speed, int duration){
		map=m;
		ADdmg=AD;
		APdmg=AP;
		dur=duration;
		this.range=range;
		this.speed=speed;
		name="ConstProy";
		this.dir=dir;
		p=pl;
		Thread t=new Thread(this,name);
		t.start();
	}
	
	@Override
	public void run() {
		try{
		int x=p.getX(), y=p.getY();
		Point p2= new Point((int)(p.getX()+dir.getX()),(int)(p.getY()+dir.getY()));
		for(int i=0 ; i<range ; i++){
			if(!map.getMap()[(int)p2.getX()][(int)(p2.getY())].isEmpty()){
					if(map.getMap()[(int)p2.getX()][(int)(p2.getY())].hasEnemy())
						map.getMap()[(int)p2.getX()][(int)(p2.getY())].attack(ADdmg, APdmg, map, p);
			}
			map.getMap()[(int)p2.getX()][(int)p2.getY()].hasProy(true,this);
			p2.translate((int)dir.getX(), (int)dir.getY());
			
			Thread.sleep(speed);
		}
		Thread.sleep(dur);
		for(int i=0 ; i<range+1 ; i++){
			map.getMap()[(int)(x+i*dir.getX())][(int)(y+i*dir.getY())].hasProy(false, null);
		}
		}catch(InterruptedException e){}
	}
	
	public void targetHit(){}
	
	@Override
	public int getADdmg() {
		return ADdmg;
	}
	
	@Override
	public int getAPdmg() {
		return APdmg;
	}
	
	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		if(dur==100){
			g.setColor(new Color(15,70,110));
			g.fillRoundRect(18+x*ESCALA, 18+y*ESCALA, ESCALA/2, ESCALA/2,4,4);
		}else if(dur==50){
			g.setColor(new Color(15,70,110));
			g.fillRoundRect(18+x*ESCALA, 18+y*ESCALA, ESCALA/2, ESCALA/2,4,4);
		}
		
	}

}
