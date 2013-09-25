/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 */

package com.gamemaker.controllers;

import com.gamemaker.beans.Sprite;

public class CustomEventListener {

	public CustomEventListener() {
		// TODO Auto-generated constructor stub
	}

	public void BounceObject(Sprite spriteObj) {
		double currentX = spriteObj.getxPos();
		currentX += 20;
		spriteObj.setxPos(currentX);

	}

	public void PlayMusic() {

	}

	public void MoveLeft(Sprite spriteObj) {
		double currentX = spriteObj.getxPos();
		currentX += 5;
		spriteObj.setxPos(currentX);

	}

	public void MoveRight() {

	}

	public void MoveSW() {

	}

	public void MoveSE() {

	}

	public void MoveNW() {

	}

	public void MoveNE() {

	}

	public void ExplodeObject() {

	}

	public void ScoreChange() {

	}
}
