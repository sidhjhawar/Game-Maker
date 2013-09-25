/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 *  This is the Unit test class for testing the Move left action class
 */

package com.gamemaker.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.gamemaker.actions.MoveLeft;
import com.gamemaker.beans.Sprite;

public class MoveLeftTest {

	@Test
	public void test() throws IOException {
		MoveLeft left1 = new MoveLeft();
		double currentX = 12.0;
		Sprite spriteObj = new Sprite();
		left1.perform(spriteObj);
		spriteObj.setxPos(currentX);
		spriteObj.setxSpeed(4);

		currentX -= spriteObj.getxSpeed();
		assertEquals(8, (int) currentX);

	}

}
