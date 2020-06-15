package com.scope.gameState;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.scope.entity.Background;
import com.scope.entity.Group;
import com.scope.entity.HUD;
import com.scope.entity.PMissile;
import com.scope.entity.Player;
import com.scope.game.GamePanel;
import com.scope.inputHandler.InputHandler;

import static com.scope.entity.MapObject.*;

public class LevelState extends GameStateImpl {

    private Background bg;
    private Player player;
    private HUD hud;
    private PMissile pmissile;
    private Group group;
    private static boolean playerShouldDie;

    private static int score;
    private static int highScore = 0;
    private static final double enemyMS = 0.4;
    private static final double incEnemyMS = 0.4;

    public LevelState(GameStateManager gsm) {
        super(gsm);
        init();
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

            // init pmissile
            pmissile = new PMissile(3 * GamePanel.SCALE, 4 * GamePanel.SCALE);
            pmissile.setPosition(player.getx() + 5, player.gety());

            // init group of enemies
            group = new Group(enemyMS);

            // hud
            hud = new HUD(player);

        } catch (Exception e) {
            e.printStackTrace();
        }

        score = 0;

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

    public void update() {
        bg.update();
        player.update();
        pmissile.setDX(player.getx() + 5);
        pmissile.update();
        group.update();

        // collision of the player and the badGuy
        for (int i = 0; i < group.getBg4Arr().size(); i++) {
            if (group.getBg4Arr().get(i).isAlive() && !player.isFlinch()) {
                if (group.checkIntersectsAll(group.getBg4Arr().get(i), player) ||
                        group.checkExplBgAll(group.getBg4Arr().get(i), pmissile)) {
                    score += group.getBg4Arr().get(i).getCost();
                }
            }
        }

        for (int i = 0; i < group.getBg3Arr().size(); i++) {
            if (group.getBg3Arr().get(i).isAlive() && !player.isFlinch())
                if (group.checkIntersectsAll(group.getBg3Arr().get(i), player) ||
                        group.checkExplBgAll(group.getBg3Arr().get(i), pmissile))
                    score += group.getBg3Arr().get(i).getCost();
        }

        for (int i = 0; i < group.getBg2Arr().size(); i++) {
            if (group.getBg2Arr().get(i).isAlive() && !player.isFlinch())
                if (group.checkIntersectsAll(group.getBg2Arr().get(i), player) ||
                        group.checkExplBgAll(group.getBg2Arr().get(i), pmissile))
                    score += group.getBg2Arr().get(i).getCost();
        }

        for (int i = 0; i < group.getBg1Arr().size(); i++) {
            if (group.getBg1Arr().get(i).isAlive() && !player.isFlinch())
                if (group.checkIntersectsAll(group.getBg1Arr().get(i), player) ||
                        group.checkExplBgAll(group.getBg1Arr().get(i), pmissile))
                    score += group.getBg1Arr().get(i).getCost();
        }

        for (int i = 0; i < group.getBg11Arr().size(); i++) {
            if (group.getBg11Arr().get(i).isAlive() && !player.isFlinch())
                if (group.checkIntersectsAll(group.getBg11Arr().get(i), player) ||
                        group.checkExplBgAll(group.getBg11Arr().get(i), pmissile))
                    score += group.getBg11Arr().get(i).getCost();
        }

        pmissile.checkAttack(player);

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
            } catch (FileNotFoundException e) {
            }

            gsm.setScore(score);
            gsm.setState(GameStateManager.GAMEOVER);

        }

        handleInput();

    }

    public void draw(Graphics2D g) {
        // draw bg
        bg.draw(g);
        // draw player
        player.draw(g);
        // draw pmissile
        pmissile.draw(g);
        // draw alive enemies
        group.draw(g);
        // draw hud
        hud.draw(g);
    }

    public void handleInput() {
        if (InputHandler.isPressed(InputHandler.LEFT)) {
            player.setDirection(MOVE_LEFT);
            pmissile.setDirection(MOVE_LEFT);
        } else if (InputHandler.isPressed(InputHandler.RIGHT)) {
            player.setDirection(MOVE_RIGHT);
            pmissile.setDirection(MOVE_RIGHT);
        } else {
            player.setDirection(NO_MOVE);
            pmissile.setDirection(NO_MOVE);
        }
        if (InputHandler.isPressed(InputHandler.SPACE)) {
            pmissile.setShoot(true);
        }
        if (!InputHandler.isPressed(InputHandler.SPACE)) {
            pmissile.setShoot(false);
        }
    }


}
