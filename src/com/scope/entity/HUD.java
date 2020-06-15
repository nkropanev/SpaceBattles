package com.scope.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.gameState.LevelState;

public class HUD {

    private Player player;
    private BufferedImage image;
    private Font font;
    private Font scoreFont;
    private Color color;
    private Color scoreColor;

    public HUD(Player p) {

        player = p;

        //load HUD sprites
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/HUD/extralife0.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        color = Color.RED;
        scoreColor = Color.WHITE;
        font = new Font("Arial", Font.PLAIN, 10);
        scoreFont = new Font("Arial", Font.PLAIN, 10);

    }

    public void draw(Graphics2D g) {

        for (int i = 0; i < player.getHealth(); i++) {
            g.drawImage(image, 3 + 13 * i, 5, null);
        }

        g.setColor(color);
        g.setFont(font);
        //score
        g.drawString("score", 52, 9);
        //high score
        g.drawString("high score", 128, 9);
        //values
        g.setColor(scoreColor);
        g.setFont(scoreFont);
        String score = String.valueOf(LevelState.getScore());
        g.drawString(score, 52, 19);
        String highScore = String.valueOf(LevelState.getHighScore());
        g.drawString(highScore, 128, 19);

    }

}
