   
package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.UnavailablePhotoAlbumException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem;

class IFilesystemFacade implements IFilesystem{

	private AlbumData albumData;
	
	public IFilesystemFacade(){ //System.out.println("IFilesystemFacade.IFilesystemFacade()");
		albumData = new AlbumData();
	}
	
	public String[] getImageNames ( String albumName ) throws UnavailablePhotoAlbumException{
System.out.println("IFilesystemFacade.getImageNames()");
		return albumData.getImageNames(albumName);
	} 
	
	public void addNewPhotoToAlbum ( String imageName, String imagePath, String albumName ) throws InvalidImageDataException, PersistenceMechanismException{
System.out.println("IFilesystemFacade.addNewPhotoToAlbum()");
		albumData.addNewPhotoToAlbum(imageName, imagePath, albumName);
	} 
	
	public void deleteImage ( String imageName, String albumName ) throws PersistenceMechanismException, ImageNotFoundException{
System.out.println("IFilesystemFacade.deleteImage()");
		albumData.deleteImage(imageName, albumName);
	} 
	
	public Image getImageFromRecordStore ( String albumName, String imageName ) throws ImageNotFoundException, PersistenceMechanismException{
System.out.println("IFilesystemFacade.getImageFromRecordStore()");		
		return albumData.getImageFromRecordStore(albumName, imageName);
	} 
	
	public String[] getAlbumNames (  ){ //System.out.println("IFilesystemFacade.getAlbumNames()");
		return albumData.getAlbumNames();
	} 
	
	public void resetImageData (  ) throws PersistenceMechanismException{
System.out.println("IFilesystemFacade.resetImageData()");		
		albumData.resetImageData();	
	} 
	
	public void createNewPhotoAlbum ( String albumName ) throws PersistenceMechanismException, InvalidPhotoAlbumNameException{
System.out.println("IFilesystemFacade.createNewPhotoAlbum()");
		albumData.createNewPhotoAlbum(albumName);		
	} 
	
	public void deletePhotoAlbum ( String albumName ) throws PersistenceMechanismException{
System.out.println("IFilesystemFacade.deletePhotoAlbum()");
		albumData.deletePhotoAlbum(albumName);	
	} 

}