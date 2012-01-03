package br.unicamp.ic.sed.mobilemedia.mobilephonemgr;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.prov.IMobilePhoneCtr;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IMobilePhone;

class IMobilePhoneFacade implements IMobilePhone {

	private Manager mgr;

	private IMobilePhoneCtr imobile;

	public IMobilePhoneFacade(Manager manager) { //System.out.println("IMobilePhoneFacade.IMobilePhoneFacade()");
		
		//System.out.println("construtor do IMobilePhoneFacade");
		if(manager == null)
			System.err.println("manager is null");
		
		this.mgr = manager;
		
		// get IManager of the mobilephonecontroller
		IManager imanager = this.mgr.getInternalComponent("mobilephonecontroller");
		if(imanager == null)
			System.err.println("IManager is null");
		else
			System.err.println("imanager is not null");
		
		//get IMobilePhone of the mobilephonecontroller
		this.imobile =	(IMobilePhoneCtr) imanager.getProvidedInterface("IMobilePhoneCtr");
		if(imobile == null)
			System.err.println("imobile is null");
		
	}


	public void postCommand(Command comand) { //System.out.println("IMobilePhoneFacade.postCommand()");


		imobile.postCommand(comand);

	}



	public void startUp() { //System.out.println("IMobilePhoneFacade.startUp()");
		//System.out.println("br.unicamp.ic.sed.mobilemedia.mobilephonemgr.IMobilePhoneFacade");
		//System.out.println("Starting MobilePhone...");
		if(imobile == null)
			System.err.println("Composite component was not correctly initiated.");
		imobile.startUp();

	}

}
