   
package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.impl;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.prov.IMobilePhoneCtr;

class IMobilePhoneMgrFacade implements IMobilePhoneCtr{

	private BaseController controller;
	
	public IMobilePhoneMgrFacade() { //System.out.println("IMobilePhoneMgrFacade.IMobilePhoneMgrFacade()");
		controller = new BaseController();
		
	}
	
	public void postCommand ( Command command ){ //System.out.println("IMobilePhoneMgrFacade.postCommand()");
		controller.postCommand( command );
	}



	public void startUp() { //System.out.println("IMobilePhoneMgrFacade.startUp()");
		BaseController controller = new BaseController();
		//controller.setManager(manager);
		controller.init();
		
	}



	

}