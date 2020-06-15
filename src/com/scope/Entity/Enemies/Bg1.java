package com.scope.Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.scope.Animation.Animation;
import com.scope.Entity.Enemy;

public class Bg1 extends Enemy {

    private BufferedImage[] sprites;
    private BufferedImage[] sSprites;

    public Bg1(int w, int h) {

        super(w, h);

        cost = 10;

        //load sprites
        sprites = loadImages("/Sprites/Enemies/bg1", 4);

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(500);

    }

    public void set2ndAnimation() {

        sSprites = loadImages("/Sprites/Enemies/bg1", 4);
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
