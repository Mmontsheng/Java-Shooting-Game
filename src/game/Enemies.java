package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Enemy;
import entities.Friendly;

public class Enemies implements Enemy {
	// private Sprites sprites;
	private double y;
	private double x;
	private int r;

	private double dy;
	private double dx;
	private double rad;
	private int health;

	private boolean ready;
	private boolean dead;

	private Game game;
	private Controller controller;

	private Player player;

	// private Random rand = new Random();

	public Enemies(int type, int rank, Game game, Controller controller, Player player, double enemySpeed) {
		// super(x, y);
		// this.sprites = sprites;
		this.game = game;
		this.controller = controller;
		this.player = player;
		r = 16;
		
		this.x = (Math.random() * Game.PLAY_AREA_WIDTH / 2) + (Game.PLAY_AREA_WIDTH / 4);
		this.y = 50 + r;

		double angle = Math.random() * 140 + 20;
		rad = Math.toRadians(angle);

		dx = Math.cos(rad) * enemySpeed;
		dy = Math.sin(rad) * enemySpeed;

		ready = false;
		dead = false;

	}

	public void hit() {
		health--;
		if (health <= 0) {
			dead = true;
		}
	}

	public boolean isDead() {
		return dead;
	}

	@Override
	public void tick() {
		// int ra
		y += dy;
		x += dx;

		if (!ready) {
			if (x > (Game.PLAY_AREA_WIDTH + r) && x < Game.PLAY_AREA_WIDTH - r && y > (50 + r) && y < Game.PLAY_AREA_HEIGHT - r) {
				ready = true;
			}
		}

		if (x < r && dx < 0) {
			dx = -dx;
		}
		if (x > Game.PLAY_AREA_WIDTH - r && dx > 0) {
			dx = -dx;
		}
		if (y <= 50 + r && dy < 0) {
			dy = -dy;
		}
		if (y > (Game.PLAY_AREA_HEIGHT + 50) - r && dy > 0) {
			dy = -dy;
		}

		for (int i = 0; i < game.f.size(); i++) {
			// bullet collided with enemy
			Friendly tempF = game.f.get(i);
			if (Physics.collision(this, tempF)) {

				// remov enemy
				controller.removeEnemy(this);
				// remove bullet
				controller.removeFriendly(tempF);
				Game.score += 5;
				game.setEnemyKilled(game.getEnemyKilled() + 1);
				break;
			}
		}

		// player collides with enemies
		for (int i = 0; i < game.e.size(); i++) {
			if (Physics.collision(player, this)) {
				// controller.removeEnemy(tempEnt);
				Game.health -= 2;
				break;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 24, 24);
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
