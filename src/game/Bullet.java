package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Friendly;

public class Bullet extends GameObject  implements Friendly {
	private double bulletSpeed;

	public Bullet(double d, double e, Player player, Sprites sprites, double bulletSpeed) {
		super(d, e);
		this.bulletSpeed=bulletSpeed;
	}

	// @Override
	public void tick() {
		y -= bulletSpeed;
	}
	// @Override
	public void render(Graphics g) {
		//sprites.getBullet().paintIcon(null, g, (int)x,(int) y);
		g.setColor(Color.white);
		g.fillOval((int)x,(int) y, 15, 15);
	}

	// @Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 15, 15);
	}

	// @Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	// @Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	// @Override
	public void setX(double x) {
		this.x = x;
	}

	// @Override
	public void setY(double y) {
		this.y = y;
	}

}
