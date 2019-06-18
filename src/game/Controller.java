package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import entities.Enemy;
import entities.Friendly;

public class Controller {
	private LinkedList<Friendly> friendly = new LinkedList<Friendly>();
	private LinkedList<Enemy> enemy = new LinkedList<Enemy>();
	private Friendly tempFri;
	private Enemy tempEnemy;
	private Game game;
	private Player player;
	private double enemySpeed = 4;

	private long waveStartTimer;
	private long waveStartTimerDiff;
	// private int waveNumber;
	private boolean waveStart;
	private int waveDelay = 3000;

	public Controller(Sprites sprites, Game game, Player player) {
		this.game = game;
		this.player = player;
		waveStartTimer = 0;
		waveStartTimerDiff = 0;
		waveStart = true;

	}

	public void tick() {
		if (waveStartTimer == 0 && enemy.size() == 0) {
			game.setEnemyCount(game.getEnemyCount() + 1);
			// waveNumber++;
			waveStart = false;
			waveStartTimer = System.nanoTime();

		} else {
			waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
			if (waveStartTimerDiff > waveDelay) {
				waveStart = true;
				waveStartTimer = 0;
				waveStartTimerDiff = 0;
			}
		}

		// creste enemies
		if (waveStart && enemy.size() == 0) {
			enemy.clear();
			enemySpeed += 0.5;

			game.setBulletSpeed(game.getBulletSpeed() + 0.5);
			addEnemy(game.getEnemyCount());

		}

		for (int i = 0; i < friendly.size(); i++) {
			tempFri = friendly.get(i);
			tempFri.tick();
			if (tempFri.getY() <= 45) {
				removeFriendly(tempFri);
			}
		}
		for (int i = 0; i < enemy.size(); i++) {
			tempEnemy = enemy.get(i);
			tempEnemy.tick();

		}

	}

	public void render(Graphics g) {

		if (waveStartTimer != 0) {
			g.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			String s = "- WAVE  " + game.getEnemyCount();
			int length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
			int alpha = (int) (255 * Math.sin(3.14 * waveStartTimerDiff / waveDelay));
			if (alpha > 255) {
				alpha = 255;
			}
			g.setColor(new Color(255, 255, 255, alpha));
			g.drawString(s, (int) (Game.PLAY_AREA_WIDTH / 2 - length), (int) (Game.PLAY_AREA_HEIGHT / 2));
		}

		for (int i = 0; i < friendly.size(); i++) {
			tempFri = friendly.get(i);
			tempFri.render(g);

		}

		for (int i = 0; i < enemy.size(); i++) {
			tempEnemy = enemy.get(i);
			tempEnemy.render(g);

		}
	}

	public void addFriendly(Friendly f) {
		friendly.add(f);
	}

	public void addEnemy(Enemy e) {
		enemy.add(e);
	}

	private void addEnemy(int enemyCount) {

		for (int i = 0; i < enemyCount; i++) {
			addEnemy(new Enemies(1, 1, game, this, player, enemySpeed));
		}
	}

	public void removeEnemy(Enemy e) {
		enemy.remove(e);
	}

	public void removeFriendly(Friendly f) {
		friendly.remove(f);
	}

	public LinkedList<Friendly> getFriendly() {
		return friendly;
	}

	public LinkedList<Enemy> getEnemy() {
		return enemy;
	}

}
