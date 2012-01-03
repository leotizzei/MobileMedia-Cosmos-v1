package br.unicamp.ic.sed.mobilemedia.mobilephonemgr;
import java.util.Hashtable;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;

class Manager extends AManagerComposite implements IManager {

	
	Hashtable internalComponents;
	Hashtable requiredInterfaces;
	Hashtable providedInterfaces;
	
	
	Manager() { //System.out.println("Manager.Manager()");
		requiredInterfaces = new Hashtable();
		providedInterfaces= new Hashtable();
		internalComponents = new Hashtable();

		// getting all IManager from the internal components
		IManager mobilePhoneController = br.unicamp.ic.sed.mobilemedia.mobilephonemgr.mobilephonecontroller.impl.ComponentFactory.createInstance();
		IManager connMobPh_ExcHand = br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionshandler_mobilephonemgr.impl.ComponentFactory.createInstance();
		IManager warningEHIManager = br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.impl.ComponentFactory.createInstance();
		
		this.setInternalComponent("mobilephonecontroller", mobilePhoneController);
		this.setInternalComponent("warningexceptionhandler", warningEHIManager);
		this.setInternalComponent("warningexceptionshandler_mobilephonemgr", connMobPh_ExcHand);
		
		// setting internal connections
		mobilePhoneController.setRequiredInterface("IExceptionsHandlerCtr",connMobPh_ExcHand.getProvidedInterface("IExceptionsHandlerCtr"));
		connMobPh_ExcHand.setRequiredInterface("IExceptionsHandlerCtr", warningEHIManager.getProvidedInterface("IExceptionsHandlerCtr"));
		
		// setting mobilephonecontroller's required interfaces
		mobilePhoneController.setRequiredInterface("IPhotoCtr",new IAdapterPhoto());
		mobilePhoneController.setRequiredInterface("IFilesystemCtr",new IAdapterFilesystem());
		mobilePhoneController.setRequiredInterface("IMobileResourcesCtr",new IAdapterMobileResources());
		mobilePhoneController.setRequiredInterface("IAlbumCtr",new IAdapterAlbum());
		
		
		//System.out.println("******************---"+this);
		
		
		// setting connector warningexceptionshandler_mobilephonemgr's required interface
		connMobPh_ExcHand.setRequiredInterface("IExceptionsHandlerCtr", warningEHIManager.getProvidedInterface("IExceptionsHandlerCtr"));
		
		//setting WarningExceptionHandler's required interfaces		
		warningEHIManager.setRequiredInterface("IMobileResources", new IAdapterMobileResourcesWEH(this));
		
		// setting provided interfaces		
		this.setProvidedInterface("IMobilePhone", new IMobilePhoneFacade(this));
		
		//System.out.println("Saindo do mobilephonemgr.Manager");

		
	}


	public IManager getInternalComponent(String arg0){ //System.out.println("Manager.getInternalComponent()");

		return (IManager) internalComponents.get(arg0);
	}


	

	public boolean isComposite() { //System.out.println("Manager.isComposite()");

		return true;
	}




	public void setInternalComponent(String arg0, IManager arg1){ //System.out.println("Manager.setInternalComponent()");
		internalComponents.put(arg0, arg1);

	}
	
	public Object getProvidedInterface(String name){ //System.out.println("Manager.getProvidedInterface()");
		return this.providedInterfaces.get(name);
	}
	
	public Object getRequiredInterface(String name){ //System.out.println("Manager.getRequiredInterface()");
		return this.requiredInterfaces.get(name);
	}

	public void setProvidedInterface(String name, Object facade){ //System.out.println("Manager.setProvidedInterface()");
		this.providedInterfaces.put(name, facade);
	}

	public void setRequiredInterface(String name,Object facade){ //System.out.println("Manager.setRequiredInterface()");
		if(name == null)
			System.err.println("name is null");
		
		if(facade == null)
			System.err.println("br.unicamp.ic.sed.mobilemedia.mobilephonemgr.Manager facade is null");
		else{
			System.err.println("("+name+","+facade.getClass().getName()+")");
		}
		this.requiredInterfaces.put(name, facade);
	}








}
