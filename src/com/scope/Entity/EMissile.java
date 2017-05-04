package com.scope.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.NewGame.GamePanel;

public class EMissile extends MapObject {

	private int flightSpeed = (int)(2.0 * GamePanel.SCALE);
	private BufferedImage sprite;
	private boolean shot;
	private boolean flying;
	private int dx;
	
	public EMissile(int width, int height) {
		
		super(0, 0, width, height);
		dx = (int)x;
		shot = false;
		flying = false;
		
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
	
	public int getDX() {
		return dx;
	}
	
	public void setFlying(boolean b) {
		flying = b;
	}
	
	public boolean isFlying() { return flying; }
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setShoot(boolean b) {
		shot = b;
	}
	
	public void checkExpl(Player p) {
		
		if(intersects(p)) {
			flying = false;
			p.setHealth(p.getHealth()-1);
			if(p.getHealth() < 0) p.setHealth(0);
			if(p.getHealth() == 0) p.setAlive(false);
			p.setFlinch(true);
			p.setFlinchTimer(System.nanoTime());
		}
		
	}
	
	public void update() {
		
		if(flying) {
			y += flightSpeed;
			if( y>= GamePanel.HEIGHT + height)
				flying = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		if(flying)
			g.drawImage(sprite, (int)x, (int)y, null);
			
	}
	
}
