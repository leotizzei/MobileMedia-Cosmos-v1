package br.unicamp.ic.sed.mobilemedia.filesystemmgr_mobilephonemgr.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class Manager implements IManager{

	Hashtable requiredInterfaces;
	Hashtable providedInterfaces;
 
	public Manager() { //System.out.println("Manager.Manager()");
		requiredInterfaces = new Hashtable();
		providedInterfaces = new Hashtable();
		this.providedInterfaces.put("IFilesystem",new IFileSystemAdapter(this));
	}
	
	public String[] getProvidedInterfaces(){ //System.out.println("Manager.getProvidedInterfaces()");
	   Vector provInterfaceList = new Vector();
	  provInterfaceList.addElement("IFilesystem");
	     
	   
	   return convertListToArray(provInterfaceList.elements());
	}
	
	public String[] getRequiredInterfaces(){ //System.out.println("Manager.getRequiredInterfaces()");
	
		return convertListToArray(requiredInterfaces.keys());
	}
	
	public Object getProvidedInterface(String name){
//System.out.println("Manager.getProvidedInterface()");
	    
	   return this.providedInterfaces.get(name);
	}
	
	public void setRequiredInterface(String name, Object adapter){ //System.out.println("Manager.setRequiredInterface()");
		this.requiredInterfaces.put(name,adapter);
	}
	
	public Object getRequiredInterface(String name){ //System.out.println("Manager.getRequiredInterface()");
	   return requiredInterfaces.get(name);
	}

	private String[] convertListToArray(Enumeration stringEnum){ //System.out.println("Manager.convertListToArray()");
		Vector stringVector = new Vector();
		for (Enumeration iter = stringEnum; iter.hasMoreElements();) {
			String element = (String) iter.nextElement();
			stringVector.addElement(element);
		}
		
		String[] stringArray = new String[stringVector.size()];
		for (int i=0; i < stringVector.size(); i++){
			stringArray[i] = (String) stringVector.elementAt(i);
		}
		return stringArray;
	}
}



