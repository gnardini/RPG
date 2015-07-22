package setup;

import java.awt.Color;
import java.awt.Graphics;

import players.Player;
import map.Maps;
import enemies.Enemy;

public class Info implements myConstants {

	int top, left;
	boolean hasinfo, hasEnemyInfo;
	String info;
	Enemy en;
	Color yell= new Color(240,240,180);
	
	public Info(int t, int l){
		top=t;
		left=l;
		hasinfo=false;
	}
	
	public void showCurrentInfo(Graphics g){
		g.setColor(yell);
		g.fillRect(top, left, 196, 200);
		if(hasinfo){
			g.setColor(new Color(50,90,10));
			g.drawString(info,top+25,left+25);
		}
		if(hasEnemyInfo){
			g.setColor(new Color(50,90,10));
			g.drawString(en.getName(),top+25,left+40);
			g.drawString("HP: " + (int)en.HP.getX() + " / " + (int)en.HP.getY(),top+25,left+55);
		}
	}
	
	public void setInfo(String s,boolean b){
		info=s;
		hasinfo=b;
	}
	
	public void setEnemyInfo(Enemy e, boolean b){
		en=e;
		hasEnemyInfo=b;
	}
	
	public void showPlayerInfo(Graphics g,Player p, Maps map){
		drawBox(g);
		g.setColor(new Color(50,90,10));
		g.drawString("Name: " + p.getName(), top+5, left+15);
		int HPX=(int)p.HP.getX(); if(HPX<0) HPX=0;
		g.drawString("HP: " + HPX + " / " + (int)p.HP.getY(), top+5, left+30);
		if(p.usesMana()) g.drawString("Mana: " + (int)p.mana.getX() + " / " + (int)p.mana.getY(), top+5, left+45);
		g.drawString("Level: " + p.getLevel(), top+5, left+60);
		g.drawString("Experience: " + p.getCurrentExp() + " / " + p.getTotalExp(), top+5, left+75);
		g.drawString("Gold: " + p.getGold(), top+5, left+90);
		g.drawString("Map: " + map.getName(), top+5, left+105);
		g.drawString("Inventory:", top+5, left+125);
		p.drawInv(g, top+5,left+140);
		g.drawString("Cooldowns: ", top+5, left+260);
		g.drawString(p.getAtt5name() + ": " + p.getTimeLeft(p.getAtt5name()) + " / " + p.getCDatt5(), top+5, left+275);
		g.drawString(p.getAtt2name() + ": " + p.getTimeLeft(p.getAtt2name()) + " / " + p.getCDatt2(), top+5, left+310);
		g.drawString(p.getAtt3name() + ": " + p.getTimeLeft(p.getAtt3name()) + " / " + p.getCDatt3(), top+5, left+345);
		g.drawString(p.getAtt4name() + ": " + p.getTimeLeft(p.getAtt4name()) + " / " + p.getCDatt4(), top+5, left+380);
		g.setColor(Color.GREEN);
		g.fillRect(top+5,left+280 , (int)((double)(p.getTimeLeft(p.getAtt5name())/p.getCDatt5())*150) , 15);
		g.fillRect(top+5,left+315 , (int)((double)(p.getTimeLeft(p.getAtt2name())/p.getCDatt2())*150) , 15);
		g.fillRect(top+5,left+350 , (int)((double)(p.getTimeLeft(p.getAtt3name())/p.getCDatt3())*150) , 15);
		g.fillRect(top+5,left+385 , (int)((double)(p.getTimeLeft(p.getAtt4name())/p.getCDatt4())*150) , 15);
		g.fillRect(50,-18+ESCALA*21 , (int)((double)(p.getTimeLeft(p.getAtt5name())/p.getCDatt5())*150) , 15);
		g.fillRect(250,-18+ESCALA*21 , (int)((double)(p.getTimeLeft(p.getAtt2name())/p.getCDatt2())*150) , 15);
		g.fillRect(450,-18+ESCALA*21 , (int)((double)(p.getTimeLeft(p.getAtt3name())/p.getCDatt3())*150) , 15);
		g.fillRect(650,-18+ESCALA*21 , (int)((double)(p.getTimeLeft(p.getAtt4name())/p.getCDatt4())*150) , 15);
		g.setColor(Color.RED);
		g.drawString(p.getAtt5name(),60  ,-6+ESCALA*21 );
		g.drawString(p.getAtt2name(),260  ,-6+ESCALA*21 );
		g.drawString(p.getAtt3name(),460  ,-6+ESCALA*21 );
		g.drawString(p.getAtt4name(),660  ,-6+ESCALA*21 );
		g.setColor(Color.WHITE);
		for(int i=0; i<4 ; i++) g.drawRect(50+i*200, -18+ESCALA*21, 150, 15);
		g.setColor(Color.BLACK);
		for(int i=0; i<4 ; i++) g.drawRect(top+5, left+280+i*35, 150, 15);
		
	}
	
	public void drawBox(Graphics g){
		g.setColor(yell);
		g.fillRect(top, left, 196, 430);
	}
	
	public boolean hasInfo(){
		return hasinfo;
	}
	
}
