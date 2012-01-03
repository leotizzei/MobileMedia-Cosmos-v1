
package br.unicamp.ic.sed.mobilemedia.photo.impl;

import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto;

class IPhotoFacade implements IPhoto{

	private PhotoController photoController;
	
	public IPhotoFacade( ){ //System.out.println("IPhotoFacade.IPhotoFacade()");
		photoController = new PhotoController( );
	}
	
	
	public void initPhotoListScreen ( String[] imageNames ){ //System.out.println("IPhotoFacade.initPhotoListScreen()");
		photoController.initPhotoListScreen(imageNames);

	}

	public void initPhotoViewScreen ( Image image ){ //System.out.println("IPhotoFacade.initPhotoViewScreen()");
		photoController.initPhotoViewScreen(image);

	} 

	public String getSelectedPhoto (  ){ //System.out.println("IPhotoFacade.getSelectedPhoto()");
		return photoController.getSelectedPhoto();

	}
	
	public String getAddedPhotoName() { //System.out.println("IPhotoFacade.getAddedPhotoName()");
			
		return photoController.getAddedPhotoName();
	}
	
	public String getAddedPhotoPath() { //System.out.println("IPhotoFacade.getAddedPhotoPath()");
	
		return photoController.getAddedPhotoPath();
	}
	
	
	public void initAddPhotoToAlbum(String albumName) { //System.out.println("IPhotoFacade.initAddPhotoToAlbum()");
		photoController.initAddPhotoToAlbum(albumName);

	} 


}