package com.scope.entity;

import com.scope.entity.enemies.Bg1;
import com.scope.entity.enemies.Bg2;
import com.scope.entity.enemies.Bg3;
import com.scope.entity.enemies.Bg4;
import com.scope.game.GamePanel;
import com.scope.gameState.LevelState;

import java.awt.*;
import java.util.ArrayList;

public class Group {

    private static final int bg1Num = 8;
    private static final int bg4Num = 2;
    private static final int bg2Num = 6;
    private static final int bg3Num = 4;

    private int count;
    private boolean empty;
    private int direction;
    private double groupMS;

    private ArrayList<Bg1> bg1Arr;
    private ArrayList<Bg1> bg11Arr;
    private ArrayList<Bg2> bg2Arr;
    private ArrayList<Bg3> bg3Arr;
    private ArrayList<Bg4> bg4Arr;

    private ArrayList<Enemy>[] group;

    public Group(double ms) {
        groupMS = ms;
        initNewGroup();
    }

    private void moveBack() {
        for (int i = 0; i < bg4Num; i++) {
            bg4Arr.get(i).setPosition(GamePanel.WIDTH / 2 - 27 + GamePanel.WIDTH / 11 * i, 40);
        }

        for (int i = 0; i < bg3Num; i++) {
            bg3Arr.get(i).setPosition(GamePanel.WIDTH / 3 - 8 + GamePanel.WIDTH / 11 * i, 55);
        }

        for (int i = 0; i < bg2Num; i++) {
            bg2Arr.get(i).setPosition(GamePanel.WIDTH / 4 - 9 + GamePanel.WIDTH / 11 * i, 70);
        }

        for (int i = 0; i < bg1Num; i++) {
            bg1Arr.get(i).setPosition(GamePanel.WIDTH / 8 + GamePanel.WIDTH / 11 * i, 85);

            bg11Arr.get(i).setPosition(GamePanel.WIDTH / 8 + GamePanel.WIDTH / 11 * i, 100);
        }
    }

    public boolean checkExplBgAll(Enemy en, PMissile pm) {

        if (en.intersects(pm)) {
            en.setAlive(false);
            pm.setFlying(false);
            pm.setPosition(pm.getDX(),
                    GamePanel.HEIGHT - 20 * GamePanel.SCALE / GamePanel.SCALE - 2 * GamePanel.SCALE);
            return true;
        }
        return false;

    }

    public boolean checkIntersectsAll(Enemy en, Player p) {
        if (en.intersects(p) && !p.isFlinch()) {
            en.setAlive(false);
            p.setHealth(p.getHealth() - 1);
            if (p.getHealth() < 0) p.setHealth(0);
            if (p.getHealth() == 0) p.setAlive(false);
            p.setFlinch(true);
            p.setFlinchTimer(System.nanoTime());
            moveBack();
            return true;
        }
        return false;
    }

    public void initNewGroup() {
        bg4Arr = new ArrayList<>();
        bg3Arr = new ArrayList<>();
        bg2Arr = new ArrayList<>();
        bg1Arr = new ArrayList<>();
        bg11Arr = new ArrayList<>();
        group = new ArrayList[]{bg4Arr, bg3Arr, bg2Arr, bg1Arr, bg11Arr};

        for (int i = 0; i < bg4Num; i++) {
            Bg4 bg4 = new Bg4(13 * GamePanel.SCALE, 14);
            bg4.setPosition(GamePanel.WIDTH / 2 - 27 + GamePanel.WIDTH / 11 * i, 40);
            bg4.setMS(groupMS);
            bg4Arr.add(bg4);
        }

        for (int i = 0; i < bg3Num; i++) {
            Bg3 bg3 = new Bg3(13 * GamePanel.SCALE, 14);
            bg3.setPosition(GamePanel.WIDTH / 3 - 8 + GamePanel.WIDTH / 11 * i, 55);
            bg3.setMS(groupMS);
            if (i % 2 == 1) bg3.set2ndAnimation();
            bg3Arr.add(bg3);
        }

        for (int i = 0; i < bg2Num; i++) {

            Bg2 bg2 = new Bg2(13 * GamePanel.SCALE, 14);
            bg2.setPosition(GamePanel.WIDTH / 4 - 9 + GamePanel.WIDTH / 11 * i, 70);
            bg2.setMS(groupMS);
            if (i % 2 == 0) bg2.set2ndAnimation();
            bg2Arr.add(bg2);
        }

        for (int i = 0; i < bg1Num; i++) {
            Bg1 bg1 = new Bg1(13 * GamePanel.SCALE, 14);
            bg1.setPosition(GamePanel.WIDTH / 8 + GamePanel.WIDTH / 11 * i, 85);
            bg1.setMS(groupMS);
            if (i % 2 == 1) bg1.set2ndAnimation();
            bg1Arr.add(bg1);

            Bg1 bg11 = new Bg1(13 * GamePanel.SCALE, 14);
            bg11.setPosition(GamePanel.WIDTH / 8 + GamePanel.WIDTH / 11 * i, 100);
            bg11.setMS(groupMS);
            if (i % 2 == 1) bg11.set2ndAnimation();
            bg11Arr.add(bg11);
        }

        empty = false;
        recount();
        direction = 2;

    }

