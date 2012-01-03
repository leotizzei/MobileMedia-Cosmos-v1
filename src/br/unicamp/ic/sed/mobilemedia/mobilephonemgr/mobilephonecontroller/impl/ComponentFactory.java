package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.impl;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;


public class ComponentFactory {

	static IManager manager = null;

	public static IManager createInstance(){ //System.out.println("ComponentFactory.createInstance()");
		if(manager == null)
			manager = new Manager();
		
		return manager;
	}
}



