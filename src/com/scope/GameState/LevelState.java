package com.scope.GameState;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.scope.Entity.Background;
import com.scope.Entity.Enemy;
import com.scope.Entity.HUD;
import com.scope.Entity.PMissile;
import com.scope.Entity.Player;
import com.scope.InputHandler.InputHandler;
import com.scope.NewGame.GamePanel;

public class LevelState extends GameState {

	private Background bg;
	private Player player;
	private HUD hud;
	private PMissile pmissile;
	private Enemy enemy;
	
	private static int score;
	private static int highScore = 0;
	
	public LevelState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		InputHandler.setFalse();
		
		try {
			
			//load high score
			try {
				
				Scanner in = new Scanner(new File("res/HighScore.txt"));
				highScore = in.nextInt();
				in.close();
				
			}
			catch(FileNotFoundException e) {
				highScore = 0;
			}
			
			//init bg
			bg = new Background("/Sprites/Background/background-v2.png", 1);
			bg.setVector(0, 0.5);
			
			//init player
			player = new Player(13*GamePanel.SCALE,	20*GamePanel.SCALE);
			player.setPosition((GamePanel.WIDTH-player.getWidth()/2)/2, 
					GamePanel.HEIGHT-player.getHeight()/GamePanel.SCALE-2*GamePanel.SCALE);
			player.setCollisions();
			
			
			//init pmissile
			pmissile = new PMissile(3*GamePanel.SCALE, 4*GamePanel.SCALE);
			pmissile.setPosition(player.getx()+5, player.gety());
			pmissile.setCollisions();
			
			//init enemy
			enemy = new Enemy(13*GamePanel.SCALE, 20*GamePanel.SCALE);
			enemy.setPosition(GamePanel.WIDTH/2,
					GamePanel.HEIGHT/4);
			enemy.setCollisions();
			
			//hud
			hud = new HUD(player);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		score = 0;
		
	}
	
	public static int getScore() { return score; }
	public static int getHighScore() { return highScore; }
	
	public void update() {
		
		bg.update();
		player.update();
		pmissile.setDX(player.getx() + 5);
		pmissile.update();
		player.checkAttack(enemy);
		pmissile.checkAttack(player);
		pmissile.checkExpl(enemy);
		
		if(!enemy.isAlive()) {
			score += enemy.getCost();
		}
		
		if(score > highScore) {
			highScore = score;
		}
		
		if(player.getHealth() == 0) {
			
			try {
				
				PrintWriter out = new PrintWriter(new File("res/HighScore.txt"));
				out.print(highScore);
				out.close();
				
			}
			catch(FileNotFoundException e) {
			}
			
			gsm.score = score;
			gsm.setState(GameStateManager.GAMEOVER);
			
		}
		enemy.update();
		handleInput();
		
	}
	
	public void draw(Graphics2D g) {

		//draw bg
		bg.draw(g);
		
		//draw player
		player.draw(g);
		
		//draw pmissile
		pmissile.draw(g);
		
		//draw enemy
		enemy.draw(g);
		
		//draw hud
		hud.draw(g);
		
	}
	
	public void handleInput() {
		
		if(InputHandler.isPressed(InputHandler.LEFT)) {
			player.direction = 1;
			pmissile.direction = 1;
		}
		else if(InputHandler.isPressed(InputHandler.RIGHT)) {
			player.direction = 2;
			pmissile.direction = 2;
		}
		else {
			player.direction = 0;
			pmissile.direction = 0;
		}
		
		if(InputHandler.isPressed(InputHandler.SPACE)) {
			pmissile.setShoot(true);
		}
		
		if(!InputHandler.isPressed(InputHandler.SPACE)) {
			pmissile.setShoot(false);
		}
		
	}

	
}
