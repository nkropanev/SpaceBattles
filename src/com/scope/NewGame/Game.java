package com.scope.NewGame;

import javax.swing.JFrame;

public class Game {

	public static final String name = "Space battles";
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame(name);
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
	}
	
}
