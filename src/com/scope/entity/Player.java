package com.scope.entity;

import com.scope.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

    private int health;
    private BufferedImage sprite;
    private boolean flinching;
    private long flinchTimer;

    public Player(int width, int height) {
        super(0, 0, width, height);

        health = 3;
        direction = 0;

        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/player1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 1000) {
                flinching = false;
                x = (GamePanel.WIDTH - width / 2) / 2;
            }
        }

        int moveSpeed = 3;
        if (direction == MOVE_LEFT) {
            x -= moveSpeed;
            if (x < 0) {
                x = 0;
            }
        } else if (direction == MOVE_RIGHT) {
            x += moveSpeed;
            if (x > (GamePanel.WIDTH - width / GamePanel.SCALE)) {
                x = GamePanel.WIDTH - width / GamePanel.SCALE;
            }
        }
    }

    public void draw(Graphics2D g) {
        if (!flinching) {
            g.drawImage(sprite, (int) x, (int) y, null);
        }
    }

    public boolean isFlinch() {
        return flinching;
    }

    public void setFlinch(boolean b) {
        flinching = b;
    }

    public void setFlinchTimer(long t) {
        flinchTimer = t;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        health = h;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
