package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.impl;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;

public class ComponentFactory {

	private static IManager manager = null;

	public static IManager createInstance(){ //System.out.println("ComponentFactory.createInstance()");
	
		if (manager==null)
			manager = (IManager) new Manager();
		
		return manager;
	}
}



