package com.scope.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.NewGame.GamePanel;

public class Enemy extends MapObject {

	private int currFace;
	private int cost;
	private BufferedImage sprite;
	
	public Enemy(int width, int height) {
		
		super(ENEMY_TYPE,0 , 0, width, height);
		this.width = width;
		this.height = height;
		
		alive = true;
		direction = 0;
		cost = 100;
		
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/enemy.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getCost() { return cost; }
	
	public void update() {
		
		setCollisions();
		if(alive) {
			if(y < GamePanel.HEIGHT) {
				y++;
			}
			else {
				y = GamePanel.HEIGHT/4;
			}
		}
		if(!alive) {
			alive = true;
			y = GamePanel.HEIGHT/4;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		if(alive) {
			g.drawImage(sprite, x, y, null);
		}
		
	}
	
}
