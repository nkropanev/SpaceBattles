package com.scope.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.scope.NewGame.GamePanel;


public class Background {

	private BufferedImage image;
	
	public Background(String s) {
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream(s));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(
				image,
				0,
				0,
				null);
	}
	
}
