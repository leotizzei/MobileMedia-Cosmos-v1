package br.unicamp.ic.sed.mobilemedia.mobilephonemgr;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.req.IAlbumCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IAlbum;


public class IAdapterAlbum implements IAlbumCtr {

	
	
	
	
	public String getNewAlbumName() { //System.out.println("IAdapterAlbum.getNewAlbumName()");
		IManager mgr = ComponentFactory.createInstance();
		IAlbum album = (IAlbum) mgr.getRequiredInterface("IAlbum");
		return album.getNewAlbumName();
	}

	
	public String getSelectedAlbum() { //System.out.println("IAdapterAlbum.getSelectedAlbum()");
		IManager mgr = ComponentFactory.createInstance();
		IAlbum album = (IAlbum) mgr.getRequiredInterface("IAlbum");
		return album.getSelectedAlbum();
	}

	
	public void initAlbumListScreen(String[] albumNames) {// System.out.println("IAdapterAlbum.initAlbumListScreen()");
		IManager mgr = ComponentFactory.createInstance();
		IAlbum album = (IAlbum) mgr.getRequiredInterface("IAlbum");
		if(album == null)
			System.err.println("album is null");
		album.initAlbumListScreen(albumNames);
		
		
	}

	
	public void initDeleteAlbumScreen(String albumName) { //System.out.println("IAdapterAlbum.initDeleteAlbumScreen()");
		IManager mgr = ComponentFactory.createInstance();
		IAlbum album = (IAlbum) mgr.getRequiredInterface("IAlbum");
		album.initDeleteAlbumScreen(albumName);
		

	}


	public void initNewAlbumScreen() { //System.out.println("IAdapterAlbum.initNewAlbumScreen()");
		IManager mgr = ComponentFactory.createInstance();
		IAlbum album = (IAlbum) mgr.getRequiredInterface("IAlbum");
		album.initNewAlbumScreen();

	}

}
