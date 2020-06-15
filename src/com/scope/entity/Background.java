package com.scope.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.game.GamePanel;

public class Background {

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    //animations
    private int numFrames = 5;
    private BufferedImage[] frames;

    public Background(String s, double ms) {

        try {

            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(s));
            frames = new BufferedImage[numFrames];
            for (int i = 0; i < numFrames; i++) {
                frames[i] = image.getSubimage(
                        i * GamePanel.WIDTH,
                        0,
                        GamePanel.WIDTH,
                        GamePanel.HEIGHT);
            }

            moveScale = ms;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPosition(double x, double y) {

        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;

    }

    public void setVector(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;

    }

    public void update() {
        x += dx;
        y += dy;
        if (y == 1400)
            y = 0;
    }

    public void draw(Graphics2D g) {

        for (int i = 0; i < numFrames + 1; i++) {
            if (i != 5) {
                g.drawImage(
                        frames[i],
                        (int) x,
                        (int) y - i * GamePanel.HEIGHT,
                        null);
            } else {
                g.drawImage(
                        frames[0],
                        (int) x,
                        (int) y - i * GamePanel.HEIGHT,
                        null);
            }
        }

    }

}
