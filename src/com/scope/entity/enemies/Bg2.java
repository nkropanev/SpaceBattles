package com.scope.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.scope.animation.Animation;
import com.scope.entity.Enemy;

public class Bg2 extends Enemy {

    private BufferedImage[] sprites;
    private BufferedImage[] sSprites;

    public Bg2(int w, int h) {

        super(w, h);

        cost = 20;

        //load sprites
        sprites = loadImages("/Sprites/Enemies/bg2", 4);

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(500);

    }

    public void set2ndAnimation() {

        sSprites = loadImages("/Sprites/Enemies/bg2", 4);
        BufferedImage buff = sSprites[0];
        for (int i = 0; i < 3; i++) {
            sSprites[i] = sSprites[i + 1];
        }
        sSprites[3] = buff;
        animation.setFrames(sSprites);

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
