/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * 	This model/bean is basically the central storage of the Game, all the sprites and events
 *  are stored inside this class. 
 */
package com.gamemaker.beans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GameBean {
	private String background;
	private double xSize;
	private double ySize;
	ArrayList<Sprite> sprites = null;

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) throws IOException {
		this.background = background;
	}

	public double getxSize() {
		return xSize;
	}

	public void setxSize(double xSize) {
		this.xSize = xSize;
	}

	public double getySize() {
		return ySize;
	}

	public void setySize(double ySize) {
		this.ySize = ySize;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(ArrayList<Sprite> sprites) {
		this.sprites = sprites;
	}

	public GameBean() throws IOException {
		sprites = new ArrayList<Sprite>();
	}

	public void removeSprite(int index) {
		sprites.remove(index);
	}

}
