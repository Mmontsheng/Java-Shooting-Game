package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Enemy;

public class Bomb extends GameObject implements Enemy {

	private Game game;

	public Bomb(double x, double y, Game game) {
		super(x, y);
		this.game=game;
	}

	@Override
	public void tick() {
		y+=game.getBulletSpeed();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) x, (int) y, 5, 5);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) x, (int) y, 5, 5);
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

}
