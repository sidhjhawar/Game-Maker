/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * This is the Observable interface
 */

package com.gamemaker.controllers;

import java.io.IOException;

import com.gamemaker.beans.Observer;

public interface Observable {

	public void register(Observer ob);

	public void unRegister(Observer ob);

	public void notifyObservers() throws IOException;
}
