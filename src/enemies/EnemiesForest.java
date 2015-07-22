package enemies;

import java.awt.Point;
import java.util.Random;

import players.Player;
import map.Maps;
import setup.myConstants;

public class EnemiesForest extends Enemies implements Runnable, myConstants{
	
	private Thread t;
	
	public EnemiesForest(Player p, Maps map) {
		en= new Enemy[16];
		for(int i=0; i<15 ; i++)
			en[i]=new Monkey(180);
		en[15]=new SuperMonkey(250);
		setCant(16);
		setP(p);
		setMap(map);
		t=new Thread(this,"Enemies");
		t.start();
	}
	
	@Override
	public void run() {
		for(int i=0 ; i<getCant()-1 ; i++)
			setStartPosition(getMap(), en[i]);
		setBossStartPosition(getMap(), en[getCant()-1]);
		while(!isEnd()){
			try{
				actualizarDistancias(getMap(),getP());
				BossMoveandAttack();
				Thread.sleep(250);
				actualizarDistancias(getMap(),getP());
				checkOnEnemies();
				moveEnemies();
				enemiesAttack();
				Thread.sleep(250);
			}catch(InterruptedException e){}
		}
	}
	
	public void end(){
		setEnd(true);
	}
	
	public void BossMoveandAttack(){
		if(en[getCant()-1].isAlive()){
			en[getCant()-1].move(getP(),getMap(),getDist());
			en[getCant()-1].attack(getP(),getMap());
		}
	}
	
	public void checkOnEnemies(){
		for(int i=0 ; i<getCant()-1 ; i++)
			if(!en[i].isAlive()){
				en[i]=new Monkey(180);
				setStartPosition(getMap(), en[i]);
			}
		if(!en[15].isAlive() && timeToRespawn()){
			en[15]=new SuperMonkey(250);
			setBossStartPosition(getMap(), en[15]);
		}	
			
	}
	
	
	public void setStartPosition(Maps map, Enemy en){
		int x, y;
		Random r = new Random();
		do{
			x=r.nextInt(DIM);
			y=r.nextInt(DIM);
		}while(!map.getMap()[x][y].isEmpty() || (x>46 && x<60 && y<1 && y>65));
		map.getMap()[x][y].isEmpty(false, en);
		en.pos=new Point(x,y);
	}
	
	public void setBossStartPosition(Maps map, Enemy en){
		int x, y;
		Random r = new Random();
		do{
			x=r.nextInt(5)+DIM-6;
			y=r.nextInt(DIM);
		}while(!map.getMap()[x][y].isEmpty());
		map.getMap()[x][y].isEmpty(false, en);
		en.pos=new Point(x,y);
	}

}

