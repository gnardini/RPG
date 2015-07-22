package enemies;

import java.awt.Point;
import java.util.*;

import players.Player;
import map.Maps;
import setup.myConstants;

public class EnemiesFreila extends Enemies implements myConstants, Runnable {

	private Thread t;
	
	
	public EnemiesFreila(Player p, Maps map) {
		en= new Enemy[11];
		for(int i=0; i<10 ; i++)
			en[i]=new Snake(70);
		en[10]=new SuperSnake(150);
		setCant(11);
		setP(p);
		setMap(map);
		t=new Thread(this,"Enemies");
		t.start();
	}
	
	@Override
	public void run() {
		for(int i=0 ; i<getCant()-1 ; i++)
			setStartPosition(getMap(), en[i]);
		setBossStartPosition(getMap(), en[10]);
		while(!isEnd()){
			try{
				checkOnEnemies();
				moveEnemies();
				enemiesAttack();
				Thread.sleep(600);

				actualizarDistancias(getMap(),getP());
			}catch(InterruptedException e){}
		}
	}
	
	public void end(){
		setEnd(true);
	}
	
	public void checkOnEnemies(){
		for(int i=0 ; i<getCant()-1 ; i++)
			if(!en[i].isAlive()){
				en[i]=new Snake(70);
				setStartPosition(getMap(), en[i]);
			}
		if(!en[10].isAlive() && timeToRespawn()){
			en[10]=new SuperSnake(150);
			setBossStartPosition(getMap(), en[10]);
		}	
			
	}
	
	
	public void setStartPosition(Maps map, Enemy en){
		int x, y;
		Random r = new Random();
		do{
			x=r.nextInt(DIM/2)+DIM/2;
			y=r.nextInt(DIM);
		}while(!map.getMap()[x][y].isEmpty());
		map.getMap()[x][y].isEmpty(false, en);
		en.pos=new Point(x,y);
	}
	
	public void setBossStartPosition(Maps map, Enemy en){
		int x, y;
		Random r = new Random();
		do{
			x=r.nextInt(10)+DIM-10;
			if(r.nextInt(2)==0) y=r.nextInt(23)+DIM-23;
			else y=r.nextInt(22)+1;
		}while(!map.getMap()[x][y].isEmpty());
		map.getMap()[x][y].isEmpty(false, en);
		en.pos=new Point(x,y);
	}
	
	}
