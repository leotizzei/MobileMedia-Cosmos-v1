package br.unicamp.ic.sed.mobilemedia.mobilephonemgr;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;



public class ComponentFactory {

	static IManager mgr;
	
	public static IManager createInstance(){// System.out.println("ComponentFactory.createInstance()");
		if(mgr == null)
			mgr = new Manager();
		return mgr;
	}
	
}
