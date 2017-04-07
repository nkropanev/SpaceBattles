package com.scope.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.scope.InputHandler.InputHandler;
import com.scope.NewGame.Game;
import com.scope.NewGame.GamePanel;

public class MenuState extends GameState {

	//private Background bg;
	private Color bgColor;
	
	private int currentChoise = 0;
	private String[] options = {
			"Start",
			"Quit"
	};
	
	private Color titleColor;
	private Color color;
	private Color color1;
	
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		
		super(gsm);
		
		try {
			
			//set backgrond
			bgColor = Color.BLACK;
			
			//set fonts and colors
			titleColor = Color.WHITE;
			color = Color.WHITE;
			color1 = Color.YELLOW;
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 14);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		init();
		
	}
	
	public void init() {
		
		InputHandler.setFalse();
		
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		//draw bg
		g.setColor(bgColor);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(Game.name, 90, 70);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			
			if(i == currentChoise) {
				g.setColor(color1);
			}
			else {
				g.setColor(color);
			}
			g.drawString(options[i], 145, 145 + i * 15);
			
		}
		
	}
	
	public void select() {
		
		if(currentChoise == 0) {
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoise == 1) {
			System.exit(0);
		}
		
	}
	
	public void handleInput() {
		if(InputHandler.isPressed(InputHandler.ENTER)) select();
		if(InputHandler.isPressed(InputHandler.DOWN)) {
			currentChoise++;
			if(currentChoise == options.length)
				currentChoise = options.length - 1;
		}
		if(InputHandler.isPressed(InputHandler.UP)) {
			currentChoise--;
			if(currentChoise < 0)
				currentChoise = 0;
		}
		
	}
	
}
