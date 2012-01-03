package br.unicamp.ic.sed.mobilemedia.mobilephonemgr;

import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IMobileResources;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.spec.req.IMobileResourcesWEH;

public class IAdapterMobileResourcesWEH implements IMobileResourcesWEH {

	private Manager manager;
	
	public IAdapterMobileResourcesWEH(Manager mgr) {// System.out.println("IAdapterMobileResourcesWEH.IAdapterMobileResourcesWEH()");
		manager = mgr;
	}
	
	public MIDlet getMainMIDlet() { //System.out.println("IAdapterMobileResourcesWEH.getMainMIDlet()");
		IMobileResources mobResources = (IMobileResources) manager.getRequiredInterface("IMobileResources");
		return mobResources.getMainMIDlet();
	}

}
