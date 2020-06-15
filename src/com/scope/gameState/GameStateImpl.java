package com.scope.gameState;

import java.awt.*;

public abstract class GameStateImpl implements GameState {

    protected final GameStateManager gsm;

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
