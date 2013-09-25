/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * This is the main view of the project, Here the game window is rendered and all the
 * actionlistener are attached to the buttons.
 */

package com.gamemaker.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.gamemaker.beans.GameBean;
import com.gamemaker.beans.Sprite;
import com.gamemaker.constants.Dimensions;
import com.gamemaker.controllers.GameController;

public class MainGameView extends JFrame {

	private JPanel contentPane;
	private Boolean isFirstTime;
	public static Logger logger = Logger.getLogger(MainGameView.class);

	public Boolean getIsFirstTime() {
		return isFirstTime;
	}

	public void setIsFirstTime(Boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

	private JLayeredPane layeredPane;
	LeftPanelView leftPanelView;
	RightPanelView rightPanelView;
	private GameBean gameBean;
	private GameController gameController;

	public GameBean getGameBean() {
		return gameBean;
	}

	public void setGameBean(GameBean gameBean) {
		this.gameBean = gameBean;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public MainGameView() throws IOException {
		contentPane = new JPanel();
		isFirstTime = true;

		leftPanelView = new LeftPanelView(this);
		rightPanelView = new RightPanelView(this);
		layeredPane = new JLayeredPane();

		setGameBean(new GameBean());
		gameController = new GameController(this);

		setInitialWindow();

		this.addComponentListener(new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				double a = e.getComponent().getSize().width;
				double b = e.getComponent().getSize().height;
				Dimensions.xScale = ((a - Dimensions.xWindow) / Dimensions.xWindow) + 1;
				Dimensions.yScale = ((b - Dimensions.yWindow) / Dimensions.yWindow) + 1;
				try {
					rightPanelView.resizeContents();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			public void componentHidden(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentShown(ComponentEvent e) {
			}
		});

	}

	public LeftPanelView getLeftPanelView() {
		return leftPanelView;
	}

	public void setLeftPanelView(LeftPanelView leftPanelView) {
		this.leftPanelView = leftPanelView;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		BufferedImage imageSprite = null;
		BufferedImage imageBackground = null;
		List<Sprite> spriteList;
		if (getGameBean().getSprites().size() > 0 && isFirstTime == false) {
			spriteList = gameController.getGameBean().getSprites();
			for (Sprite sprite : spriteList) {
				String spriteImagePath = "img/sprite/" + sprite.getImage();
				try {
					imageSprite = ImageIO.read(new File(spriteImagePath));
					sprite.setHeight(imageSprite.getHeight());
					sprite.setWidth(imageSprite.getWidth());
				} catch (IOException e) {
					e.printStackTrace();
				}

				g.drawImage(imageSprite, (int) (sprite.getxPos() + 500),
						(int) (sprite.getyPos() + 20), null);
			}

		} else
			isFirstTime = false;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameView board = new MainGameView();
					board.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// This method does all the intial setting up the application window.
	public void setInitialWindow() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int) Dimensions.xWindow, (int) Dimensions.yWindow);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(layeredPane);
		layeredPane.add(leftPanelView.setLeftPanel());

		JPanel jpr = rightPanelView.setRightPanel();
		layeredPane.add(jpr);

	}

	public RightPanelView getRightPanelView() {
		return rightPanelView;
	}

}