    private void recount() {
        count = 0;
        for (ArrayList<Enemy> enemies : getGroup()) {
            for (Enemy enemy : enemies) {
                if (enemy.isAlive()) {
                    count++;
                }
            }
        }
    }

    private void newY() {
        for (ArrayList<Enemy> enemies : getGroup()) {
            for (Enemy enemy : enemies) {
                enemy.sety(enemy.gety() + 10);
            }
        }
    }

    private void checkDirection() {
        for (ArrayList<Enemy> enemies : getGroup()) {
            for (Enemy enemy : enemies) {
                checkDirectionForBg(enemy.getx());
            }
        }
    }

    private void checkDirectionForBg(int getx) {
        if (getx >= GamePanel.WIDTH - 13) {
            direction = 1;
            newY();
        } else if (getx <= 0) {
            direction = 2;
        }
    }

    public double getGroupMS() {
        return groupMS;
    }

    public void setGroupMS(double ms) {
        groupMS = ms;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void update() {
        //upd bg4Arr
        for (Bg4 bg4 : bg4Arr) {
            bg4.direction = direction;
            if (bg4.isAlive()) {
                bg4.update();
                if (bg4.gety() >= GamePanel.HEIGHT) {
                    moveBack();
                    LevelState.killPlayer();
                }
            }
        }

        for (int i = 0; i < bg1Arr.size(); i++) {
            //upd bg1Arr
            bg1Arr.get(i).direction = direction;
            if (bg1Arr.get(i).isAlive()) {
                bg1Arr.get(i).update();
                if (bg1Arr.get(i).gety() >= GamePanel.HEIGHT) {
                    moveBack();
                    LevelState.killPlayer();
                }
            }

            //upd bg11Arr
            bg11Arr.get(i).direction = direction;
            if (bg11Arr.get(i).isAlive()) {
                bg11Arr.get(i).update();
                if (bg11Arr.get(i).gety() >= GamePanel.HEIGHT) {
                    moveBack();
                    LevelState.killPlayer();
                }
            }

            //upd bg2Arr
            if (i > 0 && i < bg1Arr.size() - 1) {
                bg2Arr.get(i - 1).direction = direction;
                if (bg2Arr.get(i - 1).isAlive()) {
                    bg2Arr.get(i - 1).update();
                    if (bg2Arr.get(i - 1).gety() >= GamePanel.HEIGHT) {
                        moveBack();
                        LevelState.killPlayer();
                    }
                }

            }

            //upd bg3Arr
            if (i > 1 && i < bg1Arr.size() - 2) {
                bg3Arr.get(i - 2).direction = direction;
                if (bg3Arr.get(i - 2).isAlive()) {
                    bg3Arr.get(i - 2).update();
                    if (bg3Arr.get(i - 2).gety() >= GamePanel.HEIGHT) {
                        moveBack();
                        LevelState.killPlayer();
                    }
                }

            }

        }

        recount();

        if (count == 0) {
            empty = true;
        }

        checkDirection();
    }

    public void draw(Graphics2D g) {
        for (ArrayList<Enemy> enemies : getGroup()) {
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }
        }

    }

    public ArrayList<Enemy>[] getGroup() {
        return group;
    }

}
