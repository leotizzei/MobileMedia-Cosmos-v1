   
package br.unicamp.ic.sed.mobilemedia.filesystemmgr_mobilephonemgr.impl;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.excep.UnavailablePhotoAlbumException;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IFilesystem;

class IFileSystemAdapter implements IFilesystem{
	
	private Manager manager;
	
	public IFileSystemAdapter(Manager mgr) { //System.out.println("IFileSystemAdapter.IFileSystemAdapter()");
		this.manager = mgr;
	}
	
	public String[] getImageNames ( String albumName ) throws UnavailablePhotoAlbumException{
	//System.out.println("IFileSystemAdapter.getImageNames()");
	
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			return filesystem.getImageNames(albumName);
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.UnavailablePhotoAlbumException e) {
				throw new UnavailablePhotoAlbumException( e.getMessage() );
		}
	} 
	
	public void addNewPhotoToAlbum ( String imageName, String imagePath, String albumName ) throws InvalidImageDataException, PersistenceMechanismException{
//System.out.println("IFileSystemAdapter.addNewPhotoToAlbum()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			filesystem.addNewPhotoToAlbum(imageName, imagePath, albumName);
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException e) {
			throw new InvalidImageDataException( e.getMessage() );
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException e) {
			throw new PersistenceMechanismException( e.getMessage() );
		}
	}
	
	public void deleteImage ( String imageName, String albumName ) throws PersistenceMechanismException, ImageNotFoundException{
//System.out.println("IFileSystemAdapter.deleteImage()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			filesystem.deleteImage(imageName, albumName);
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException e) {
			throw new PersistenceMechanismException( e.getMessage() );
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException e) {
			throw new ImageNotFoundException( e.getMessage() ); 
		}
	}
	
	public Image getImageFromRecordStore ( String albumName, String imageName ) throws ImageNotFoundException, PersistenceMechanismException{
//System.out.println("IFileSystemAdapter.getImageFromRecordStore()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			return filesystem.getImageFromRecordStore(albumName, imageName);
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException e) {
			throw new ImageNotFoundException( e.getMessage() );
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException e) {
			throw new PersistenceMechanismException( e.getMessage() );
		}
	}
	
	public String[] getAlbumNames (  ){
//System.out.println("IFileSystemAdapter.getAlbumNames()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");		return filesystem.getAlbumNames();
	}
	
	public void resetImageData (  ) throws PersistenceMechanismException{
//System.out.println("IFileSystemAdapter.resetImageData()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			filesystem.resetImageData();
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException e) {
			throw new PersistenceMechanismException( e.getMessage() );
		}
	}
	
	public void createNewPhotoAlbum ( String albumName ) throws PersistenceMechanismException, InvalidPhotoAlbumNameException{
//System.out.println("IFileSystemAdapter.createNewPhotoAlbum()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			filesystem.createNewPhotoAlbum(albumName);
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException e) {
			throw new PersistenceMechanismException( e.getMessage() );
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidPhotoAlbumNameException e) {
			throw new InvalidPhotoAlbumNameException( e.getMessage() );
		}
	}
	
	public void deletePhotoAlbum ( String albumName ) throws PersistenceMechanismException{
//System.out.println("IFileSystemAdapter.deletePhotoAlbum()");		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		try {
			filesystem.deletePhotoAlbum(albumName);
		} catch (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException e) {
			throw new PersistenceMechanismException( e.getMessage() );
		}
	}

}