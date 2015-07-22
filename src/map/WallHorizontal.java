package map;

import java.awt.Color;
import java.awt.Graphics;

import setup.myConstants;

public class WallHorizontal extends Wall implements myConstants {
	
	public void paintSelf(Graphics g, int x, int y) {
		g.setColor(new Color(90,0,0));
		g.fillRect(10+x*ESCALA, 16+y*ESCALA, ESCALA, ESCALA-20);
	}
}
