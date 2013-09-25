/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * 	This is one of the model that we have to store the events related to a particular sprite.
 */
package com.gamemaker.beans;

import com.gamemaker.actions.ActionTasks;
import com.gamemaker.views.MainGameView;

public class Event {
	private String name;
	private char key;
	private ActionTasks actionTask;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public ActionTasks getActionTasks() {
		return actionTask;
	}

	public void setActionTasks(ActionTasks actionTask) {
		this.actionTask = actionTask;
	}

	public Event() {
	}

}
