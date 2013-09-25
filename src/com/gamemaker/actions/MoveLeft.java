/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * 	This class basically implements the Action 	Listener and has its own implementation i.e
 *  to Move Left based on keypressed call.
 */

package com.gamemaker.actions;

import com.gamemaker.beans.Sprite;

public class MoveLeft implements ActionTasks {

	private String className;

	public MoveLeft() {
		super();
		className = this.getClass().getName().toString();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public void perform(Sprite spriteObj) {
		double currentX = spriteObj.getxPos();
		double currentY = spriteObj.getyPos();
		// currentX += 5;
		currentX -= spriteObj.getxSpeed();
		spriteObj.setxPos(currentX);
	}

	@Override
	public String toString() {
		return this.getClass().getName().toString();
	}
}
