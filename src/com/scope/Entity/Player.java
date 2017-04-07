package com.scope.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.NewGame.GamePanel;

public class Player extends MapObject {

	private int health;
	private int maxHealth;
	private int moveSpeed = (int)(2.3 * GamePanel.SCALE);
	private BufferedImage sprite;
	private boolean flinching;
	private long flinchTimer;
	
	public Player(int width, int height) {
		
		super(PLAYER_TYPE, 0, 0, width, height);
		
		this.width = width;
		this.height = height;
		
		health = maxHealth = 3;
		alive = true;
		direction = 0;
		
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/player1.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkAttack(Enemy e) {
		
		if(intersects(e) && !flinching) {
			e.alive = false;
			health--;
			if(health < 0) health = 0;
			if(health == 0) alive = false;
			flinching = true;
			flinchTimer = System.nanoTime();
		}
		
	}
	
	public boolean isFlinch() { return flinching; }
	public void setFlinch(boolean b) { flinching = b; }
	public void setFlinchTimer(long t) { flinchTimer = t; }
	public int getHealth() { return health; }
	public void setHealth(int h) { health = h; }
	public int getMaxHealth() { return maxHealth; }
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
		setCollisions();
		
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 1000) {
				flinching = false;
				x = (GamePanel.WIDTH-width/2)/2;
			}
		}
		
		if(direction == MOVE_LEFT) {
			x -= moveSpeed;
			if(x < 0) {
				x = 0;
			}
		}
		else if(direction == MOVE_RIGHT) {
			x += moveSpeed;
			if(x > (GamePanel.WIDTH - width/GamePanel.SCALE)) {
				x = GamePanel.WIDTH - width/GamePanel.SCALE;
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		if(!flinching) {
			g.drawImage(sprite, x, y, null); 
		}
		
	}
	
}
