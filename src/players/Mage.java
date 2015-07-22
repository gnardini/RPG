package players;

import java.awt.Point;
import java.util.HashMap;

import map.Maps;
import abilities.FireBall;



public class Mage extends Player {

	private static final long serialVersionUID = 1L;

	public Mage() {
		super(100);
		cooldowns = new HashMap<>();
		level=1;
		AD=5;
		AP=25;
		armor=0;
		MR=0;
		CDatt1=1500;
		CDatt2=6000;
		CDatt3=9000;
		CDatt4=7500;
		CDatt5=10000;
		CDR=0;
		mana=new Point(100,100);
		basemana=new Point(100,100);
		manaregen=10;
		healthregen=1;
		new ManaRegen(this);
		cooldowns.put("AA", (long)0);
		cooldowns.put("FireBall", (long)0);
		cooldowns.put("Blink", (long)0);
		cooldowns.put("Arcane Nova", (long)0);
		cooldowns.put("Freeze", (long)0);
	}
	
	public void attack1(Maps map){
		int dmg = (int)((AD+eq.getEquipADdmg()));
		if(!isOnCooldown("AA")){
			setCooldown("AA", CDatt1-eq.getEquipAttspd());
			map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
		}
	}
	
	public void attack2(Maps map){
		int dmg=(int)((AP+eq.getEquipAPdmg())*0.8);
		if(!isOnCooldown("FireBall")){
			if(mana.getX()>=25){
				mana.translate(-25, 0);
				setCooldown("FireBall", (long)(CDatt2*(1-eq.getEquipCDR())));
				new FireBall(map,0, dmg, face, this,10,200);
			}else new MsjNoMana(map);
		}
	}
	
	public void attack3(Maps map){
		if(!isOnCooldown("Blink")){
			if(mana.getX()>=10){
				mana.translate(-10, 0);
				setCooldown("Blink", (long)(CDatt3*(1-eq.getEquipCDR())));
				blink(map);
			}else new MsjNoMana(map);
		}
	}
	
	public void attack4(Maps map){
		int dmg=(int)((AP+eq.getEquipAPdmg())*0.4);
		if(!isOnCooldown("Arcane Nova")){
			if(mana.getX()>=25){
				mana.translate(-25, 0);
				setCooldown("Arcane Nova", (long)(CDatt4*(1-eq.getEquipCDR())));
				for(int i=-1; i<2 ; i++)
					for(int j=-1; j<2 ; j++)
						if(i!=0 || j!=0)
							new FireBall(map,0,dmg,new Point(i,j),this,3,100);
			}else new MsjNoMana(map);
		}
	}
	
	public void attack5(Maps map) {
		int dmg=(int)((AP+eq.getEquipAPdmg())*0.25);
		if(!isOnCooldown("Freeze")){
			if(mana.getX()>=20){
				mana.translate(-20, 0);
				setCooldown("Freeze", (long)(CDatt5*(1-eq.getEquipCDR())));
				map.getMap()[(int)(pos.getX()+face.getX())][(int)(pos.getY()+face.getY())].attack(dmg, 0, map, this);
				for(int i=-2 ; i<=2 ; i++)
					for(int j=-2 ; j<=2 ; j++)
						if(i!=0 || j!=0)
							map.getMap()[(int)(pos.getX()+i)][(int)(pos.getY()+j)].snare(0,dmg,map,this,3000);
			}
		}
	}
	
	public void levelUP(Maps map){
		new Thread( new MsjLvlUP(map), "Level UP" );
		level++;
		int newHP=100+(int)Math.pow(level+2,2.3);
		baseHP.setLocation(newHP,newHP);
		HP.setLocation(newHP+eq.getEquipHP(), newHP+eq.getEquipHP());
		int newmana=100+(int)Math.pow(level+2,1.8);
		basemana.setLocation(newmana,newmana);
		mana.setLocation(newmana+eq.getEquipMana(),newmana+eq.getEquipMana());
		exp.setLocation(0.0, Math.pow(level, 3.2)+100);
		AD+=2;
		AP+=5;
		armor++;
		MR+=2;
		CDatt1-=5;
		manaregen++;
		if(level%2==0) healthregen++;
	}
	
	private void blink(Maps map){
		int x=(int)(pos.getX()+5*face.getX()), y=(int)(pos.getY()+5*face.getY()),contador=0;
		while((x<0 || x>=DIM || y<0 || y>=DIM || !map.getMap()[x][y].isEmpty()) && contador<5){
			x-=(int)face.getX();
			y-=(int)face.getY();
			contador++;
		}
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(true,null);
		pos.move(x, y);
		map.getMap()[(int)pos.getX()][(int)pos.getY()].isEmpty(false,this);
	}
	
	public String getAtt1name(){
		return "AA";
	}
	
	public String getAtt2name(){
		return "FireBall";
	}
	
	public String getAtt3name(){
		return "Blink";
	}
	
	public String getAtt4name(){
		return "Arcane Nova";
	}
	
	public String getAtt5name(){
		return "Freeze";
	}
	
	public double getCDatt1() {
		return (double)(CDatt1-eq.getEquipAttspd())/1000;
	}

	public double getCDatt2() {
		return (double)(CDatt2*(1-eq.getEquipCDR()))/1000;
	}

	public double getCDatt3() {
		return (double)(CDatt3*(1-eq.getEquipCDR()))/1000;
	}
	
	public double getCDatt4() {
		return (double)(CDatt4*(1-eq.getEquipCDR()))/1000;
	}
	
	public double getCDatt5() {
		return (double)(CDatt5*(1-eq.getEquipCDR()))/1000;
	}
	
	@Override
	public boolean usesMana() {
		return true;
	}
	
	public boolean isOnCooldown(String name){
		return getTimeLeft(name)!=0;		
	}
	
	public void setCooldown(String name, long time){
		long l= System.currentTimeMillis() + time;
		cooldowns.put(name, l);
	}
	
	public double getTimeLeft(String name){
		long l = cooldowns.get(name);
		if(System.currentTimeMillis()>l) return 0;
		else return /*roundTwoDecimals(*/((double)(l-System.currentTimeMillis())/1000);
	}
	
}
