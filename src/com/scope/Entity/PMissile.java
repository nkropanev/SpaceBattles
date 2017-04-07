package com.scope.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.NewGame.GamePanel;

public class PMissile extends MapObject{

	private int flightSpeed = (int)(2.7 * GamePanel.SCALE);
	private int moveSpeed = (int)(2.3 * GamePanel.SCALE);
	private BufferedImage sprite;
	private boolean hit;
	private boolean shot;
	private boolean flying;
	private int dx;
	private boolean flinching;
	private long flinchTimer;
	
	public PMissile(int width, int height) {
		
		super(PMISSILE_TYPE, 0, 0, width, height);
		
		dx = x;
		
		shot = false;
		flying = false;
		
		this.width = width;
		this.height = height;
		
		direction = 0;
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/pmissile0.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setDX(int x) {
		dx = x;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setShoot(boolean b) {
		shot = b;
	}
	
	public void setHit() {
		
		if(hit) return;
		hit = true;
		
	}
	
	public boolean getHit() { return hit; }
	
	public void checkExpl(Enemy e) {
		
		if(intersects(e)) {
			e.setAlive(false);
			flying = false;
			y = GamePanel.HEIGHT-20*GamePanel.SCALE/GamePanel.SCALE-2*GamePanel.SCALE;
			x = dx;
		}
		
	}
	
	public void checkAttack(Player p) {
		
		flinching = p.isFlinch();
		
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
		
		if(shot && !flying) {
			flying = true;
		}
		
		if(flying) {
			setPosition(x, y - flightSpeed);
			if(y <= 0) {
				flying = false;
				y = GamePanel.HEIGHT-20*GamePanel.SCALE/GamePanel.SCALE-2*GamePanel.SCALE;
				x = dx;
			}
		}
		else {
			hit = false;
			y = GamePanel.HEIGHT-20*GamePanel.SCALE/GamePanel.SCALE-2*GamePanel.SCALE;
			if(direction == MOVE_LEFT) {
				x -= moveSpeed;
				if(x < 5) {
					x = 5;
				}
			}
			else if(direction == MOVE_RIGHT) {
				x += moveSpeed;
				if(x > (GamePanel.WIDTH - width/GamePanel.SCALE-5)) {
					x = GamePanel.WIDTH - width/GamePanel.SCALE-5;
				}
			}
			dx = x;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		if(!flinching) {
			g.drawImage(sprite, x, y, null);
		}
		
	}
	
}
