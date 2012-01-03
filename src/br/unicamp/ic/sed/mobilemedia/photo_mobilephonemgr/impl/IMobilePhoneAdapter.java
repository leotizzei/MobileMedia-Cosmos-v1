   
package br.unicamp.ic.sed.mobilemedia.photo_mobilephonemgr.impl;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.photo.spec.req.IMobilePhone;

class IMobilePhoneAdapter implements IMobilePhone{
	
	Manager manager;
	
	public IMobilePhoneAdapter(Manager mgr) {
		manager = mgr;
	}
	
	
	public void postCommand ( Command comand ){ //System.out.println("IMobilePhoneAdapter.postCommand()");
		br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IMobilePhone mobilePhone;
		
		
		mobilePhone = (br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IMobilePhone)manager.getRequiredInterface("IMobilePhone");
		mobilePhone.postCommand(comand);
	} 

}