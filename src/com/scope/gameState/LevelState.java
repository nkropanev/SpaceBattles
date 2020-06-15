package com.scope.gameState;

import com.scope.entity.*;
import com.scope.game.GamePanel;
import com.scope.inputHandler.InputHandler;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.scope.entity.MapObject.*;

public class LevelState extends GameStateImpl {

    private static final double enemyMS = 0.4;
    private static final double incEnemyMS = 0.4;
    private static boolean playerShouldDie;
    private static int score;
    private static int highScore = 0;
    private Background bg;
    private Player player;
    private HUD hud;
    private PMissile pMissile;
    private Group group;

    public LevelState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    public static int getScore() {
        return score;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static void killPlayer() {
        playerShouldDie = true;
    }

    public void init() {
        InputHandler.setFalse();

        playerShouldDie = false;

        try {
            // load high score
            try {
                Scanner in = new Scanner(new File("res/HighScore.txt"));
                highScore = in.nextInt();
                in.close();
            } catch (FileNotFoundException e) {
                highScore = 0;
            }

            // init bg
            bg = new Background("/Sprites/Background/background.png", 1);
            bg.setVector(0, 0.5);

            // init player
            player = new Player(13 * GamePanel.SCALE, 20 * GamePanel.SCALE);
            player.setPosition((GamePanel.WIDTH - player.getWidth() / 2) / 2,
                    GamePanel.HEIGHT - player.getHeight() / GamePanel.SCALE - 2 * GamePanel.SCALE);

            // init pMissile
            pMissile = new PMissile(3 * GamePanel.SCALE, 4 * GamePanel.SCALE);
            pMissile.setPosition(player.getx() + 5, player.gety());

            // init group of enemies
            group = new Group(enemyMS);

            // hud
            hud = new HUD(player);

        } catch (Exception e) {
            e.printStackTrace();
        }

        score = 0;
    }

    public void update() {
        bg.update();
        player.update();
        pMissile.setDX(player.getx() + 5);
        pMissile.update();
        group.update();

        // collision of the player and the badGuy
        checkPlayerCollision();

        pMissile.checkAttack(player);

        if (score > highScore) {
            highScore = score;
        }

        if (group.isEmpty()) {
            group.setGroupMS(group.getGroupMS() + incEnemyMS);
            group.initNewGroup();
        }

        if (playerShouldDie) {
            player.setHealth(player.getHealth() - 1);
            if (player.getHealth() < 0)
                player.setHealth(0);
        }

        playerShouldDie = false;

        //game over
        if (player.getHealth() == 0) {

            try {
                PrintWriter out = new PrintWriter(new File("res/HighScore.txt"));
                out.print(highScore);
                out.close();
            } catch (FileNotFoundException ignored) {
            }

            gsm.setScore(score);
            gsm.setState(GameStateManager.GAME_OVER);

        }

        handleInput();
    }

    private void checkPlayerCollision() {
        for (ArrayList<Enemy> enemies : group.getGroup()) {
            for (Enemy bg : enemies) {
                if (checkBgPlayerCollision(bg)) {
                    break;
                }
            }
        }
    }

    private boolean checkBgPlayerCollision(Enemy bg) {
        if (bg.isAlive() && !player.isFlinch()) {
            if (group.checkIntersectsAll(bg, player) ||
                    group.checkExplBgAll(bg, pMissile)) {
                score += bg.getCost();
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g) {
        // draw bg
        bg.draw(g);
        // draw player
        player.draw(g);
        // draw pMissile
        pMissile.draw(g);
        // draw alive enemies
        group.draw(g);
        // draw hud
        hud.draw(g);
    }

    public void handleInput() {
        if (InputHandler.isPressed(InputHandler.LEFT)) {
            player.setDirection(MOVE_LEFT);
            pMissile.setDirection(MOVE_LEFT);
        } else if (InputHandler.isPressed(InputHandler.RIGHT)) {
            player.setDirection(MOVE_RIGHT);
            pMissile.setDirection(MOVE_RIGHT);
        } else {
            player.setDirection(NO_MOVE);
            pMissile.setDirection(NO_MOVE);
        }
        if (InputHandler.isPressed(InputHandler.SPACE)) {
            pMissile.setShoot(true);
        }
        if (!InputHandler.isPressed(InputHandler.SPACE)) {
            pMissile.setShoot(false);
        }
    }

}
