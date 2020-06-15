package com.spaceButtles.entity;

import com.spaceButtles.gameState.LevelState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {

    private final Player player;
    private final Font font;
    private final Font scoreFont;
    private final Color color;
    private final Color scoreColor;
    private BufferedImage image;

    public HUD(Player p) {
        player = p;

        // load HUD sprites
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/HUD/extra_life0.png"));
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
        // score
        g.drawString("score", 52, 9);
        // high score
        g.drawString("high score", 128, 9);
        // values
        g.setColor(scoreColor);
        g.setFont(scoreFont);
        String score = String.valueOf(LevelState.getScore());
        g.drawString(score, 52, 19);
        String highScore = String.valueOf(LevelState.getHighScore());
        g.drawString(highScore, 128, 19);
    }

}
