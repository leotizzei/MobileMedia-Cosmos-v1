package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.impl;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.spec.prov.IExceptionsHandlerCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.spec.req.IMobileResourcesWEH;



public class WarningExceptionHandler implements IExceptionsHandlerCtr{

	public void handle(Exception exception) { System.out.println("WarningExceptionHandler.handle()");
		IManager manager = ComponentFactory.createInstance();
		
		//br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.spec.req.IMobileResourcesWEH
		//MIDlet midlet = (MIDlet)manager.getRequiredInterface( "IMobileResources" );
		
		//main.IMobileResources mob = (main.IMobileResources) manager.getRequiredInterface("IMobileResourcesCtr");
		IMobileResourcesWEH mob = (IMobileResourcesWEH) manager.getRequiredInterface("IMobileResources");
		
		MIDlet midlet = mob.getMainMIDlet();
		
		exception.printStackTrace();
		Alert alert = new Alert( "Error" , exception.getMessage() , null, AlertType.ERROR );
		//System.out.println("tela = " + exception.getMessage());
		alert.setTimeout( 5000 );
		
		Displayable currentDisplay = Display.getDisplay( midlet ).getCurrent();
		Display.getDisplay( midlet ).setCurrent(alert, currentDisplay );
	}
}
