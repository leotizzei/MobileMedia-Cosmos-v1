/*
 * Created on Sep 28, 2004
 */
package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.NullAlbumDataReference;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.UnavailablePhotoAlbumException;

/**
 * @author tyoung
 * 
 * This class represents the data model for Photo Albums. A Photo Album object
 * is essentially a list of photos or images, stored in a Hashtable. Due to
 * constraints of the J2ME RecordStore implementation, the class stores a table
 * of the images, indexed by an identifier, and a second table of image metadata
 * (ie. labels, album name etc.)
 * 
 * This uses the ImageAccessor class to retrieve the image data from the
 * recordstore (and eventually file system etc.)
 */
public class AlbumData {

	private ImageAccessor imageAccessor;

	//imageInfo holds image metadata like label, album name and 'foreign key' index to
	// corresponding RMS entry that stores the actual Image object
	protected Hashtable imageInfoTable = new Hashtable();

	public boolean existingRecords = false; //If no records exist, try to reset

	/**
	 *  Constructor. Creates a new instance of ImageAccessor
	 */
	public AlbumData() { //System.out.println("AlbumData.AlbumData()");
		imageAccessor = new ImageAccessor(this);
		
	}

	/**
	 *  Load any photo albums that are currently defined in the record store
	 */
	public String[] getAlbumNames() { System.out.println("AlbumData.getAlbumNames()");

		//Shouldn't load all the albums each time
		//Add a check somewhere in ImageAccessor to see if they've been
		//loaded into memory already, and avoid the extra work...
		try {
			imageAccessor.loadAlbums();
		} catch (InvalidImageDataException e) {
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
		}
		return imageAccessor.getAlbumNames();
	}

	/**
	 *  Get the names of all images for a given Photo Album that exist in the Record Store.
	 * @throws UnavailablePhotoAlbumException 
	 * @throws InvalidImageDataException 
	 * @throws PersistenceMechanismException 
	 */
	public String[] getImageNames(String recordName) throws UnavailablePhotoAlbumException  {
System.out.println("AlbumData.getImageNames()");
		String[] result;
		try {
			result = imageAccessor.loadImageDataFromRMS(recordName);
		} catch (PersistenceMechanismException e) {
			throw new UnavailablePhotoAlbumException( e.getMessage() );
			
		} catch (InvalidImageDataException e) {
			throw new UnavailablePhotoAlbumException( e.getMessage() );
		}

		return result;

	}

	/**
	 *  Define a new user photo album. This results in the creation of a new
	 *  RMS Record store.
	 * @throws PersistenceMechanismException 
	 * @throws InvalidPhotoAlbumNameException 
	 */
	public void createNewPhotoAlbum(String albumName) throws PersistenceMechanismException, InvalidPhotoAlbumNameException {
		System.out.println("AlbumData.createNewPhotoAlbum()");
		imageAccessor.createNewPhotoAlbum(albumName);

	}
	
	public void deletePhotoAlbum(String albumName) throws PersistenceMechanismException{
System.out.println("AlbumData.deletePhotoAlbum()");
		imageAccessor.deletePhotoAlbum(albumName);
	}

	/**
	 *  Get a particular image (by name) from a photo album. The album name corresponds
	 *  to a record store.
	 * @throws ImageNotFoundException 
	 * @throws PersistenceMechanismException 
	 */
	public Image getImageFromRecordStore(String recordStore, String imageName) throws ImageNotFoundException, PersistenceMechanismException {
System.out.println("AlbumData.getImageFromRecordStore()");
		ImageData imageInfo = null;
		try {
			imageInfo = imageAccessor.getImageInfo(imageName);
		} catch (NullAlbumDataReference e) {
			imageAccessor = new ImageAccessor(this);
		}
		//Find the record ID and store name of the image to retrieve
		int imageId = imageInfo.getForeignRecordId();
		String album = imageInfo.getParentAlbumName();
		//Now, load the image (on demand) from RMS and cache it in the hashtable
		Image imageRec = imageAccessor.loadSingleImageFromRMS(album, imageName, imageId); //rs.getRecord(recordId);
		return imageRec;

	}
	public void addNewPhotoToAlbum(String label, String path, String album) throws InvalidImageDataException, PersistenceMechanismException{
System.out.println("AlbumData.addNewPhotoToAlbum()");
		imageAccessor.addImageData(label, path, album);
	}

	/**
	 *  Delete a photo from the photo album. This permanently deletes the image from the record store
	 * @throws ImageNotFoundException 
	 * @throws PersistenceMechanismException 
	 */
	public void deleteImage(String imageName, String storeName) throws PersistenceMechanismException, ImageNotFoundException {
System.out.println("AlbumData.deleteImage()");
		try {
			imageAccessor.deleteSingleImageFromRMS(imageName, storeName);
		}
		catch (NullAlbumDataReference e) {
			imageAccessor = new ImageAccessor(this);
			e.printStackTrace();
		} 
	}
	
	/**
	 *  Reset the image data for the application. This is a wrapper to the ImageAccessor.resetImageRecordStore
	 *  method. It is mainly used for testing purposes, to reset device data to the default album and photos.
	 * @throws PersistenceMechanismException 
	 * @throws InvalidImageDataException 
	 */
	public void resetImageData() throws PersistenceMechanismException {
System.out.println("AlbumData.resetImageData()");
		try {
			imageAccessor.resetImageRecordStore();
		} catch (InvalidImageDataException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the hashtable that stores the image metadata in memory.
	 * @return Returns the imageInfoTable.
	 */
	public Hashtable getImageInfoTable() { //System.out.println("AlbumData.getImageInfoTable()");
		return imageInfoTable;
	}

	/**
	 * Update the hashtable that stores the image metadata in memory
	 * @param imageInfoTable
	 *            The imageInfoTable to set.
	 */
	public void setImageInfoTable(Hashtable imageInfoTable) { //System.out.println("AlbumData.setImageInfoTable()");
		this.imageInfoTable = imageInfoTable;
	}
}