/*
F * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 *  This is the main controller , it receives updates of user interaction with the MainGameView
 *  This class handles all the traffic between Model and View.
 *  Game Controller also implements all the Game Logic.
 */

package com.gamemaker.controllers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.gamemaker.actions.ActionTasks;
import com.gamemaker.beans.Event;
import com.gamemaker.beans.GameBean;
import com.gamemaker.beans.Observer;
import com.gamemaker.beans.Sprite;
import com.gamemaker.constants.Dimensions;
import com.gamemaker.views.MainGameView;

public class GameController implements Observer {

	private MainGameView mainGameView;
	private GameBean gameBean;
	private BufferedImage image;
	private CustomEventListener customListener;
	static Logger logger = Logger.getLogger(GameController.class);

	public GameController() {
		customListener = new CustomEventListener();
	}

	public CustomEventListener getCustomListener() {
		return customListener;
	}

	public void setCustomListener(CustomEventListener customListener) {
		this.customListener = customListener;
	}

	public MainGameView getMainGameView() {
		return mainGameView;
	}

	public void setMainGameView(MainGameView mainGameView) {
		this.mainGameView = mainGameView;
	}

	public GameBean getGameBean() {
		return gameBean;
	}

	public void setGameBean(GameBean gameBean) {
		this.gameBean = gameBean;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public GameController(MainGameView mainGameView) {
		this.mainGameView = mainGameView;
	}

	// This method handles the request of delete sprite from the user.
	public void deleteSprite(String sprite) {
		for (int i = 0; i < gameBean.getSprites().size(); i++) {
			System.out.println("Deleted");
			if (gameBean.getSprites().get(i).getName().equals(sprite)) {
				gameBean.removeSprite(i);
				break;
			}

		}
		mainGameView.repaint();

	}

	// This method adds the sprite to the list of the sprite in Game Bean
	public void addSprite(String name, String imgName, int spriteX, int spriteY)
			throws IOException {
		// gameBean = new GameBean();
		gameBean = mainGameView.getGameBean();

		Sprite sprite = new Sprite();
		Event event = new Event();
		ArrayList<Sprite> spriteList;
		if (gameBean.getSprites() == null) {
			spriteList = new ArrayList<Sprite>();
			gameBean.setSprites(spriteList);
		}
		sprite.setxSpeed(Integer.parseInt(mainGameView.getLeftPanelView()
				.getxSpeedTextField().getText()));
		sprite.setySpeed(Integer.parseInt(mainGameView.getLeftPanelView()
				.getySpeedTextField().getText()));

		sprite.setName(name);
		sprite.setImage(imgName);
		sprite.setxPos(spriteX);
		sprite.setyPos(spriteY);
		spriteList = gameBean.getSprites(); // adding sprite to the sprite list
											// in GameBean
		spriteList.add(sprite);

		gameBean.setSprites(spriteList);
		addSpriteToPane(sprite);
	}

	// This method helps to render the sprite to the View.
	public void addSpriteToView(String name, String imgName, int spriteX,
			int spriteY) throws IOException {
		// gameBean = new GameBean();
		gameBean = mainGameView.getGameBean();

		Sprite sprite = new Sprite();
		Event event = new Event();
		ArrayList<Sprite> spriteList;
		if (gameBean.getSprites() == null) {
			spriteList = new ArrayList<Sprite>();
			gameBean.setSprites(spriteList);
		}

		addSpriteToPane(sprite);
	}

	/*
	 * Adding the sprite to the right pane which represents te game panel
	 */
	private void addSpriteToPane(Sprite sprite) {
		logger.info("Sprite Added to the pane ");
		mainGameView.repaint();
	}

	/*
	 * This method handles the user request to edit the existing sprite inside
	 * the game. The sprite name and all the parameters are received from the
	 * View , the Controller checks which sprite user wants to edit/update and
	 * then saves the new values.
	 */
	public void editSprite(String name, String imgName, int spriteX, int spriteY)
			throws IOException {
		Sprite sprite = new Sprite();
		Event event = new Event();
		ArrayList<Sprite> spriteList;
		spriteList = gameBean.getSprites();
		for (int i = 0; i < spriteList.size(); i++) {
			logger.info(spriteList.get(i).getName().toString());
			if (spriteList.get(i).getName().toString().equalsIgnoreCase(name)) {
				sprite.setxSpeed(Integer.parseInt(mainGameView
						.getLeftPanelView().getxSpeedTextField().getText()));
				sprite.setySpeed(Integer.parseInt(mainGameView
						.getLeftPanelView().getySpeedTextField().getText()));
				sprite.setName(name);
				sprite.setImage(imgName);
				sprite.setxPos(spriteX);
				sprite.setyPos(spriteY);
				spriteList.set(i, sprite);
				gameBean.setSprites(spriteList);
				addSpriteToPane(sprite);
				break;
			}
		}

	}

	/*
	 * This method attaches the list of the events to a particular sprite.
	 */

	public void associateEvent(String name, char key, ActionTasks actionTask,
			String event) {
		for (int i = 0; i < gameBean.getSprites().size(); i++) {
			if (gameBean.getSprites().get(i).getName().equals(name)) {
				Event ev1 = new Event();
				ev1.setActionTasks(actionTask);
				ev1.setKey(key);
				ev1.setName(event);
				gameBean.getSprites().get(i).getEvents().add(ev1);
			}
		}
	}

	/*
	 * This method saves the Game in JSON file format inside the "saved" folder
	 */

	public boolean saveGame(String gameName) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "files", "createdBy",
				"lastUpdatedBy" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jObj = new JSONObject();
		jObj.put("GameAttributes", JSONSerializer.toJSON(gameBean, jsonConfig));
		try {
			String fileName = "saved/" + gameName + ".json";
			System.out.println(fileName);
			FileWriter file = new FileWriter(fileName);
			file.write(jObj.toString());
			file.flush();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * This method as the name suggests loads the game from the history of saved
	 * games in the folder "saved"
	 */
	public boolean loadGame(String fileName) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, IOException {
		JSONObject gameJSON = new JSONObject();
		gameBean = mainGameView.getGameBean();
		gameBean.setSprites(new ArrayList<Sprite>());
		String JsonString = null;
		try {
			FileReader fileReader = new FileReader("saved/" + fileName);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			JsonString = bufferReader.readLine();
			bufferReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		gameJSON = (JSONObject) JSONSerializer.toJSON(JsonString);
		gameJSON = (JSONObject) JSONSerializer.toJSON(gameJSON.get(
				"GameAttributes").toString());
		gameBean.setBackground(gameJSON.getString("background"));
		gameBean.setxSize(Double.parseDouble(gameJSON.getString("xSize")));
		gameBean.setySize(Double.parseDouble(gameJSON.getString("ySize")));
		JSONArray sps = (JSONArray) JSONSerializer.toJSON(gameJSON.get(
				"sprites").toString());
		Iterator spritesIterator = sps.iterator();
		while (spritesIterator.hasNext()) {
			JSONObject jsonSpriteArray = (JSONObject) spritesIterator.next();
			Sprite spts = new Sprite();
			spts.setEvents(new ArrayList<Event>());
			spts.setImage(jsonSpriteArray.get("image").toString());
			spts.setName(jsonSpriteArray.get("name").toString());
			spts.setxPos(Double.parseDouble(jsonSpriteArray.get("xPos")
					.toString()));
			spts.setyPos(Double.parseDouble(jsonSpriteArray.get("yPos")
					.toString()));
			spts.setxSpeed(Double.parseDouble(jsonSpriteArray.get("xSpeed")
					.toString()));
			spts.setySpeed(Double.parseDouble(jsonSpriteArray.get("ySpeed")
					.toString()));
			JSONArray evs = (JSONArray) JSONSerializer.toJSON(jsonSpriteArray
					.get("events").toString());
			Iterator eventsIterator = evs.iterator();
			while (eventsIterator.hasNext()) {
				JSONObject jsonEventsArray = (JSONObject) eventsIterator.next();
				Event evts = new Event();
				evts.setName(jsonEventsArray.get("name").toString());
				evts.setKey(jsonEventsArray.get("key").toString().charAt(0));
				String eventActionTask = jsonEventsArray.get("actionTasks")
						.toString();
				gameJSON = (JSONObject) JSONSerializer.toJSON(eventActionTask);
				ActionTasks actionTask = (ActionTasks) Class.forName(
						gameJSON.getString("className")).newInstance();
				evts.setActionTasks(actionTask);
				spts.getEvents().add(evts);
			}
			gameBean.getSprites().add(spts);
			ArrayList<Sprite> sprts = mainGameView.getGameController()
					.getGameBean().getSprites();
			for (int i = 0; i < sprts.size(); i++) {
				for (int j = 0; j < sprts.get(i).getEvents().size(); j++) {

				}
				mainGameView.getGameController().addSpriteToView(
						sprts.get(i).getName(), sprts.get(i).getImage(),
						(int) sprts.get(i).getxPos(),
						(int) sprts.get(i).getyPos());
				/*
				 * try {
				 * //mainGameView.rightPanelView.changeBackground(gameBean.
				 * getBackground()); } catch (IOException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
			}
		}
		return true;
	}

	/*
	 * This method basically listens to the keypress and calls the action
	 * related method.
	 */
	public void keyListener(char keyPress) {
		for (int i = 0; i < gameBean.getSprites().size(); i++) {
			for (int j = 0; j < gameBean.getSprites().get(i).getEvents().size(); j++) {
				if (gameBean.getSprites().get(i).getEvents().get(j).getKey() == keyPress) {

					ActionTasks actionTask = gameBean.getSprites().get(i)
							.getEvents().get(j).getActionTasks();
					actionTask.perform(gameBean.getSprites().get(i));
					mainGameView.repaint();

				}
			}
		}
	}

	/*
	 * This method adds the background image to the panel.
	 */
	public void addBackground(String bgName) throws IOException {
		gameBean.setBackground(bgName);
		mainGameView.repaint();
	}

	@Override
	public void update() {
		for (Sprite sprite : mainGameView.getGameBean().getSprites()) {
			if (sprite.getName().contains("ball")) {
				update(sprite);

			} else if (sprite.getName().equalsIgnoreCase("Paddle")) {
				updatePaddle(sprite);
			}

		}
		checkCollision();
		checkGameWin();
	}

	private void checkGameWin() {
		// TODO Auto-generated method stub
		
	}

	public boolean checkCollision() {
		double width1, height1;
		double width2, height2;
		double x1Min, x1Max, x2Min, x2Max, y1Min, y1Max, y2Min, y2Max;
		double gamePanelWidth, gamePanelHeight;
		boolean yCollision = false, xCollision = false;
		gamePanelHeight = mainGameView.getRightPanelView().getRightPanel()
				.getHeight();
		gamePanelWidth = mainGameView.getRightPanelView().getRightPanel()
				.getWidth();
		for (Sprite sprite1 : mainGameView.getGameBean().getSprites()) {
			width1 = sprite1.getWidth();
			height1 = sprite1.getHeight();
			x1Min = sprite1.getxPos() ;
			x1Max = sprite1.getxPos() + width1;
			y1Min = sprite1.getyPos() ;
			y1Max = sprite1.getyPos() + height1;

			for (Sprite sprite2 : mainGameView.getGameBean().getSprites()) {

				if (!sprite1.getName().equals(sprite2.getName())) {
					width2 = sprite2.getWidth();
					height2 = sprite2.getHeight();

					x2Min = sprite2.getxPos() ;
					x2Max = sprite2.getxPos() + width2;
					y2Min = sprite2.getyPos() ;
					y2Max = sprite2.getyPos() + height2;

					if (sprite1.getyPos() < sprite2.getyPos()) {
						// 1 above 2
						if ((y2Max - y1Min) <= (height1 + height2))

							yCollision = true;
						// 1 below 2
					} else if (sprite1.getyPos() > sprite2.getyPos()) {
						if ((y1Max - y2Min) <= (height1 + height2))
							yCollision = true;
					}
					if (sprite1.getxPos() < sprite2.getxPos()) {
						// 1 left of 2
						if ((x2Max - x1Min) <= (width1 + width2))
							xCollision = true;
						// 1 right of 2
					} else if (sprite1.getxPos() > sprite2.getxPos()) {
						if ((x1Max - x2Min) <= (width1 + width2))
							xCollision = true;
					}

					if (yCollision && xCollision) {
						xCollision = false;
						yCollision = false;
						if ((sprite1.getImage().equalsIgnoreCase("ball.png") || sprite1
								.getImage().equalsIgnoreCase("paddle.jpg"))
								&& (sprite2.getImage().equalsIgnoreCase("ball.png") || sprite2
										.getImage().equalsIgnoreCase("paddle.jpg"))) {
							if (sprite1.getImage().equalsIgnoreCase("ball.png"))
								sprite1.setySpeed(-(sprite1.getySpeed()));
							/*
							 * if(sprite2.getName().equalsIgnoreCase("ball"))
							 * sprite2.setySpeed(-(sprite1.getySpeed()));
							 */
							// if colllision is due to ball check whether its paddle or brick
							
						}
					//	checkWinGame(sprite1, sprite2);
						return true;
					}
				}
			}
		}
		return false;
	}

	/*
	 * This method updates the object and checks for the collision.
	 */

	private void checkWinGame(Sprite sprite1, Sprite sprite2) {
		// checking winning condition of the game
		if((sprite1.getImage().equalsIgnoreCase("ball.png") && sprite2.getImage().equalsIgnoreCase("sprite_bricks.png"))||((sprite2.getImage().equalsIgnoreCase("ball.png") && sprite1.getImage().equalsIgnoreCase("sprite_bricks.png")))){
			logger.info("Game Won");
		//	endGame("won");
		}
		
	}

	public void update(Sprite spriteObj) {

		// Getting the x and y cordinates of the sprite object and then checking
		// for collisions with the wall

		double x = spriteObj.getxPos();
		if (x < 10)
			spriteObj.setxSpeed(-(spriteObj.getxSpeed()));
		else if (x > Dimensions.rightPanelWidth - 50)
			spriteObj.setxSpeed(-(spriteObj.getxSpeed()));
		x = x + spriteObj.getxSpeed();

		double y = spriteObj.getyPos();
		if (y < 20)
			spriteObj.setySpeed(-(spriteObj.getySpeed()));
		else if (y > Dimensions.rightPanelHeight - 30){
			spriteObj.setySpeed(-(spriteObj.getySpeed()));
			logger.info("Game Lost");
			endGame("lost");
		}

		y = y + spriteObj.getySpeed();
		spriteObj.setxPos(x);
		spriteObj.setyPos(y);

	}

	private void endGame(String string) {
		logger.info("in End Game");
		String label = "";
		if(string.equals("won")){
			label = "You Won :) ";
		}	else{
			label = "You Lost :( ";
		}
		
		mainGameView.getLeftPanelView().getTimerTask().stop();
		JLabel jLabel =mainGameView.getLeftPanelView().getResultLabel();
		jLabel.setText(label);
		jLabel.setForeground(Color.RED);
		jLabel.setLocation(200, 200);
		jLabel.setVisible(true);
		mainGameView.getLeftPanelView().setResultLabel(jLabel);
		
	}

	public void updatePaddle(Sprite spriteObj) {
		/*
		 * double x = spriteObj.getxPos(); x = x + spriteObj.getxSpeed();
		 * spriteObj.setxPos(x);
		 */
	}

	// This method repaints the update
	@Override
	public void draw() {
		this.mainGameView.repaint();
	}

}
