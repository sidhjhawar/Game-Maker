/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * This class is the left part of the window or the game builder form. The class basically
 * extends the JPanel.
 */

package com.gamemaker.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.gamemaker.actions.ActionTasks;
import com.gamemaker.actions.MoveLeft;
import com.gamemaker.beans.Sprite;
import com.gamemaker.constants.Dimensions;
import com.gamemaker.controllers.GameController;
import com.gamemaker.controllers.TimerTask;

public class LeftPanelView extends JPanel {
	private JPanel contentPane;
	private JTextField spriteTextField;
	private JTextField xSpeedTextField;
	private TimerTask timerTask;
	private JPanel jPanel;
	static Logger logger = Logger.getLogger(LeftPanelView.class);

	public JTextField getxSpeedTextField() {
		return xSpeedTextField;
	}

	public void setxSpeedTextField(JTextField xSpeedTextField) {
		this.xSpeedTextField = xSpeedTextField;
	}

	public JTextField getySpeedTextField() {
		return ySpeedTextField;
	}

	public void setySpeedTextField(JTextField ySpeedTextField) {
		this.ySpeedTextField = ySpeedTextField;
	}

	public TimerTask getTimerTask() {
		return timerTask;
	}

	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}

	public JLabel getResultLabel() {
		return resultLabel;
	}

	public void setResultLabel(JLabel resultLabel) {
		this.resultLabel = resultLabel;
	}

	public JPanel getjPanel() {
		return jPanel;
	}

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	private JTextField gameName;
	private JLabel gameNameLabel;
	private JTextField yPosTextField;
	private JTextField xPosTextField;
	private JTextField ySpeedTextField;
	private JComboBox eventList;
	private JTextField keyTextField;
	private JComboBox actionList;
	private JComboBox savedList;

	private JTextField xGameSizeTextField;
	private JTextField yGameSizeTextField;
	private JTextField heightTextField;
	private JTextField widthTextField;
	private JLabel lblSprite;
	private JLabel lblImage;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JLabel lblXPosition;
	private JLabel lblYPosition;
	private JLabel lblXSpeed;
	private JLabel lblYSpeed;
	private JLabel lblEventLabel;
	private JLabel lblKey;
	private JLabel lblAction;
	private JButton btnAssociate;
	private JLabel lblBackground;
	private JLabel lblGameSizer;
	private JLabel lblX;
	private JLabel lblY;
	private JButton btnAdd;
	private JButton btnEdit;

	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnStartGame;
	private JComboBox spriteImageList;
	private JComboBox bgImageList;
	private JPanel widthHeightPanel;
	private MainGameView mainGameView;
	
	private JLabel resultLabel;
	
	public LeftPanelView(MainGameView mainGameview) {
		gameName = new JTextField();
		gameNameLabel = new JLabel("Name");
		savedList = new JComboBox();
		this.mainGameView = mainGameview;
		widthHeightPanel = new JPanel();
		timerTask = new TimerTask(mainGameview);

		lblSprite = new JLabel("Sprite");

		spriteTextField = new JTextField();

		lblImage = new JLabel("Image");

		lblWidth = new JLabel("Width");

		widthTextField = new JTextField();

		lblHeight = new JLabel("Height");

		heightTextField = new JTextField();

		lblXPosition = new JLabel("X Position");

		xPosTextField = new JTextField();

		lblYPosition = new JLabel("Y Position");

		yPosTextField = new JTextField();

		lblXSpeed = new JLabel("X Speed");

		xSpeedTextField = new JTextField();

		lblYSpeed = new JLabel("Y Speed");

		ySpeedTextField = new JTextField();

		lblEventLabel = new JLabel("Events");

		lblKey = new JLabel("Key");

		keyTextField = new JTextField();

		lblAction = new JLabel("Action");

		actionList = new JComboBox();

		btnAssociate = new JButton("Associate");
		btnAssociate.addActionListener(new AssociateAdapter());

		lblBackground = new JLabel("Background");

		lblGameSizer = new JLabel("Game Size");
		
		resultLabel = new JLabel();
		resultLabel.setVisible(false);

		lblX = new JLabel("X");

		xGameSizeTextField = new JTextField();

		lblY = new JLabel("Y");

		yGameSizeTextField = new JTextField();

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new AddAdapter());

		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new EditAdapter());

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stu

				mainGameView.getGameController().deleteSprite(
						spriteTextField.getText());
			}
		});

		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveAdapter());

		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new LoadAdapter());

		btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new StartGameAdapter());

	}

	// This method does the placing of the JComponents inside the Grid Layout

	public JPanel setLeftPanel() {
		setBounds(0, 0, (int) (Dimensions.leftPanelWidth),
				(int) Dimensions.leftPanelHeight);
		setLayout(new GridBagLayout());
		setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gameNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gameNameLabel.setBounds((int) Dimensions.leftMargin, 20,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(gameNameLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;

		add(gameName, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;

		String[] fileList = getFileList("./saved/");
		savedList = new JComboBox(fileList);
		add(savedList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;

		lblSprite.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSprite.setBounds((int) Dimensions.leftMargin, 20,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblSprite, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;

		spriteTextField.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 20,
				(int) Dimensions.largeTextField, (int) Dimensions.lineHeight);
		add(spriteTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;

		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblImage.setBounds((int) Dimensions.leftMargin, 50,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblImage, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;

		String[] imgList = getFileList("./img/sprite");
		spriteImageList = new JComboBox(imgList);
		spriteImageList.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 50,
				(int) Dimensions.largeTextField, (int) Dimensions.lineHeight);
		add(spriteImageList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;

		lblXPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXPosition.setBounds((int) Dimensions.leftMargin, 110,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblXPosition, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;

		xPosTextField.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 110,
				(int) Dimensions.smallTextField, (int) Dimensions.lineHeight);
		add(xPosTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;

		lblYPosition.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYPosition.setBounds((int) Dimensions.leftMargin + 200, 110,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblYPosition, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;

		yPosTextField.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 210), 110,
				(int) Dimensions.smallTextField, (int) Dimensions.lineHeight);
		add(yPosTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;

		lblXSpeed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXSpeed.setBounds((int) Dimensions.leftMargin, 140,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblXSpeed, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		xSpeedTextField.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 140,
				(int) Dimensions.smallTextField, (int) Dimensions.lineHeight);
		add(xSpeedTextField, c);
		// contentPane.add(xSpeedTextField,c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;

		lblYSpeed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYSpeed.setBounds((int) (Dimensions.leftMargin + 200), 140,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblYSpeed, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;

		ySpeedTextField.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 210), 140,
				(int) Dimensions.smallTextField, (int) Dimensions.lineHeight);
		add(ySpeedTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		lblEventLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEventLabel.setBounds((int) Dimensions.leftMargin, 190,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblEventLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;

		String[] eventNameList = { "", "KeyPressed", "Collision" };
		eventList = new JComboBox(eventNameList);
		eventList.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 50,
				(int) Dimensions.largeTextField, (int) Dimensions.lineHeight);
		add(eventList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;

		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKey.setBounds((int) Dimensions.leftMargin, 220,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblKey, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;

		keyTextField.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 220,
				(int) Dimensions.largeTextField, (int) Dimensions.lineHeight);
		add(keyTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		lblAction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAction.setBounds((int) Dimensions.leftMargin, 250,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblAction, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		String[] actionNameList = { "", "Bounce", "Explode", "Music",
				"MoveLeft", "MoveRight", "MoveUp", "Boost" };
		actionList = new JComboBox(actionNameList);
		actionList.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 50,
				(int) Dimensions.largeTextField, (int) Dimensions.lineHeight);
		add(actionList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 9;

		btnAssociate.setBounds((int) Dimensions.leftMargin + 50, 280,
				(int) Dimensions.largeButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnAssociate, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 10;
		lblBackground.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBackground.setBounds((int) Dimensions.leftMargin, 330,
				(int) Dimensions.labelWidth, (int) Dimensions.lineHeight);
		add(lblBackground, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;

		String[] bgList = getFileList("./img/bg");
		bgImageList = new JComboBox(bgList);
		bgImageList.setBounds((int) (Dimensions.leftMargin
				+ Dimensions.labelWidth + 10), 330,
				(int) Dimensions.largeTextField, (int) Dimensions.lineHeight);
		add(bgImageList, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 11;
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds((int) Dimensions.leftMargin, 390,
				(int) Dimensions.smallButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnAdd, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 11;

		btnEdit.setBounds((int) Dimensions.leftMargin, 390,
				(int) Dimensions.smallButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnEdit, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 11;

		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStartGame.setBounds((int) Dimensions.leftMargin + 50, 450,
				(int) Dimensions.largeButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnStartGame, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 12;

		btnDelete.setBounds((int) Dimensions.leftMargin + 100, 390,
				(int) Dimensions.smallButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnDelete, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 12;

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSave.setBounds((int) Dimensions.leftMargin + 200, 390,
				(int) Dimensions.smallButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnSave, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 12;

		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoad.setBounds((int) Dimensions.leftMargin + 300, 390,
				(int) Dimensions.smallButtonWidth,
				(int) Dimensions.buttonHeight);
		add(btnLoad, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 13;
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		resultLabel.setLocation(200,200);
		resultLabel.setVisible(false);
		add(resultLabel,c);
		
		return this;

	}

	public class AddAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = spriteTextField.getText();
			String imgName = (String) spriteImageList.getSelectedItem();
			String bgName = (String) bgImageList.getSelectedItem();
			int spriteX = Integer.parseInt(xPosTextField.getText());
			int spriteY = Integer.parseInt(yPosTextField.getText());
			try {
				mainGameView.getGameController().addSprite(name, imgName,
						spriteX, spriteY);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				mainGameView.getGameBean().setBackground(bgName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				mainGameView.rightPanelView.changeBackground(bgName);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public class EditAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String name = spriteTextField.getText();
			String imgName = (String) spriteImageList.getSelectedItem();
			String bgName = (String) bgImageList.getSelectedItem();
			int spriteX = Integer.parseInt(xPosTextField.getText());
			int spriteY = Integer.parseInt(yPosTextField.getText());
			try {
				mainGameView.getGameBean().setBackground(bgName);
				mainGameView.getGameController().editSprite(name, imgName,
						spriteX, spriteY);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info(name);

		}

	}

	public class StartGameAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			removeFocus();
			timerTask.register(mainGameView.getGameController());
			timerTask.run();

		}

	}

	public void removeFocus() {
		btnAdd.setFocusable(false);
		btnAssociate.setFocusable(false);
		btnDelete.setFocusable(false);
		btnLoad.setFocusable(false);
		btnSave.setFocusable(false);
		btnStartGame.setFocusable(false);
		spriteImageList.setFocusable(false);
		spriteTextField.setFocusable(false);
		widthTextField.setFocusable(false);
		heightTextField.setFocusable(false);
		xPosTextField.setFocusable(false);
		yPosTextField.setFocusable(false);
		xSpeedTextField.setFocusable(false);
		ySpeedTextField.setFocusable(false);
		bgImageList.setFocusable(false);
		actionList.setFocusable(false);
		eventList.setFocusable(false);
		keyTextField.setFocusable(false);
	}

	public void getFocus() {
		btnAdd.setFocusable(true);
		btnAssociate.setFocusable(true);
		btnDelete.setFocusable(true);
		btnLoad.setFocusable(true);
		btnSave.setFocusable(true);
		btnStartGame.setFocusable(true);
		spriteImageList.setFocusable(true);
		spriteTextField.setFocusable(true);
		widthTextField.setFocusable(true);
		heightTextField.setFocusable(true);
		xPosTextField.setFocusable(true);
		yPosTextField.setFocusable(true);
		xSpeedTextField.setFocusable(true);
		ySpeedTextField.setFocusable(true);
		bgImageList.setFocusable(true);
		actionList.setFocusable(true);
		eventList.setFocusable(true);
		keyTextField.setFocusable(true);
	}

	private String[] getFileList(String loc) {
		File folder = new File(loc);
		File[] listOfFiles = folder.listFiles();
		String[] list = new String[listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				list[i] = listOfFiles[i].getName();
			} else if (listOfFiles[i].isDirectory()) {
			}
		}
		return list;
	}

	public class AssociateAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			char key = keyTextField.getText().charAt(0);
			String action = (String) actionList.getSelectedItem();
			action = "com.gamemaker.actions." + action;

			try {
				ActionTasks actionTask = (ActionTasks) Class.forName(action)
						.newInstance();
				logger.info("Class Name: " + actionTask.getClass().toString());
				String event = (String) eventList.getSelectedItem();
				String name = spriteTextField.getText();

				mainGameView.getGameController().associateEvent(name, key,
						actionTask, event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public class ViewAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<Sprite> sprts = mainGameView.getGameController()
					.getGameBean().getSprites();
			for (int i = 0; i < sprts.size(); i++) {
				for (int j = 0; j < sprts.get(i).getEvents().size(); j++) {
				}
			}
		}

	}

	public class SaveAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String game = gameName.getText();
			mainGameView.getGameController().saveGame(game);
		}

	}

	public class LoadAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				String loadFile = (String) savedList.getSelectedItem();
				if (mainGameView.getGameController().loadGame(loadFile)) {
					mainGameView.rightPanelView.changeBackground(mainGameView
							.getGameBean().getBackground());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void resizeContents() {
		this.setBounds(0, 0, (int) (Dimensions.leftPanelWidth),
				(int) Dimensions.leftPanelHeight);
	}

}
