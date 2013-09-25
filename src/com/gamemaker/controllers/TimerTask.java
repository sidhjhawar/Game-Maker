/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * This is the class implements Observable. This basically drives the game
 */

package com.gamemaker.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.Timer;

import com.gamemaker.beans.Observer;
import com.gamemaker.views.MainGameView;

public class TimerTask implements Observable {
	private Timer t;
	private MainGameView mainGameView;
	Map<Observer, MainGameView> observerList;

	public TimerTask(MainGameView mainGameView) {
		this.mainGameView = mainGameView;
		t = new Timer(10, new TimerTaskListener());
		observerList = new ConcurrentHashMap<Observer, MainGameView>();

	}

	public void run() {
		t.start();
	}
	
	public void stop(){
		t.stop();
	}

	class TimerTaskListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				notifyObservers();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void register(Observer observer) {
		observerList.put(observer, mainGameView);
	}

	@Override
	public void unRegister(Observer observer) {
		observerList.remove(observer);
	}

	@Override
	public void notifyObservers() throws IOException {
		Iterator it = observerList.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Observer, MainGameView> entry = (Map.Entry) it.next();
			Observer ob = entry.getKey();
			MainGameView mainGame = new MainGameView();
			ob.update();
			ob.draw();
		}
	}

}
