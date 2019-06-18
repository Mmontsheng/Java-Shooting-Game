package game;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Friendly;

public class Player extends GameObject implements Friendly {
	private Sprites sprites;
	// private String direction="up";

	private int velX = 0;
	private int velY = 0;

	public Player(int x, int y, Sprites sprites) {
		super(x, y);
		this.sprites = sprites;
	}

	public void tick() {
		x += velX;
		y += velY;

		if (x < 0) {
			x = 0;
		}
		if (x > Game.PLAY_AREA_WIDTH - 32) {
			x = Game.PLAY_AREA_WIDTH - 32;
		}
		if (y <= 50) {
			y = 50;
		}
		if (y > (Game.PLAY_AREA_HEIGHT + 16)) {
			y = Game.PLAY_AREA_HEIGHT + 16;
		}
	}

	public void render(Graphics g) {
		sprites.getPlayer().paintIcon(null, g, (int) x, (int) y);

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
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
