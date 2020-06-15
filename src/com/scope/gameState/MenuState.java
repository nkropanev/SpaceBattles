package com.scope.gameState;

import com.scope.game.Game;
import com.scope.game.GamePanel;
import com.scope.inputHandler.InputHandler;

import java.awt.*;

public class MenuState extends GameStateImpl {

    private final String[] options = {
            "Start",
            "Quit"
    };
    private Color bgColor;
    private int currentChoice = 0;
    private Color titleColor;
    private Color color;
    private Color color1;

    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        init();
    }

    public void init() {
        InputHandler.setFalse();

        try {
            // set background
            bgColor = Color.BLACK;

            // set fonts and colors
            titleColor = Color.WHITE;
            color = Color.WHITE;
            color1 = Color.YELLOW;
            titleFont = new Font("Times New Roman", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 14);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        //draw bg
        g.setColor(bgColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString(Game.NAME, 25, 70);

        //draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(color1);
            } else {
                g.setColor(color);
            }
            g.drawString(options[i], 105, 165 + i * 15);
        }
    }

    public void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.LEVEL_STATE);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    public void handleInput() {
        if (InputHandler.isPressed(InputHandler.ENTER)) {
            select();
        }
        if (InputHandler.isPressed(InputHandler.DOWN)) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = options.length - 1;
            }
        }
        if (InputHandler.isPressed(InputHandler.UP)) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = 0;
            }
        }
    }

}
