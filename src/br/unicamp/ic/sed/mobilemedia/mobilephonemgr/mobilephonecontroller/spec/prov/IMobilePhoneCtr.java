package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.spec.prov;

import javax.microedition.lcdui.Command;

public interface IMobilePhoneCtr{

	public void postCommand ( Command comand ); 
	
	public void startUp();
}