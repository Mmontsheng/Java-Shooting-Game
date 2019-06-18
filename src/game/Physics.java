package game;

import entities.Enemy;
import entities.Friendly;


public class Physics {
	
	public static boolean collision(Enemy enemy, Friendly friendly) {
		//for (int i = 0; i < entb.size(); i++) {
			if(enemy.getBounds().intersects(friendly.getBounds())){
				return true;
			}
		
		return false;
	}
	public static boolean collision(Friendly friendly, Enemy enemy) {
		//for (int i = 0; i < entb.size(); i++) {
			if(friendly.getBounds().intersects(enemy.getBounds())){
				return true;
			}
		
		return false;
	}
	
	
	
	
	

}
