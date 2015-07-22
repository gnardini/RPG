package players;

import inventory.Inventory;
import inventory.MinorHealthPotion;
import inventory.MinorManaPotion;
import inventory.NoItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import map.Maps;
import quests.Quest;
import setup.myConstants;
import equipment.Equipment;

public abstract class Player extends LifeObject implements myConstants{

	private static final long serialVersionUID = 1L;
	protected int level;
	protected Point baseHP;
	protected Point exp;
	public Point mana;
	protected Point basemana;
	protected int manaregen;
	protected int healthregen;
	protected int gold;
	protected long CDatt1;
	protected long CDatt2;
	protected long CDatt3;
	protected long CDatt4;
	protected long CDatt5;
	protected double CDR;
	protected Inventory it1;
	protected Inventory it2;
	protected Inventory it3;
	protected Inventory it4;
	protected HashMap<String, Long> cooldowns;
	protected Equipment eq;
	protected Map<String,Quest> quests;
	private Maps map;
	
	private int north= KeyEvent.VK_UP;
	private int south= KeyEvent.VK_DOWN;
	private int east= KeyEvent.VK_RIGHT;
	private int west= KeyEvent.VK_LEFT;
	private int att1= KeyEvent.VK_A;
	private int att2= KeyEvent.VK_W;
	private int att3= KeyEvent.VK_E;
	private int att4= KeyEvent.VK_R;
	private int att5= KeyEvent.VK_Q;
	private int item1= KeyEvent.VK_1;
	private int item2= KeyEvent.VK_2;
	private int item3= KeyEvent.VK_3;
	private int item4= KeyEvent.VK_4;
	private int interact= KeyEvent.VK_P;
	private int info= KeyEvent.VK_C;
	
	public Player(int HP){
		super(HP);
		baseHP=new Point(HP,HP);
		pos=new Point(4,7);
		name = "Player";
		exp=new Point(0,100);
		gold=0;
		eq=new Equipment(this);
		it1=new NoItem(this);
		it2=new NoItem(this);
		it3=new NoItem(this);
		it4=new NoItem(this);
		quests= new TreeMap<String,Quest>();
		new HealthRegen(this);
	}
	
	public void paintSelf(Graphics g, int x, int y, Image[] img){
			if(face.x==1)
				g.drawImage(img[0],10+x*ESCALA,10+y*ESCALA,null);
			else if(face.x==-1)
				g.drawImage(img[1],10+x*ESCALA,10+y*ESCALA,null);
			else if(face.y==1)
				g.drawImage(img[2],10+x*ESCALA,10+y*ESCALA,null);
			else if(face.y==-1)
				g.drawImage(img[3],10+x*ESCALA,10+y*ESCALA,null);
		
		g.setColor(Color.white);
		g.fillRect(10+x*ESCALA, 6+y*ESCALA, ESCALA, ESCALA/8);
		g.setColor(Color.RED);
		g.fillRect(10+x*ESCALA, 6+y*ESCALA, (int)(HP.getX()/HP.getY()*ESCALA), ESCALA/8);
		if(usesMana()){
			g.setColor(Color.white);
			g.fillRect(10+x*ESCALA, 1+y*ESCALA, ESCALA, ESCALA/8);
			g.setColor(Color.blue);
			g.fillRect(10+x*ESCALA, 1+y*ESCALA, (int)(mana.getX()/mana.getY()*ESCALA), ESCALA/8);
		}/*
		g.setColor(Color.blue);
		g.fillRoundRect(11+x*ESCALA, 11+y*ESCALA, 14, 14, 4,4);
		g.setColor(Color.white);
		g.fillOval(15+x*ESCALA, 14+y*ESCALA, 2, 2);
		g.fillOval(20+x*ESCALA, 14+y*ESCALA, 2, 2);
		g.drawArc(15+x*ESCALA, 20+y*ESCALA, 6, 2, 170, 190);
		*/
	}
	
	public void setMap(Maps map){
		this.map=map;
	}
	
	public void enemyDied(String enemy){
		for(String name: quests.keySet()){
			quests.get(name).enemyKilled(enemy);
		}
	}
	
	@Override
	public void die(Player p, Maps map) {
		map.getInfo().setInfo("YOU HAVE DIED!!!",true);
		exp.setLocation(exp.getX()*0.9, exp.getY());
		try{
			HP.setLocation(-100, HP.getY());
			Thread.sleep(5000);
			HP.setLocation(HP.getY(), HP.getY());
			if(usesMana()) mana.setLocation(mana.getY(), mana.getY());
			map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(true, this);
			pos.setLocation(map.getRespawnLocationX(), map.getRespawnLocationY());
			map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(false, this);
			face.x=1;
			face.y=0;
		}catch(InterruptedException e){}
		map.getInfo().setInfo(null,false);
	}
	
