package com.gamemaker.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.gamemaker.actions.MoveLeft;
import com.gamemaker.beans.*;

public class GameBeanTest {

	@Test
	public void test() {
		GameBean gamebean = null;
		try {
			gamebean = new GameBean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();

		Sprite spriteObj = null;
		try {
			spriteObj = new Sprite();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sprite spriteObj1 = null;
		try {
			spriteObj1 = new Sprite();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sprites.add(0, spriteObj);
		sprites.add(1, spriteObj1);

		// int a=1, b=1;
		gamebean.setSprites(sprites);
		assertEquals(sprites, gamebean.getSprites());
		gamebean.removeSprite(1);

		assertEquals(1, sprites.size());

	}

}
