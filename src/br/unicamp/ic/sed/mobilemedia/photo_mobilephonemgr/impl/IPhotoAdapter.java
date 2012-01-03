   
package br.unicamp.ic.sed.mobilemedia.photo_mobilephonemgr.impl;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IPhoto;


class IPhotoAdapter implements IPhoto{
	
	Manager manager ;
	
	public IPhotoAdapter(Manager mgr) {
		manager = mgr;
	}
	
	
	public void initPhotoListScreen ( String[] imageNames ){ //System.out.println("IPhotoAdapter.initPhotoListScreen()");

		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto iphoto;
		
		IManager manager = ComponentFactory.createInstance();
		iphoto = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		iphoto.initPhotoListScreen(imageNames);
		
	} 
	public void initPhotoViewScreen ( Image image ){ //System.out.println("IPhotoAdapter.initPhotoViewScreen()");
		
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto iphoto;
		
		IManager manager = ComponentFactory.createInstance();
		iphoto = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		iphoto.initPhotoViewScreen(image);
	} 
	
	public void initAddPhotoToAlbum (String albumName){ //System.out.println("IPhotoAdapter.initAddPhotoToAlbum()");
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto iphoto;
		
		IManager manager = ComponentFactory.createInstance();
		iphoto = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		
		iphoto.initAddPhotoToAlbum(albumName);
		
	} 
	public String getSelectedPhoto (  ){ //System.out.println("IPhotoAdapter.getSelectedPhoto()");
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto iphoto;
		
		IManager manager = ComponentFactory.createInstance();
		iphoto = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		
		return iphoto.getSelectedPhoto();
		
	} 
	public String getAddedPhotoPath (  ){ //System.out.println("IPhotoAdapter.getAddedPhotoPath()");
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto iphoto;
		
		IManager manager = ComponentFactory.createInstance();
		iphoto = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		
		return iphoto.getAddedPhotoPath();
		
	} 
	public String getAddedPhotoName (  ){ //System.out.println("IPhotoAdapter.getAddedPhotoName()");
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto iphoto;
		
		IManager manager = ComponentFactory.createInstance();
		iphoto = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		
		return iphoto.getAddedPhotoName();
		
	} 

}