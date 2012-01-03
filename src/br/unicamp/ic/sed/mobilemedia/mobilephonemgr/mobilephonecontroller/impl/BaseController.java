/*
 * Created on Sep 28, 2004
 *
 */
package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.dt.Constants;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.req.IAlbumCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.req.IExceptionsHandlerCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.req.IFilesystemCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.req.IPhotoCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.UnavailablePhotoAlbumException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;


/**
 * @author tyoung
 *
 * This is the base controller class used in the MVC architecture.
 * It controls the flow of screens for the MobileMedia application.
 * Commands handled by this class should only be for the core application
 * that runs on any MIDP platform. Each device or class of devices that supports
 * optional features will extend this class to handle feature specific commands.
 * 
 */
class BaseController implements CommandListener, ControllerInterface {
	
	//Define a successor to implement the Chain of Responsibility design pattern
	private BaseController nextController;
	
	;

	//Keep track of the navigation so we can go 'back' easily
	private String currentScreenName;
	
	
	//Keep track of the current photo album being viewed
	//Ie. name of the currently active J2ME record store
	private String currentStoreName = "My Photo Album";

	
	
	
	
	
	
/*	protected IManager getManager() {
		if(manager == null)
			System.out.println("manager is null");
		return manager;this.getManager();
	}

	protected void setManager(IManager manager2) {
		if(manager2 == null)
			System.out.println("manager2 is null");
		this.manager = (Manager) manager2;
	}*/

	/**
	 * Initialize the controller
	 */
	public void init() { //System.out.println("BaseController.init()");
	    
		IManager manager = ComponentFactory.createInstance();
				
		//Get all MobileMedia defined albums from the record store
	    IFilesystemCtr fileSystem = (IFilesystemCtr)manager.getRequiredInterface("IFilesystemCtr");
	    if(fileSystem == null)
	    	System.err.println("filesystem is null inside basecontroller");
	    
	    String[] albumNames = fileSystem.getAlbumNames();
		
	    //System.out.println("realizou operacoes com filesystem");
	    
		//initialize all the screens
		IAlbumCtr album = (IAlbumCtr)manager.getRequiredInterface("IAlbumCtr");
		album.initAlbumListScreen(albumNames);
		
		//Set the current screen to the photo album listing
		currentScreenName = Constants.ALBUMLIST_SCREEN;
		
	}

