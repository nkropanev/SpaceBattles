package com.scope.Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.scope.Animation.Animation;
import com.scope.Entity.Enemy;

public class Bg4 extends Enemy {

    private BufferedImage[] sprites;

    public Bg4(int w, int h) {

        super(w, h);

        cost = 100;

        //load sprites
        sprites = loadImages("/Sprites/Enemies/g", 4);

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(450);
    }

    public void update() {

        super.update();
        animation.update();

    }

    public void draw(Graphics2D g) {

        if (alive)
            g.drawImage(animation.getImage(), (int) x, (int) y, null);

    }

}
