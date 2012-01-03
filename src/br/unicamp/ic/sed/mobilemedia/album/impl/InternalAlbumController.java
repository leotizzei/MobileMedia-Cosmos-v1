package br.unicamp.ic.sed.mobilemedia.album.impl;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.album.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.album.spec.req.IMobilePhone;
import br.unicamp.ic.sed.mobilemedia.album.spec.req.IMobileResources;

class InternalAlbumController implements CommandListener{

	public void commandAction(Command c, Displayable d) { //System.out.println("InternalAlbumController.commandAction()");
		IManager manager = ComponentFactory.createInstance();
		IMobilePhone iMobilePhone = (IMobilePhone)manager.getRequiredInterface("IMobilePhone" );
		iMobilePhone.postCommand(c);
	}
	
	/**
	 * Set the current screen for display
	 */
    public void setCurrentScreen(Displayable d) { //System.out.println("InternalAlbumController.setCurrentScreen()");
    	IManager manager = ComponentFactory.createInstance();
    	//System.out.println("1: "+manager);
    	IMobileResources iMobileResources = (IMobileResources)manager.getRequiredInterface("IMobileResources");
    	//System.out.println("2: "+iMobileResources);
    	MIDlet midlet = iMobileResources.getMainMIDlet();
    	//System.out.println("3");
    	Display.getDisplay(midlet).setCurrent(d);
    	//System.out.println("4");
    } 
    
    /**
	 * Set the current screen for display
	 */
    public void setCurrentScreen(Alert a , Displayable d) { //System.out.println("InternalAlbumController.setCurrentScreen()");
    	IManager manager = ComponentFactory.createInstance();
    	IMobileResources iMobileResources = (IMobileResources)manager.getRequiredInterface("IMobileResources");
        MIDlet midlet = iMobileResources.getMainMIDlet();
    	Display.getDisplay(midlet).setCurrent(a , d);
    } 
}
