package setup;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import map.Bush;
import map.Grass;
import map.Maps;
import map.NPC;
import map.Rock;
import map.WallHorizontal;
import map.WallVertical;
import players.Archer;
import players.Player;
import enemies.Monkey;
import enemies.Snake;
import enemies.SuperMonkey;
import enemies.SuperSnake;

// OK this the class where we will draw
public class mainCanvas extends Canvas implements KeyListener, myConstants, MouseListener{

	private static final long serialVersionUID = 1L;
	boolean repaintInProgress = false;
	private Maps map;
	private Player p;
	private Info status;
	private Info current;
	private Image[] img;
	private Map<String, Image> images = new HashMap<String, Image>();
	
	mainCanvas() {
		setIgnoreRepaint(true);
		addKeyListener(this);
		addMouseListener(this);
		status= new Info(20+ESCALA*26,10);
		current= new Info(20+ESCALA*26,450);
		
		p = new Archer();
		map= new Maps("Freila", current, p);
		map.getMap()[(int)p.getX()][(int)p.getY()].isEmpty(false,p);
		p.setMap(map);
		
		img= new Image[18];
		loadImages();
		
		
		
		mainChrono chrono = new mainChrono(this);
		new Timer(16, chrono).start();
	}
	
	private void loadImages(){
		try{
		img[0]=loadImage("images/red hair.png");
		img[1]=loadImage("images/red hair back.png");
		img[2]=loadImage("images/red hair der.png");
		img[3]=loadImage("images/red hair izq.png");
		img[4]=loadImage("images/pasto.png");
		img[5]=loadImage("images/bat.png");
		img[6]=loadImage("images/bush.png");
		img[7]=loadImage("images/hiena.png");
		img[8]=loadImage("images/ladrillos.png");
		img[9]=loadImage("images/roca.png");
		img[10]=loadImage("images/snake-chica.png");
		img[11]=loadImage("images/snake.png");
		img[12]=loadImage("images/vendedor.png");
		img[13]=loadImage("images/bat.png");
		img[14]=loadImage("images/pasto2.png");
		img[15]=loadImage("images/pasto3.png");
		img[16]=loadImage("images/roca2.png");
		img[17]=loadImage("images/snake grande.png");
		}catch(IOException e){}
	}
	
	public void initImages() {
		try{
			images.put(Grass.class.getName(), loadImage("pasto2.png"));
			images.put(Snake.class.getName(), loadImage("snake.png"));
			images.put(SuperSnake.class.getName(), loadImage("snake grande.png"));
			images.put(WallHorizontal.class.getName(), loadImage("ladrillos.png"));
			images.put(WallVertical.class.getName(), loadImage("ladrillos.png"));
			images.put(Bush.class.getName(), loadImage("bush.png"));
			images.put(Rock.class.getName(), loadImage("roca2.png"));
			images.put(Monkey.class.getName(), loadImage("bat.png"));
			images.put(SuperMonkey.class.getName(), loadImage("hiena.png"));
			images.put(NPC.class.getName(), loadImage("vendedor.png"));
			images.put("Front", loadImage("red hair.png"));
			images.put("Back", loadImage("red hair back.png"));
			images.put("Left", loadImage("red hair izq.png"));
			images.put("Right", loadImage("red hair der.png"));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Image loadImage(String fileName) throws IOException {
		InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
		if (stream == null) {	
			return ImageIO.read(new File(fileName)); 
		} else {
			return ImageIO.read(stream);
		}
	}
	
	public void myRepaint() {
		if(repaintInProgress)
			return;
		repaintInProgress = true;
		BufferStrategy strategy = getBufferStrategy();
		Graphics g = strategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 226+ESCALA*31, 20+ESCALA*31);
		
		status.showPlayerInfo(g, p, map);
		current.showCurrentInfo(g);
		
		int x, y;
		y=p.getX();x=p.getY();
		if(x<TAMANO+3)x=TAMANO+3;
		else if(x>DIM-TAMANO-3) x = DIM-TAMANO-3;
		if(y<TAMANO)y=TAMANO;
		else if(y>DIM-TAMANO) y = DIM-TAMANO;
		for(int i=x-TAMANO-3, k=0; i<x+TAMANO+3 ; i++,k++){
			for( int j=y-TAMANO, l=0 ; j<y+TAMANO; j++,l++){
				map.getMap()[j][i].paintSelf(g, k, l, img);
			}
		}/*
		if(y>p.getX()) y=p.getX();
		else if(y<p.getX()) y=TAMANO*2-DIM+p.getX();
		else if(y==p.getX()) y=TAMANO;
		if(x>p.getY()) x=p.getY();
		else if(x<p.getY()) x=TAMANO*2-DIM+p.getY();
		else if(x==p.getY()) x=TAMANO;
		
		p.paintSelf(g, x, y, img);*/
		status.showPlayerInfo(g, p, map);
		strategy.show();
		Toolkit.getDefaultToolkit().sync();
		repaintInProgress = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(p.isAlive()){
		if(key==p.getNorth()) p.move(new Point(-1,0), map,p);
		else if(key==p.getSouth())p.move(new Point(1,0), map,p);
		else if(key==p.getEast())p.move(new Point(0,1), map,p);
		else if(key==p.getWest())p.move(new Point(0,-1), map,p);
		else if(key==p.getAtt1()) p.attack1(map);
		else if(key==p.getAtt2()) p.attack2(map);
		else if(key==p.getAtt3()) p.attack3(map);
		else if(key==p.getAtt4()) p.attack4(map);
		else if(key==p.getAtt5()) p.attack5(map);
		else if(key==p.getItem1()) p.useItem1();
		else if(key==p.getItem2()) p.useItem2();
		else if(key==p.getItem3()) p.useItem3();
		else if(key==p.getItem4()) p.useItem4();
		else if(key==p.getInteract()) p.Interact(map);
		else if(key==p.getInfoChar()) p.ShowPlayerInfo();
		else if(key==p.getInfoChar()) new ShowControlsPanel(p);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}

