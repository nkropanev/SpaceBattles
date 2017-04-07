package com.scope.Entity;

import java.awt.Rectangle;

import com.scope.Animation.Animation;

public abstract class MapObject {
	
	//types
	public static final int PLAYER_TYPE = -1;
	public static final int PMISSILE_TYPE = -2;
	public static final int ENEMY_TYPE = 1;
	
	//moving
	public static final int NO_MOVE = 0;
	public static final int MOVE_LEFT = 1;
	public static final int MOVE_RIGHT = 2;
	public int direction;
	
	//dimensions
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Rectangle collisionRect;
	protected Rectangle hotRect;
	protected int type;
	protected boolean alive;
	
	//attributes
	protected double initAttackFrequency;
	protected double levelincAttackFrequency;
	
	//animation
	protected Animation animation;
	
	
	public MapObject(int spriteType, int x, int y, int w, int h) {
		
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		alive = true;
		type = spriteType;
		
	}
	
	public int getx() { return x; }
	public int gety() { return y; }
	public int getHeight() { return height; }
	public int getWidth() { return width; }
	public boolean isAlive() { return alive; }
	public void setAlive(boolean a) { alive = a; }
	public int type() { return type; }
	
	public void setCollisions() {
		
		collisionRect = new Rectangle(x, y, width, height);
		
	}
	
	public boolean intersects(MapObject o) {
		
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
		
	}
	
	public Rectangle getRectangle() {
		
		return new Rectangle(x, y, width/2, height/2);
		
	}
	
}
