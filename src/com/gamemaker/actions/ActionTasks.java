/*
 * author : Eldhose Rajan
 * 			Sajan Khandelwal
 * 			Sidharth Jhawar
 * 			Ajay
 * 
 * 	This interface has the generic methods to perform various actions .The class implementing 
 * 	this interface defines its own behavior. This interface is especially useful when there are
 * 	new type of actions. 
 */

package com.gamemaker.actions;

import com.gamemaker.beans.Sprite;

public interface ActionTasks {

	void perform(Sprite spriteObj);

	String getClassName();

	void setClassName(String className);
}
