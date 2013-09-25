package com.gamemaker.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.gamemaker.actions.MoveLeft;
import com.gamemaker.beans.Sprite;
import com.gamemaker.controllers.GameController;

public class GameControllerTest {

	@Test
	public void test() throws IOException {
		GameController gameController = new GameController();
		Sprite spriteObj = new Sprite();
		spriteObj.setxPos(9.0);
		spriteObj.setxSpeed(2);

		gameController.update(spriteObj);

		double currentSpeed = spriteObj.getxSpeed();

		assertEquals(-2, (int) currentSpeed);

	}

}
