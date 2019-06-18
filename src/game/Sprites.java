package game;

import javax.swing.ImageIcon;

public class Sprites {

	private ImageIcon player, enemy, bullet;
	
	public Sprites() {
		getSprites();
	}
	
	public ImageIcon getBullet(){
		return bullet;
	}
	
	public ImageIcon getEnemy() {
		return enemy;
	}


	public ImageIcon getPlayer() {
		return player;
	}

	private void getSprites() {
		player = new ImageIcon("images/player.png");
		enemy = new ImageIcon("images/enemy.png");
		bullet=new ImageIcon("images/bullet.png");
	}
}