    public boolean handleCommand(Command c) { System.out.println("BaseController.handleCommand()");

    	
    	IManager manager = ComponentFactory.createInstance();
    	
    	
		IFilesystemCtr fileSystem = (IFilesystemCtr)manager.getRequiredInterface("IFilesystemCtr");
		IAlbumCtr album = (IAlbumCtr)manager.getRequiredInterface("IAlbumCtr");
		if(album == null)
			System.err.println("album is null before the if/else");
		IPhotoCtr photo = (IPhotoCtr)manager.getRequiredInterface("IPhotoCtr");
		IExceptionsHandlerCtr excepHandler = (IExceptionsHandlerCtr)manager.getRequiredInterface("IExceptionsHandlerCtr");
    	
    	//Can this controller handle the desired action?
		//If yes, handleCommand will return true, and we're done
		//If no, handleCommand will return false, and postCommand
		//will pass the request to the next controller in the chain if one exists.
		
		String label = c.getLabel();
      	//System.out.println( this.getClass().getName() + "::handleCommand: " + label);
      	

		/** Case: Exit Application **/
		if (label.equals("Exit")) {
			//TODO: Funcionalidade de destruir aplicação
			//midlet.destroyApp(true);
			return true;
		/** Case: Reset PhotoAlbum data **/
		} else if (label.equals("Reset")) {
		    resetImageData();
			currentScreenName = Constants.ALBUMLIST_SCREEN;
			return true;
		}else if (label.equals("New Photo Album")) {
			//System.out.println("Create new Photo Album here");			
			currentScreenName = Constants.NEWALBUM_SCREEN;
			album.initNewAlbumScreen();
			return true;
			/** Case: Delete Album Photo**/
		}else if (label.equals("Delete Album")) {
			//System.out.println("Delete Photo Album here");
			currentScreenName = Constants.CONFIRMDELETEALBUM_SCREEN;
			currentStoreName = album.getSelectedAlbum( );
			album.initDeleteAlbumScreen( currentStoreName );
			return true;	
		/** Case: Yes delete Photo Album  **/
		}else if (label.equals("Yes - Delete")) {
			try {
				fileSystem.deletePhotoAlbum(currentStoreName);
			} catch (PersistenceMechanismException e) {
				excepHandler.handle( e );
				//Alert alert = new Alert( "Error", "The mobile database can not delete this photo album", null, AlertType.ERROR);
				//TODO: Apagar codigo comentado
				//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			goToPreviousScreen();
			return true;	
		/** Case: Save new Photo Album  **/	
			/** Case: No delete Photo Album  **/
		}else if (label.equals("No - Delete")) {
			goToPreviousScreen();
			return true;	
		/** Case: Save new Photo Album  **/
		} else if (label.equals("Save")) {
			try {
				//TODO: Apagar codigo comentado
				//model.createNewPhotoAlbum(((NewAlbumScreen)getCurrentScreenName()).getAlbumName());
				fileSystem.createNewPhotoAlbum( album.getNewAlbumName() );
			} catch (PersistenceMechanismException e) {
				excepHandler.handle( e );
				/*Alert alert = null;
				if (e.getCause() instanceof  RecordStoreFullException)
					alert = new Alert( "Error", "The mobile database is full", null, AlertType.ERROR);
				else
					alert = new Alert( "Error", "The mobile database can not add a new photo album", null, AlertType.ERROR);
				*///TODO: Apagar codigo comentado
				//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
		    } catch (InvalidPhotoAlbumNameException e) {
		    	excepHandler.handle( e );
		    	//Alert alert = new Alert( "Error", "You have provided an invalid Photo Album name", null, AlertType.ERROR);
		    	//TODO: Apagar codigo comentado
		    	//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
			}
			goToPreviousScreen();
			return true;
			/** Case: Select PhotoAlbum to view **/
		} else if (label.equals("Select")) {
			
			//System.out.println("\n\n========" + this );
			
			if(currentStoreName == null)
				System.err.println("currentStoreName is null");
			if(album == null)
				System.err.println("album is null");
			
			
			//Get the name of the PhotoAlbum selected, and load image list from RecordStore
			currentStoreName = album.getSelectedAlbum();
			showImageList(currentStoreName);
			currentScreenName = Constants.IMAGELIST_SCREEN;
			return true;
			/** Case: View Image **/
		} else if (label.equals("View")) {
			showImage();
			currentScreenName = Constants.IMAGE_SCREEN;
			return true;
		
		/** Case: Add photo  **/
		} else if (label.equals("Add")) {
			currentScreenName = Constants.ADDPHOTOTOALBUM_SCREEN;
			photo.initAddPhotoToAlbum( currentStoreName );
			return true;
			/** Case: Save Add photo  **/
		} else if (label.equals("Save Add Photo")) {
			try {
				fileSystem.addNewPhotoToAlbum(photo.getAddedPhotoName(), photo.getAddedPhotoPath() , currentStoreName );
				
				//TODO: Apagar codigo comentado
				//model.addNewPhotoToAlbum(((AddPhotoToAlbum)getCurrentScreenName()).getPhotoName(), ((AddPhotoToAlbum)getCurrentScreenName()).getPath(), currentStoreName);
			} catch (InvalidImageDataException e) {
				excepHandler.handle( e );
				/*Alert alert = null;
				if (e instanceof ImagePathNotValidException)
					alert = new Alert( "Error", "The path is not valid", null, AlertType.ERROR);
				else
					alert = new Alert( "Error", "The image file format is not valid", null, AlertType.ERROR);
				*///TODO: Apagar codigo comentado
				//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
				//alert.setTimeout(5000);
			} catch (PersistenceMechanismException e) {
				excepHandler.handle( e );
				/*Alert alert = null;
				if (e.getCause() instanceof  RecordStoreFullException)
					alert = new Alert( "Error", "The mobile database is full", null, AlertType.ERROR);
				else
					alert = new Alert( "Error", "The mobile database can not add a new photo", null, AlertType.ERROR);
				*///TODO: Apagar codigo comentado
				//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			goToPreviousScreen();
			return true;

		/** Case: Delete selected Photo from recordstore **/
		} else if (label.equals("Delete")) {
	      	String selectedImageName = getSelectedImageName();
	      	try {
	      		//TODO: Apagar codigo comentado
				//model.deleteImage(currentStoreName, selectedImageName);
	      		fileSystem.deleteImage(currentStoreName, selectedImageName );
	      	} catch (PersistenceMechanismException e) {
	      		excepHandler.handle( e );
	      		//Alert alert = new Alert( "Error", "The mobile database can not delete this photo", null, AlertType.ERROR);
				//TODO: Apagar codigo comentado
				//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
		        return true;
			} catch (ImageNotFoundException e) {
				excepHandler.handle( e );
				//Alert alert = new Alert( "Error", "The selected photo was not found in the mobile device", null, AlertType.ERROR);
				//TODO: Apagar codigo comentado
				//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());		        return true;
			}
	      	showImageList(currentStoreName);
		    currentScreenName = Constants.IMAGELIST_SCREEN;
			return true;
	      	
		/** Case: Go to the Previous or Fallback screen **/
		} else if (label.equals("Back")) {
		    
		    goToPreviousScreen();
			return true;
			
		/** Case: Cancel the current screen and go back one**/
		} else if (label.equals("Cancel")) {
			
			goToPreviousScreen();
			return true;
			
		}
		
		//If we couldn't handle the current command, return false
        return false;
    }
    
    public void postCommand(Command c) { //System.out.println("BaseController.postCommand()");

        //System.out.println("BaseController::postCommand - Current controller is: " + this.getClass().getName());
        
        //If the current controller cannot handle the command, pass it to the next
        //controller in the chain.
        if (handleCommand(c) == false) {
            BaseController next = getNextController();
            if (next != null) {
                System.err.println("Passing to next controller in chain: " + next.getClass().getName());
                next.postCommand(c);
            } else {
                System.err.println("BaseController::postCommand - Reached top of chain. No more handlers for command: " + c.getLabel());
            }
        }

    }

	/**
	 * Handle events. For now, this just passes control off to a 'wrapper'
	 * so we can ensure , in order to use it in the aspect advice
	 */
	public void commandAction(Command c, Displayable d) { //System.out.println("BaseController.commandAction()");
	    postCommand(c);
	}


    /**
	 * This option is mainly for testing purposes. If the record store
	 * on the device or emulator gets into an unstable state, or has too 
	 * many images, you can reset it, which clears the record stores and
	 * re-creates them with the default images bundled with the application 
	 */
	private void resetImageData() { System.out.println("BaseController.resetImageData()");
		
		//Get all required interfaces for this method
		IManager manager = ComponentFactory.createInstance();
		IFilesystemCtr fileSystem = (IFilesystemCtr)manager.getRequiredInterface("IFilesystemCtr");
		IAlbumCtr album = (IAlbumCtr)manager.getRequiredInterface("IAlbumCtr");
		IExceptionsHandlerCtr excepHandler = (IExceptionsHandlerCtr)manager.getRequiredInterface("IExceptionsHandlerCtr");
    
		
		try {
			fileSystem.resetImageData();
		} catch (PersistenceMechanismException e) {
			excepHandler.handle( e );
			/*Alert alert = null;
			if (e.getCause() instanceof  RecordStoreFullException)
				alert = new Alert( "Error", "The mobile database is full", null, AlertType.ERROR);
			else
				alert = new Alert( "Error", "It is not possible to reset the database", null, AlertType.ERROR);
			*///TODO: Apagar codigo comentado
			//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			return;
		}
        
        //Clear the names from the album list
		//TODO: Apagar codigo comentado
		/*
		for (int i = 0; i < albumListScreen.size(); i++) {
        	albumListScreen.delete(i);
        }
		
		
        
        //Get the default ones from the album
        String[] albumNames = model.getAlbumNames();
        for (int i = 0; i < albumNames.length; i++) {
        	if (albumNames[i] != null) {
        		//Add album name to menu list
        		albumListScreen.append(albumNames[i], null);
        		
        	}
        }
        
        setCurrentScreen(albumListScreen);*/
        String[] albumNames = fileSystem.getAlbumNames();
        album.initAlbumListScreen(albumNames);
    }

	//TODO: Apagar codigo comentado
    /**
	 * Set the current screen for display
	 * 
    public void setCurrentScreen(Displayable d) {
        Display.getDisplay(midlet).setCurrent(d);
    } 

    /**
	 * Set the current screen for display, after alert
	 *
    public void setCurrentScreen(Alert a, Displayable d) {
//        display.setCurrent(a, d);
        Display.getDisplay(midlet).setCurrent(a, d);
    } 

    /**
	 * Get the current screen name that is displayed
	 *
    public Displayable getCurrentScreenName() {
//        return display.getCurrent();
        return Display.getDisplay(midlet).getCurrent();
    } 
    
    /**
     * Go to the previous screen
     * TODO: Re-implement. Not working properly yet.
	 */
    private void goToPreviousScreen() { //System.out.println("BaseController.goToPreviousScreen()");
    	
    	//System.out.println("\n\n========" + this );
    	
    	//Get all required interfaces for this method
    	IManager manager = ComponentFactory.createInstance();
		IFilesystemCtr fileSystem = (IFilesystemCtr)manager.getRequiredInterface("IFilesystemCtr");
		IAlbumCtr album = (IAlbumCtr)manager.getRequiredInterface("IAlbumCtr");
		//System.out.println("ALBUM**************"+album);
		//System.out.println("FILESYSTEM****************"+fileSystem);
    	//System.out.println("currentScreenName***********"+currentScreenName);
		
    	if (currentScreenName.equals(Constants.ALBUMLIST_SCREEN)) {
		    System.err.println("Can't go back here...Should never reach this spot");
		} else if (currentScreenName.equals(Constants.IMAGE_SCREEN)) {		    
		    //Go to the image list here, not the main screen...
		    showImageList(currentStoreName);
		    currentScreenName = Constants.IMAGELIST_SCREEN;
		} else if (currentScreenName.equals(Constants.IMAGELIST_SCREEN)) {
			album.initAlbumListScreen( fileSystem.getAlbumNames() );
		    currentScreenName = Constants.ALBUMLIST_SCREEN;
		}else if (currentScreenName.equals(Constants.NEWALBUM_SCREEN)) {
			album.initAlbumListScreen( fileSystem.getAlbumNames() );
		    currentScreenName = Constants.ALBUMLIST_SCREEN;
		}else if (currentScreenName.equals(Constants.CONFIRMDELETEALBUM_SCREEN)) {
			album.initAlbumListScreen( fileSystem.getAlbumNames() );
			currentScreenName = Constants.ALBUMLIST_SCREEN;
		}else if (currentScreenName.equals(Constants.ADDPHOTOTOALBUM_SCREEN)) {
			showImageList(currentStoreName);
		    currentScreenName = Constants.IMAGELIST_SCREEN;
		}
		
	
    } 

    /**
     * Show the current image that is selected
	 */
	public void showImage() { System.out.println("BaseController.showImage()");
		//Get all required interfaces for this method
		IManager manager = ComponentFactory.createInstance();
		IFilesystemCtr fileSystem = (IFilesystemCtr)manager.getRequiredInterface("IFilesystemCtr");
		IPhotoCtr photo = (IPhotoCtr)manager.getRequiredInterface("IPhotoCtr");
		IExceptionsHandlerCtr excepHandler = (IExceptionsHandlerCtr)manager.getRequiredInterface("IExceptionsHandlerCtr");
    
		
		String name = photo.getSelectedPhoto();
		Image storedImage = null;
		try {
			storedImage = fileSystem.getImageFromRecordStore(currentStoreName, name);
		} catch (ImageNotFoundException e) {
			excepHandler.handle( e );
			//Alert alert = new Alert( "Error", "The selected photo was not found in the mobile device", null, AlertType.ERROR);
			//TODO: Apagar codigo comentado
			//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
	        return;
		} catch (PersistenceMechanismException e) {
			excepHandler.handle( e );
			//Alert alert = new Alert( "Error", "The mobile database can open this photo", null, AlertType.ERROR);
			//TODO: Apagar codigo comentado
			//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
	        return;
		}		
		
		//We can pass in the image directly here, or just the name/model pair and have it loaded
		photo.initPhotoViewScreen( storedImage );
	}

    /**
     * Show the list of images in the record store
	 * TODO: Refactor - Move this to ImageList class
	 */
	public void showImageList(String recordName) { System.out.println("BaseController.showImageList()");

		//Get all required interfaces for this method
		IManager manager = ComponentFactory.createInstance();
		IFilesystemCtr fileSystem = (IFilesystemCtr)manager.getRequiredInterface("IFilesystemCtr");
		IPhotoCtr photo = (IPhotoCtr)manager.getRequiredInterface("IPhotoCtr");
		IExceptionsHandlerCtr excepHandler = (IExceptionsHandlerCtr)manager.getRequiredInterface("IExceptionsHandlerCtr");
    		
		if (recordName == null)
			recordName = currentStoreName;
		
		String[] labels = null;
		try {
			labels = fileSystem.getImageNames(recordName);
		} catch (UnavailablePhotoAlbumException e) {
			excepHandler.handle( e );
			//Alert alert = new Alert( "Error", "The list of photos can not be recovered", null, AlertType.ERROR);
			//TODO: Apagar codigo comentado
			//Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
	        return;
	    }
		
		//TODO: Apagar codigo comentado
		//loop through array and add labels to list
		//for (int i = 0; i < labels.length; i++) {
			//if (labels[i] != null) {
				//Add album name to menu list
				//imageList.append(labels[i], null);
				
			//}
		//}
		//setCurrentScreen(imageList);
		//currentMenu = "list";
		
		photo.initPhotoListScreen( labels );
	}

    /**
     * Get the last selected image from the Photo List screen.
	 * TODO: This really only gets the selected List item. 
	 * So it is only an image name if you are on the PhotoList screen...
	 * Need to fix this
	 */
	public String getSelectedImageName() { //System.out.println("BaseController.getSelectedImageName()");
		//Get all required interfaces for this method
		IManager manager = ComponentFactory.createInstance();
		IPhotoCtr photo = (IPhotoCtr)manager.getRequiredInterface("IPhotoCtr");
		
	    return photo.getSelectedPhoto();
		
	}
	
	/**
	 * @return Returns the currentStoreName.
	 */
	public String getCurrentStoreName() { //System.out.println("BaseController.getCurrentStoreName()");
		return currentStoreName;
	}
	
	/**
	 * @param currentScreenName The currentScreenName to set.
	 */
	//public void setCurrentScreenName(String currentScreenName) {
	//	this.currentScreenName = currentScreenName;
	//}
	
    /**
     * @return
     */
    public BaseController getNextController() { //System.out.println("BaseController.getNextController()");
        return nextController;
    }
    
    /**
     * @param nextController
     */
    public void setNextController(BaseController nextController) { //System.out.println("BaseController.setNextController()");
        this.nextController = nextController;
    }
}
