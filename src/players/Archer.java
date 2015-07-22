package players;

import java.awt.Point;
import java.util.HashMap;

import map.Maps;
import abilities.ConstantProyectile;
import abilities.FireBall;
import abilities.PoisonousArrow;

public class Archer extends Player {

	private static final long serialVersionUID = 1L;

	public Archer() {
		super(100);
		cooldowns = new HashMap<>();
		level=1;
		AD=20;
		AP=5;
		armor=0;
		MR=0;
		CDatt1=1500;
		CDatt2=7500;
		CDatt3=8000;
		CDatt4=14000;
		CDatt5=11000;
		CDR=0;
		mana=new Point(100,100);
		basemana=new Point(100,100);
		manaregen=5;
		healthregen=1;
		new ManaRegen(this);
		cooldowns.put("AA", (long)0);
		cooldowns.put("Poisonous Arrow", (long)0);
		cooldowns.put("Trap", (long)0);
		cooldowns.put("Escape", (long)0);
		cooldowns.put("Explosive Arrow", (long)0);
	}
	
	public void attack1(Maps map){
		int dmg = (int)((AD+eq.getEquipADdmg())*0.5);
		if(!isOnCooldown("AA")){
			setCooldown("AA", CDatt1-eq.getEquipAttspd());
			new FireBall(map,dmg, 0, face, this,7,20);
			
		}
	}
	
	public void attack2(Maps map){
		//int dmg=(int)((AD+eq.getEquipADdmg())*0.3);
		if(!isOnCooldown("Trap")){
			setCooldown("Trap", (long)(CDatt2-eq.getEquipAttspd()));
			// new Trap();
		}
	}
	
	public void attack3(Maps map){
		Point dir = new Point(-face.x,-face.y);
		if(!isOnCooldown("Escape")){
			setCooldown("Escape", (long)(CDatt3*(1-eq.getEquipCDR())));
			for(int i=0 ; i<7 ; i++)
				move(dir,map,this);
			face= new Point(-dir.x, -dir.y);
		}
	}
	
	public void attack4(Maps map){
		int dmg=(int)((AD+eq.getEquipADdmg())*0.4);
		if(!isOnCooldown("Explosive Arrow")){
			setCooldown("Explosive Arrow", (long)(CDatt4-eq.getEquipAttspd()));
			quickStrikes(map, dmg);
		}
	}
	
	public void attack5(Maps map){
		int dmg=(int)((AD+eq.getEquipADdmg())*0.9);
		if(!isOnCooldown("Poisonous Arrow")){
			setCooldown("Poisonous Arrow", (long)(CDatt5-eq.getEquipAttspd()));
			new PoisonousArrow(map,dmg,0,face,this,8,100);
		}
	}
	
	public void levelUP(Maps map){
		new Thread( new MsjLvlUP(map), "Level UP" );
		level++;
		int newHP=100+(int)Math.pow(level+2,2.3);
		baseHP.setLocation(newHP,newHP);
		HP.setLocation(newHP+eq.getEquipHP(), newHP+eq.getEquipHP());
		exp.setLocation(0.0, Math.pow(level, 3.2)+100);
		AD+=5;
		AP+=2;
		armor+=2;
		MR++;
		CDatt1-=15;
		if(level%2==1)healthregen++;
	}
	
	public void quickStrikes(Maps map, int dmg){
		int x=(int)face.getX(), y=(int)face.getY();
		if(x==0){
			for(int i=-1; i<2 ; i++)
				new ConstantProyectile(map,dmg,0,new Point(i,y),this,2,100,50);
		}else 
			for(int i=-1; i<2 ; i++)
				new ConstantProyectile(map,dmg,0,new Point(x,i),this,2,100,50);
		
	}
	
	public String getAtt1name(){
		return "AA";
	}
	
	public String getAtt2name(){
		return "Trap";
	}
	
	public String getAtt3name(){
		return "Escape";
	}
	
	public String getAtt4name(){
		return "Explosive Arrow";
	}
	
	public String getAtt5name(){
		return "Poisonous Arrow";
	}
	
	public double getCDatt1() {
		return (double)(CDatt1-eq.getEquipAttspd())/1000;
	}

	public double getCDatt2() {
		return (double)(CDatt2-eq.getEquipAttspd())/1000;
	}

	public double getCDatt3() {
		return (double)(CDatt3*(1-eq.getEquipCDR()))/1000;
	}
	
	public double getCDatt4() {
		return (double)(CDatt4-eq.getEquipAttspd())/1000;
	}
	
	public double getCDatt5() {
		return (double)(CDatt5-eq.getEquipAttspd())/1000;
	}
	
	@Override
	public boolean usesMana() {
		return false;
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