	public void Interact(Maps map){
		if(map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].hasNPC())
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].openMenu(this);
	}
	
	public Map<String,Quest> getQuests(){
		return quests;
	}
	
	public abstract void levelUP(Maps map);
	public abstract boolean usesMana();
	public abstract void attack1(Maps map);
	public abstract void attack2(Maps map);
	public abstract void attack3(Maps map);
	public abstract void attack4(Maps map);
	public abstract void attack5(Maps map);
	public abstract String getAtt1name();
	public abstract String getAtt2name();
	public abstract String getAtt3name();
	public abstract String getAtt4name();
	public abstract String getAtt5name();
	public abstract boolean isOnCooldown(String name);
	public abstract void setCooldown(String name, long time);
	public abstract double getTimeLeft(String name);
	public abstract double getCDatt1();
	public abstract double getCDatt2();
	public abstract double getCDatt3();
	public abstract double getCDatt4();
	public abstract double getCDatt5();

	public void setExp(int expe) {
		exp.setLocation(getCurrentExp()+expe, getTotalExp());
		if(getCurrentExp()>=getTotalExp())
			levelUP(map);
	}
	
	public void drawInv(Graphics g, int x, int y){
		g.drawString("1:", x, y);
		it1.drawIt(g,x+12,y);
		g.drawString("2:", x, y+15);
		it2.drawIt(g,x+12,y+15);
		g.drawString("3:", x, y+30);
		it3.drawIt(g,x+12,y+30);
		g.drawString("4:", x, y+45);
		it4.drawIt(g,x+12,y+45);
		g.drawString("5:", x, y+60);
		g.drawString("6:", x, y+75);
		g.drawString("7:", x, y+90);
		g.drawString("8:", x, y+105);
	}
	
	public void buySword(int cost, String name,int ADdmg, int APdmg){
		gold-=cost;
		eq.newSword(name,ADdmg,APdmg);
	}
	
	public void buyChest(int cost, String name,int armor, int MR){
		gold-=cost;
		eq.newChest(name,armor,MR);
	}
	
	public void buyGloves(int cost, String name,int attspd, double CDR){
		gold-=cost;
		eq.newGloves(name,attspd,CDR);
	}
	
	public void buyHelm(int cost, String name,int HP2, int Mana2){
		gold-=cost;
		eq.newHelm(name,HP2,Mana2);
		HP.setLocation(baseHP.getX()+eq.getEquipHP(),baseHP.getY()+eq.getEquipHP());
		if(usesMana())
			mana.setLocation(basemana.getX()+eq.getEquipMana(),basemana.getY()+eq.getEquipMana());
	}
	
	public void addGold(int gold) {
		this.gold += gold;
	}

	public void buyMinorHealthPotion(int cost){
		gold-=cost;
		if(it1.isMinorHealthPotion()) it1.buyNew();
		else if(it2.isMinorHealthPotion()) it2.buyNew();
		else if(it3.isMinorHealthPotion()) it3.buyNew();
		else if(it4.isMinorHealthPotion()) it4.buyNew();
		else if(it1.isEmpty()) it1=new MinorHealthPotion(this);
		else if(it2.isEmpty()) it2=new MinorHealthPotion(this);
		else if(it3.isEmpty()) it3=new MinorHealthPotion(this);
		else if(it4.isEmpty()) it4=new MinorHealthPotion(this);
		else gold +=cost;
	}
	
	public void buyMinorManaPotion(int cost){
		gold-=cost;
		if(it1.isMinorManaPotion()) it1.buyNew();
		else if(it2.isMinorManaPotion()) it2.buyNew();
		else if(it3.isMinorManaPotion()) it3.buyNew();
		else if(it4.isMinorManaPotion()) it4.buyNew();
		else if(it1.isEmpty()) it1=new MinorManaPotion(this);
		else if(it2.isEmpty()) it2=new MinorManaPotion(this);
		else if(it3.isEmpty()) it3=new MinorManaPotion(this);
		else if(it4.isEmpty()) it4=new MinorManaPotion(this);
		else gold +=cost;
	}
	
	public void ShowPlayerInfo(){
		new PlayerInfo(this);
	}
	
	public int getArmor() {
		return armor+eq.getEquipArmor();
	}

	public int getMR() {
		return MR+eq.getEquipMR();
	}
	
	public int getAD() {
		return AD+eq.getEquipADdmg();
	}

	public int getAP() {
		return AP+eq.getEquipAPdmg();
	}
	
	public int getCDR() {
		return (int)(CDR+eq.getEquipCDR()*100);
	}

	public double getAttSpd() {
		return (double)(CDatt1-eq.getEquipAttspd())/1000;
	}
	
	public int getHP(){
		return (int)HP.getY();
	}
	
	public int getMana(){
		return (int)mana.getY();
	}
	
	public int getHPregen(){
		return healthregen+eq.getEquipHPregen();
	}
	
	public int getManaregen(){
		return manaregen+eq.getEquipManaregen();
	}
	
	public int getGold(){
		return gold;
	}
	
	public int getCurrentExp(){
		return (int)exp.getX();
	}
	
	public int getTotalExp(){
		return (int)exp.getY();
	}
	
	public int getAtt1() {
		return att1;
	}

	public void setAtt(int att) {
		this.att1 = att;
	}
	
	public int getAtt2() {
		return att2;
	}

	public void setAtt2(int att) {
		this.att2 = att;
	}
	
	public int getAtt3() {
		return att3;
	}

	public void setAtt3(int att) {
		this.att3 = att;
	}
	
	public int getAtt4() {
		return att4;
	}

	public void setAtt4(int att) {
		this.att4 = att;
	}
	
	public int getAtt5() {
		return att5;
	}

	public void setAtt5(int att) {
		this.att5 = att;
	}
	
	public int getInteract() {
		return interact;
	}
	
	public void setInteract(int n){
		interact=n;
	}
	
	public int getInfoChar() {
		return info;
	}
	
	public void setInfoChar(int n){
		info=n;
	}
	
	public int getItem1() {
		return item1;
	}

	public void setItem1(int it) {
		this.item1 = it;
	}
	
	public int getItem2() {
		return item2;
	}

	public void setItem2(int it) {
		this.item2 = it;
	}
	
	public int getItem3() {
		return item3;
	}

	public void setItem3(int it) {
		this.item3 = it;
	}
	
	public int getItem4() {
		return item4;
	}

	public void setItem4(int it) {
		this.item4 = it;
	}
	
	public String getItem1name(){
		return it1.getName();
	}
	
	public String getItem2name(){
		return it2.getName();
	}
	
	public String getItem3name(){
		return it3.getName();
	}
	
	public String getItem4name(){
		return it4.getName();
	}
	
	public void useItem1(){
		it1.use();
	}
	
	public void useItem2(){
		it2.use();
	}
	
	public void useItem3(){
		it3.use();
	}
	
	public void useItem4(){
		it4.use();
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getNorth() {
		return north;
	}

	public void setNorth(int north) {
		this.north = north;
	}

	public int getSouth() {
		return south;
	}

	public void setSouth(int south) {
		this.south = south;
	}

	public int getEast() {
		return east;
	}

	public void setEast(int east) {
		this.east = east;
	}

	public int getWest() {
		return west;
	}

	public void setWest(int west) {
		this.west = west;
	}
	
	public boolean isEnemy(){
		return false;
	}
	
	double roundTwoDecimals(double d) {
		 DecimalFormat twoDForm = new DecimalFormat("#.##");
		 return Double.valueOf(twoDForm.format(d));
	}
	
	public class MsjLvlUP implements Runnable{
		Maps map;
		
		MsjLvlUP(Maps map){
			this.map=map;
			Thread t= new Thread(this, "Level UP");
			t.start();
		}
		public void run(){
			try{
				map.getInfo().setInfo("LEVEL UP!", true);
				Thread.sleep(5000);
				map.getInfo().setInfo(null, false);
			}catch(InterruptedException e){}
			
		}
	}
	
	public class MsjNoMana implements Runnable{
		Maps map;
		
		public MsjNoMana(Maps map){
			this.map=map;
			Thread t= new Thread(this, "No mana");
			t.start();
		}
		public void run(){
			try{
				map.getInfo().setInfo("You don't have enough mana", true);
				Thread.sleep(5000);
				map.getInfo().setInfo(null, false);
			}catch(InterruptedException e){}
			
		}
	}
	
	public class ManaRegen implements Runnable{
		Player p;
		
		public ManaRegen(Player p){
			this.p=p;
			Thread t=new Thread(this,"Mana Regen");
			t.setPriority(1);
			t.start();
		}
		@Override
		public void run() {
			while(true){
				if(mana.getX()<mana.getY()) mana.translate(manaregen, 0);
				if(mana.getX()>mana.getY()) mana.setLocation(mana.getY(), mana.getY());
				try{Thread.sleep(2000);}catch(InterruptedException e){}
			}
		}
	}
	public class HealthRegen implements Runnable{
		Player p;
		
		public HealthRegen(Player p){
			this.p=p;
			Thread t=new Thread(this,"Health Regen");
			t.setPriority(1);
			t.start();
		}
		@Override
		public void run() {
			while(true){
				if(HP.getX()<HP.getY()) HP.translate(healthregen, 0);
				if(HP.getX()>HP.getY()) HP.setLocation(HP.getY(), HP.getY());
				try{Thread.sleep(2000);}catch(InterruptedException e){}
			}
		}
	}
}
