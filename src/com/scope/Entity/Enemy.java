package com.scope.Entity;

public abstract class Enemy extends MapObject {

	protected int cost;
	protected double moveSpeed;
	protected boolean canShoot;
	
	public Enemy(int width, int height) {
		
		super(0 , 0, width, height);
		direction = 0;
		canShoot = false;
		
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void sety(int y) {
		this.y = y;
	}
	
	public void setMS(double ms) { moveSpeed = ms; }
	
	public int getCost() { return cost; }
	
	public void setShoot(boolean b) { canShoot = b; }
	public boolean getShoot() { return canShoot; }
	
	private void getNextPosition() {
		
		if(direction == MOVE_LEFT) {
			x -= moveSpeed;
		}
		else if(direction == MOVE_RIGHT) {
			x += moveSpeed;
		}
		
	}
	
	public void update() {
		getNextPosition();
	}
	
}
