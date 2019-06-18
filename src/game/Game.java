package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

import entities.Enemy;
import entities.Friendly;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// bullet shooting delay
	private int shootDelay = 200;
	private long shootTimer = System.nanoTime();

	// game dimensions
	public static final double WIDTH = 840;
	public static final double HEIGHT = 700;

	public static final double PLAY_AREA_WIDTH = WIDTH - 7;
	public static final double PLAY_AREA_HEIGHT = HEIGHT - 80;
	public static final String gameTittle = "Shooting Space";

	// game loop
	private boolean running = false;
	private Thread thread;

	
	private double bulletSpeed = 5;
	
	
	// graphics
	private BufferedImage image = new BufferedImage((int) WIDTH, (int) HEIGHT, BufferedImage.TYPE_INT_BGR);
	private Sprites sprites;
	private GameExtras extras;

	// objects
	private Player player;
	private Controller controller;
	public LinkedList<Friendly> f;
	public LinkedList<Enemy> e;

	static int score = 0;
	private int enemyKilled = 0;
	private int enemyCount=-1;
	static double health = 200;

	public Game() {

		// setPreferredSize(new Dimension((int)WIDTH, (int)HEIGHT));
		// setMaximumSize(new Dimension((int)WIDTH, (int)HEIGHT));
		// setMinimumSize(new Dimension((int)WIDTH, (int)HEIGHT));
		setFocusable(true);
		requestFocusInWindow();

		JFrame frame = new JFrame(gameTittle);
		frame.setBounds(10, 10, (int) WIDTH, (int) HEIGHT);
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		// frame.add(keyInput);
		start();
	}

	private void initialize() {
		sprites = new Sprites();
		extras = new GameExtras();
		new KeyInput(this);
		addKeyListener(new KeyInput(this));
		player = new Player((int) PLAY_AREA_WIDTH / 2, (int) PLAY_AREA_HEIGHT + 32, sprites);

		controller = new Controller(sprites, this, player);
		
		e = controller.getEnemy();
		f = controller.getFriendly();

	}

	// game loop
	public void run() {
		initialize();
		long lasTime = System.nanoTime();
		final double amoutOfTicks = 60.0;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lasTime) / ns;
			lasTime = now;
			if (delta >= 1) {
				tick();
				delta--;

			}
			render();
			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
			}
		}
		stop();
	}

	private void tick() {
		player.tick();
		controller.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		player.render(g);
		controller.render(g);
		extras.renderPlayArea(g, (int) PLAY_AREA_WIDTH, (int) PLAY_AREA_HEIGHT);
		extras.drawHealthBar(g, health);
		extras.renderText(g, "Score  ", score, 15, 15);
		extras.renderText(g, "Enemy Killed  ", enemyKilled, 15, 35);

		g.dispose();
		bs.show();

	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			player.setVelX(-5);
		} else if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(5);
		} else if (key == KeyEvent.VK_UP) {
			player.setVelY(-5);
		} else if (key == KeyEvent.VK_DOWN) {
			player.setVelY(+5);

		} else if (key == KeyEvent.VK_SPACE) {

			long elapsed = (System.nanoTime() - shootTimer) / 1000000;
			if (elapsed > shootDelay) {
				controller.addFriendly(new Bullet(player.getX()+7, player.getY() - 5, player, sprites, bulletSpeed));
				// enemy fire bomb
				shootTimer = System.nanoTime();
			}

		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_UP) {
			player.setVelY(0);
		} else if (key == KeyEvent.VK_DOWN) {
			player.setVelY(0);
		}
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public double getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(double d) {
		this.bulletSpeed = d;
	}
	
	
	
	

}
