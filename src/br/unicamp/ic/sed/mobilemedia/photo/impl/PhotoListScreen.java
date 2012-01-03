/*
 * Created on Sep 13, 2004
 *
 */
package br.unicamp.ic.sed.mobilemedia.photo.impl;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;


/**
 * @author trevor
 *
 * This screen shows a listing of all photos for a selected photo album.
 * This is the screen that contains most of the feature menu items. 
 * From this screen, a user can choose to view photos, add or delete photos,
 * send photos to other users etc.
 * 
 */
public class PhotoListScreen extends List {
	
	//Add the core application commands always
	public static final Command viewCommand = new Command("View", Command.ITEM, 1);
	public static final Command addCommand = new Command("Add", Command.ITEM, 1);
	public static final Command deleteCommand = new Command("Delete", Command.ITEM, 1);
	public static final Command backCommand = new Command("Back", Command.BACK, 0);
    /**
     * Constructor
     */
	public PhotoListScreen() {
		super("Choose Items", Choice.IMPLICIT);// System.out.println("PhotoListScreen.PhotoListScreen()");
	}
	
	/**
	 * Constructor
	 * @param arg0
	 * @param arg1
	 */
	public PhotoListScreen(String arg0, int arg1) {
		super(arg0, arg1); //System.out.println("PhotoListScreen.PhotoListScreen()");
	}

	/**
	 * Constructor
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public PhotoListScreen(String arg0, int arg1, String[] arg2, Image[] arg3) {
		super(arg0, arg1, arg2, arg3); //System.out.println("PhotoListScreen.PhotoListScreen()");
	}

	/**
	 * Initialize the menu items for this screen
	 */
	public void initMenu() {// System.out.println("PhotoListScreen.initMenu()");
		
		//Add the core application commands always
		this.addCommand(viewCommand);
		this.addCommand(addCommand);
		this.addCommand(deleteCommand);
		this.addCommand(backCommand);

		//Add the optional feature menu items only if they are specified in 
		//the xxxBuild.properties file using the 'preprocessor.symbols' value
		
	
	}
	
}
