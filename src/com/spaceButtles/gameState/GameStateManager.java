package com.spaceButtles.gameState;

import java.awt.*;

public class GameStateManager {

    public static final int NUM_GAME_STATES = 3;
    public static final int MENU_STATE = 0;
    public static final int LEVEL_STATE = 1;
    public static final int GAME_OVER = 2;
    private final GameState[] gameStates;
    private int currentState;
    private int score;

    public GameStateManager() {
        gameStates = new GameState[NUM_GAME_STATES];

        currentState = MENU_STATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        if (state == MENU_STATE)
            gameStates[state] = new MenuState(this);
        else if (state == LEVEL_STATE)
            gameStates[state] = new LevelState(this);
        else if (state == GAME_OVER)
            gameStates[state] = new GameOver(this);
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    public void update() {
        if (gameStates[currentState] != null)
            gameStates[currentState].update();
    }

    public void draw(Graphics2D g) {
        if (gameStates[currentState] != null)
            gameStates[currentState].draw(g);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
