package com.scope.GameState;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.scope.Entity.Background;
import com.scope.Entity.EMissile;
import com.scope.Entity.Group;
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
	private Group group;
	private EMissile emissile;
	private static boolean playerShouldDie;
	
	private static int score;
	private static int highScore = 0;
	private static final double enemyMS = 0.4;
	private static final double incEnemyMS = 0.4;
	private static final long enemyReload = 1000;
	private static final long decEnemyReload = 300;
	
	public LevelState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		InputHandler.setFalse();
		
		playerShouldDie = false;
		
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
			bg = new Background("/Sprites/Background/background.png", 1);
			bg.setVector(0, 0.5);
			
			//init player
			player = new Player(13*GamePanel.SCALE,	20*GamePanel.SCALE);
			player.setPosition((GamePanel.WIDTH-player.getWidth()/2)/2, 
					GamePanel.HEIGHT-player.getHeight()/GamePanel.SCALE-2*GamePanel.SCALE);
			
			
			//init pmissile
			pmissile = new PMissile(3*GamePanel.SCALE, 4*GamePanel.SCALE);
			pmissile.setPosition(player.getx()+5, player.gety());
			
			//init group of enemies
			group = new Group(enemyMS, enemyReload);
			
			//init emissile
			emissile = new EMissile(3*GamePanel.SCALE, 4*GamePanel.SCALE);
<<<<<<< HEAD
			emissile.setPosition(128, 128);
=======
>>>>>>> origin/master
			
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
	public static void killPlayer() { playerShouldDie = true; }
	
	public void update() {
<<<<<<< HEAD
		
=======
>>>>>>> origin/master
		bg.update();
		player.update();
		pmissile.setDX(player.getx() + 5);
		pmissile.update();
		group.update();
<<<<<<< HEAD
		
		//emissile.update();
=======
		group.choseBG(player.getx());
		if(group.getShoot()) {
			emissile.setPosition(group.getAX(), group.getAY());
			emissile.setFlying(true);
		}
		emissile.update();
		emissile.checkExpl(player);
>>>>>>> origin/master
		
		//collision of the player and the badGuy
		for(int i = 0; i < group.bg4Arr.size(); i++) {
			if(group.bg4Arr.get(i).isAlive() && !player.isFlinch())
				if(group.checkIntersectsBg4(group.bg4Arr.get(i), player) ||
						group.checkExplBg4(group.bg4Arr.get(i), pmissile))
					score += group.bg4Arr.get(i).getCost();
		}
		
		for(int i = 0; i < group.bg3Arr.size(); i++) {
			if(group.bg3Arr.get(i).isAlive() && !player.isFlinch())
				if(group.checkIntersectsBg3(group.bg3Arr.get(i), player) ||
						group.checkExplBg3(group.bg3Arr.get(i), pmissile))
					score += group.bg3Arr.get(i).getCost();
		}
		
		for(int i = 0; i < group.bg2Arr.size(); i++) {
			if(group.bg2Arr.get(i).isAlive() && !player.isFlinch())
				if(group.checkIntersectsBg2(group.bg2Arr.get(i), player) ||
						group.checkExplBg2(group.bg2Arr.get(i), pmissile))
					score += group.bg2Arr.get(i).getCost();
		}
		
		for(int i = 0; i < group.bg1Arr.size(); i++) {
			if(group.bg1Arr.get(i).isAlive() && !player.isFlinch())
				if(group.checkIntersectsBg1(group.bg1Arr.get(i), player) ||
						group.checkExplBg1(group.bg1Arr.get(i), pmissile))
					score += group.bg1Arr.get(i).getCost();
		}
		
		for(int i = 0; i < group.bg11Arr.size(); i++) {
			if(group.bg11Arr.get(i).isAlive() && !player.isFlinch())
				if(group.checkIntersectsBg1(group.bg11Arr.get(i), player) ||
						group.checkExplBg1(group.bg11Arr.get(i), pmissile))
					score += group.bg11Arr.get(i).getCost();	
		}
		
		pmissile.checkAttack(player);
		
		if(score > highScore) {
			highScore = score;
		}
		
		if(group.empty) {
			group.setGroupMS(group.getGroupMS()+incEnemyMS);
			group.setReloadTimer(group.getReloadTimer()+decEnemyReload);
			group.initNewGroup();
		}
		
		if(playerShouldDie) {
			player.setHealth(player.getHealth() - 1);
			if(player.getHealth() < 0)
				player.setHealth(0);
		}
		
		playerShouldDie = false;
		
		//game over
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
		
		handleInput();
		
	}
	
	public void draw(Graphics2D g) {

		//draw bg
		bg.draw(g);
		
		//draw player
		player.draw(g);
		
		//draw pmissile
		pmissile.draw(g);
		
		//draw alive enemies
		group.draw(g);
		
		//draw emissile
<<<<<<< HEAD
		//emissile.draw(g);
=======
		emissile.draw(g);
>>>>>>> origin/master
		
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
