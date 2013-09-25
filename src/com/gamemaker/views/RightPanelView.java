/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * This class extends the JPanel and does setting up of the right panel or the game play area.
 */

package com.gamemaker.views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.gamemaker.constants.Dimensions;

public class RightPanelView extends JPanel {

	private JPanel rightPanel;
	private MainGameView mainGameView;
	private String currentBgImage;
	private static JLabel label;
	private BufferedImage bi;
	private static int isFirstTime = 0;
	private JLabel jLabel;

	public String getCurrentBgImage() {
		return currentBgImage;
	}

	public void setCurrentBgImage(String currentBgImage) {
		this.currentBgImage = currentBgImage;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public RightPanelView(MainGameView mainGameView) {
		rightPanel = new JPanel();
		this.mainGameView = mainGameView;
		jLabel = new JLabel();
		jLabel.setVisible(false);
	}

	// This method places the component and reads the inital background to the
	// panel.
	public JPanel setRightPanel() throws IOException {
		rightPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightPanel.setBounds((int) Dimensions.rightPanelX, 0,
				(int) (Dimensions.rightPanelWidth),
				(int) Dimensions.rightPanelHeight);
		rightPanel.addKeyListener(new KeyListenerAdapter());
		BufferedImage img = null;
		ImageIcon image = null;
		img = ImageIO.read(new File("img/bg/default.png"));
		currentBgImage = mainGameView.getGameBean().getBackground();
		image = new ImageIcon(img);
		label = new JLabel(image);
		label.setSize((int) (Dimensions.rightPanelWidth * Dimensions.xScale),
				(int) (Dimensions.rightPanelHeight * Dimensions.yScale));
		rightPanel.add(label);
		rightPanel.setFocusable(true);
		return rightPanel;
	}

	public JLabel getjLabel() {
		return jLabel;
	}

	public void setjLabel(JLabel jLabel) {
		this.jLabel = jLabel;
	}

	public void changeBackground(String bgImg) throws IOException {
		BufferedImage img = null;
		ImageIcon image = null;
		if (!bgImg.equals(currentBgImage)) {
			if (mainGameView.getGameBean().getBackground() != null) {
				rightPanel.remove(label);
				img = ImageIO.read(new File("img/bg/"
						+ mainGameView.getGameBean().getBackground()));
				currentBgImage = mainGameView.getGameBean().getBackground();
				image = new ImageIcon(img);
			} else {
				img = ImageIO.read(new File("img/bg/default.png"));
				currentBgImage = "default.png";
			}
			image = new ImageIcon(img);
			label = new JLabel(image);
			label.setSize(500, 700);
			rightPanel.add(label);
		}
	}

	public class KeyListenerAdapter implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			mainGameView.getGameController().keyListener(e.getKeyChar());

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	public void resizeContents() throws IOException {
		if (isFirstTime > 2) {
			rightPanel.setSize(
					(int) (Dimensions.rightPanelWidth * Dimensions.xScale),
					(int) (Dimensions.rightPanelHeight * Dimensions.yScale));
			rightPanel.add(label);
		}
		isFirstTime += 1;
	}

	public BufferedImage resize(BufferedImage image, int width, int height) {
		bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

}
