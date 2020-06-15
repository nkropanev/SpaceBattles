package com.scope.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.game.GamePanel;

public class PMissile extends MapObject {

    private int flightSpeed = (int) (2.7 * GamePanel.SCALE);
    private BufferedImage sprite;
    private boolean hit;
    private boolean shot;
    private boolean flying;
    private int dx;
    private boolean flinching;
    private long flinchTimer;

    public PMissile(int width, int height) {

        super(0, 0, width, height);

        dx = (int) x;

        shot = false;
        flying = false;

        direction = 0;
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/pmissile0.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDX(int x) {
        dx = x;
    }

    public int getDX() {
        return dx;
    }

    public void setFlying(boolean b) {
        flying = b;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setShoot(boolean b) {
        shot = b;
    }

    public void setHit() {

        if (hit) return;
        hit = true;

    }

    public boolean getHit() {
        return hit;
    }

    public void checkExpl(Enemy e) {

        if (intersects(e)) {
            e.setAlive(false);
            flying = false;
            y = GamePanel.HEIGHT - 20 * GamePanel.SCALE / GamePanel.SCALE - 2 * GamePanel.SCALE;
            x = dx;
        }

    }

    public void checkAttack(Player p) {

        flinching = p.isFlinch();

    }

    public void update() {

        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 1000) {
                flinching = false;
                x = (GamePanel.WIDTH - width / 2) / 2;
            }
        }

        if (shot && !flying) {
            flying = true;
        }

        if (flying) {
            y -= flightSpeed;
            if (y <= 0) {
                flying = false;
                y = GamePanel.HEIGHT - 20 * GamePanel.SCALE / GamePanel.SCALE - 2 * GamePanel.SCALE;
                x = dx;
            }
        } else {
            hit = false;
            y = GamePanel.HEIGHT - 20 * GamePanel.SCALE / GamePanel.SCALE - 2 * GamePanel.SCALE;
            x = dx;
        }

    }

    public void draw(Graphics2D g) {

        if (!flinching) {
            g.drawImage(sprite, (int) x, (int) y, null);
        }

    }

}
