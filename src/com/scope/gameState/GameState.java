package com.scope.gameState;


import java.awt.*;

public interface GameState {
    void init();

    void update();

    void draw(Graphics2D g);

    void handleInput();
}
