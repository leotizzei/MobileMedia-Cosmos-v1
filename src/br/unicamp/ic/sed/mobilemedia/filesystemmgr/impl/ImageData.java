/*
 * Created on Nov 26, 2004
 *
 */
package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

/**
 * @author trevor
 *
 * This class holds meta data associated with a photo or image. There is a one-to-one
 * relationship between images and image metadata. (ie. Every photo in MobileMedia will
 * have a corresonding ImageData object). 
 * It stores the recordId of the image record in RMS, the recordID of the metadata record
 * the name of the photo album(s) it belongs to, the text label, associated phone numbers
 * etc.
 * 
 */
public class ImageData {
	
	private int recordId; //imageData recordId
	private int foreignRecordId; //image recordId
	private String parentAlbumName; //Should we allow single image to be part of multiple albums?
	private String imageLabel;
	
	/**
	 * Constructor
	 * @param foreignRecordId
	 * @param parentAlbumName
	 * @param imageLabel
	 */
	public ImageData(int foreignRecordId, String parentAlbumName,String imageLabel) {
		super();
		this.foreignRecordId = foreignRecordId;
		this.parentAlbumName = parentAlbumName;
		this.imageLabel = imageLabel;
		
		//System.out.println("ImageData.enclosing_method()");
	}
	
	/**
	 * @return Returns the recordId.
	 */
	public int getRecordId() { //System.out.println("ImageData.getRecordId()");
		return recordId;
	}
	
	/**
	 * @param recordId The recordId to set.
	 */
	public void setRecordId(int recordId) { //System.out.println("ImageData.setRecordId()");
		this.recordId = recordId;
	}
	
	/**
	 * @return Returns the foreignRecordId.
	 */
	public int getForeignRecordId() { //System.out.println("ImageData.getForeignRecordId()");
		return foreignRecordId;
	}
	
	/**
	 * @param foreignRecordId The foreignRecordId to set.
	 */
	public void setForeignRecordId(int foreignRecordId) { //System.out.println("ImageData.setForeignRecordId()");
		this.foreignRecordId = foreignRecordId;
	}
	
	/**
	 * @return Returns the imageLabel.
	 */
	public String getImageLabel() { //System.out.println("ImageData.getImageLabel()");
		return imageLabel;
	}
	
	/**
	 * @param imageLabel The imageLabel to set.
	 */
	public void setImageLabel(String imageLabel) { //System.out.println("ImageData.setImageLabel()");
		this.imageLabel = imageLabel;
	}
	
	/**
	 * @return Returns the parentAlbumName.
	 */
	public String getParentAlbumName() { //System.out.println("ImageData.getParentAlbumName()");
		return parentAlbumName;
	}
	
	/**
	 * @param parentAlbumName The parentAlbumName to set.
	 */
	public void setParentAlbumName(String parentAlbumName) { //System.out.println("ImageData.setParentAlbumName()");
		this.parentAlbumName = parentAlbumName;
	}
}
