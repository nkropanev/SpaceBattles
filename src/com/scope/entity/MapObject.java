package com.scope.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.scope.animation.Animation;
import com.scope.game.GamePanel;

public abstract class MapObject {

    //moving
    public static final int NO_MOVE = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;

    protected int direction;

    //dimensions
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean alive;

    //animation
    protected Animation animation;


    public MapObject(int x, int y, int w, int h) {

        this.x = x;
        this.y = y;
        width = w;
        height = h;
        alive = true;

    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean a) {
        alive = a;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean intersects(MapObject o) {

        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);

    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, width / GamePanel.SCALE, height / GamePanel.SCALE);
    }

    public BufferedImage[] loadImages(String first, int n) {

        BufferedImage[] buff = new BufferedImage[n];

        try {

            for (int i = 0; i < n; i++) {
                buff[i] = ImageIO.read(
                        getClass().getResourceAsStream(first + i + ".png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buff;

    }

}
