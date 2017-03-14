package com.scope.GameState;

import java.awt.Graphics2D;

import com.scope.TileMap.Background;

public class LevelState extends GameState {

	Background bg;
	
	public LevelState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		bg = new Background("/Background/background-v2.png", 1);
		bg.setVector(0, 0.4);
		
	}
	
	public void update() {
		
		bg.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
	}
	
	public void handleInput() {}

	
}
