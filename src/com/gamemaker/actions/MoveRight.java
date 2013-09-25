/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * This class basically implements the Action Listener and has its own implementation i.e
 * to Move Right based on keypressed call.
 */

package com.gamemaker.actions;

import com.gamemaker.beans.Sprite;

public class MoveRight implements ActionTasks {

	private String className;

	public MoveRight() {
		super();
		className = this.getClass().getName().toString();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	// Moves to the right by 5 units.

	@Override
	public void perform(Sprite spriteObj) {
		// TODO Auto-generated method stub
		double currentX = spriteObj.getxPos();
		// currentX += 5 ;
		currentX += spriteObj.getxSpeed();
		spriteObj.setxPos(currentX);
	}

}
