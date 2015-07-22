package enemies;

import java.awt.Point;
import java.util.LinkedList;

import players.Player;
import map.Maps;
import setup.myConstants;

public abstract class Enemies implements Runnable, myConstants {
	protected Enemy[] en;
	private int cant;
	private Player p;
	private Maps map;
	private boolean end;
	private int[][] distancias;
	private int[] dx={0,0,-1,1};
	private int[] dy={1,-1,0,0};
	
	public void moveEnemies(){
		for(int i=0 ; i<getCant() ; i++){
			if(en[i].isAlive())
				en[i].move(getP(),getMap(), distancias);
		}
	}
	
	public void enemiesAttack(){
		for(int i=0 ; i<getCant() ; i++){
			if(en[i].isAlive())
				en[i].attack(getP(),getMap());
		}
	}
	
	public boolean timeToRespawn(){
		return en[cant-1].getTimeLeft()<=0;
	}
	
	public void actualizarDistancias(Maps map, Player p){
		int x,y, x1,y1;
		distancias= new int[25][25];
		for(int i=0 ; i<25 ; i++)
			for(int j=0 ; j<25 ; j++)
				distancias[i][j]=50;
		x=p.getX();
		y=p.getY();
		distancias[12][12]=0;
		LinkedList<Point> cola = new LinkedList<Point>();
		Point punto;
		cola.push(new Point(12,12));
		while( !cola.isEmpty()){
			punto=cola.pop();
			x1=(int)punto.getX();
			y1=(int)punto.getY();
			for(int i=0; i<4; i++){
				if(x1+dx[i]>=0 && x1+dx[i]<25 && y1+dy[i]>=0 && y1+dy[i]<25 && x+x1+dx[i]-12 >0 && x+x1+dx[i]-12<DIM && y+y1+dy[i]-12>0 && y+y1+dy[i]-12<DIM){
				if(map.getMap()[x+x1+dx[i]-12][y+y1+dy[i]-12].isEmpty() && distancias[x1+dx[i]][y1+dy[i]]>distancias[x1][y1]+1){
					distancias[x1+dx[i]][y1+dy[i]]=distancias[x1][y1]+1;
					cola.push(new Point(x1+dx[i],y1+dy[i]));
				}
				}
			}	
		}
	}
	
	public Enemy[] getEn() {
		return en;
	}
	public void setEn(Enemy[] en) {
		this.en = en;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public Player getP() {
		return p;
	}
	public int[][] getDist(){
		return distancias;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public Maps getMap() {
		return map;
	}
	public void setMap(Maps map) {
		this.map = map;
	}
	public boolean isEnd() {
		return end;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	
	

}
