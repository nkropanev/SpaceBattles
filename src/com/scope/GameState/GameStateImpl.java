package com.scope.GameState;

import java.awt.Graphics2D;

public abstract class GameStateImpl implements GameState {

	protected GameStateManager gsm;
	
	public GameStateImpl(GameStateManager gsm) {
		this.gsm = gsm;
	}

	@Override
	public abstract void update();
	@Override
	public abstract void draw(Graphics2D g);
	@Override
	public abstract void handleInput();
	
}
