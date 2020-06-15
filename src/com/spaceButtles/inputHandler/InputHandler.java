package com.spaceButtles.inputHandler;

import java.awt.event.KeyEvent;

public class InputHandler {

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    private static final int NUM_KEYS = 7;
    public static final boolean[] keyState = new boolean[NUM_KEYS];

    public static void setKey(int i, boolean b) {
        if (i == KeyEvent.VK_UP) keyState[UP] = b;
        else if (i == KeyEvent.VK_LEFT) keyState[LEFT] = b;
        else if (i == KeyEvent.VK_DOWN) keyState[DOWN] = b;
        else if (i == KeyEvent.VK_RIGHT) keyState[RIGHT] = b;
        else if (i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
        else if (i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
        else if (i == KeyEvent.VK_SPACE) keyState[SPACE] = b;
    }

    public static boolean isPressed(int i) {
        return keyState[i];
    }

    public static void setFalse() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keyState[i] = false;
        }
    }

}
