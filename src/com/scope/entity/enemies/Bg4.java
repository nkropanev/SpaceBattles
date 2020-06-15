package com.scope.entity.enemies;

import com.scope.animation.Animation;
import com.scope.entity.Enemy;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bg4 extends Enemy {

    public Bg4(int w, int h) {
        super(w, h);

        cost = 100;
        BufferedImage[] sprites = loadImages("/Sprites/Enemies/g", 4);
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(450);
    }

    public void update() {
        super.update();
        animation.update();
    }

    public void draw(Graphics2D g) {
        if (alive) {
            g.drawImage(animation.getImage(), (int) x, (int) y, null);
        }
    }

}
