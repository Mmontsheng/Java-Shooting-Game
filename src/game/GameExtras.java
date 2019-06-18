package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameExtras {

	public void renderText(Graphics g, String string, int item, int x, int y) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		g.drawString(string + item, x, y);
	}

	public void renderPlayArea(Graphics g, int WIDTH, int HEIGHT) {
		// draw frame on game area
		g.setColor(Color.RED);
		g.drawRect(0, 50, WIDTH , HEIGHT);

	}
	
	
	public void drawHealthBar(Graphics g, double health){
		g.setColor(Color.gray);
		g.fillRect(150, 15, 200, 25);
		
		float ratio= (float) (health/200);
		if(ratio>0.80){
			g.setColor(Color.GREEN);			
		}else if(ratio>=0.50 && ratio<=0.79){
			g.setColor(Color.YELLOW);
		}else if(ratio>=0.25 && ratio<=0.49){
			g.setColor(Color.ORANGE);
		}else{
			g.setColor(Color.RED);
		}
		g.fillRect(150, 15,(int) health, 25);
		
		g.setColor(Color.white);
		g.drawRect(150, 15, 200, 25);
	}


}
