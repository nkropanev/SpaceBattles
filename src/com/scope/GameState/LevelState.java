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
		
		bg = new Background("/Background/background.png");
		
	}
	
	public void update() {}
	
	public void draw(Graphics2D g) {
		bg.draw(g);
	}
	
	public void handleInput() {}

	
}
