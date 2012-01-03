package br.unicamp.ic.sed.mobilemedia.photo_mobilephonemgr.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class Manager implements IManager{

	Hashtable reqInterfaceMap = new Hashtable();
	Hashtable provInterfaceMap = new Hashtable();
	
	public Manager() {
		provInterfaceMap.put("IPhoto", new IPhotoAdapter(this));
		provInterfaceMap.put("IMobilePhone", new IMobilePhoneAdapter(this));
	}
	
	public String[] getProvidedInterfaces(){ //System.out.println("Manager.getProvidedInterfaces()");

		
	   return convertListToArray(provInterfaceMap.keys());
	}
	
	public String[] getRequiredInterfaces(){ //System.out.println("Manager.getRequiredInterfaces()");
	
		return convertListToArray(reqInterfaceMap.keys());
	}
	
	public Object getProvidedInterface(String name){ //System.out.println("Manager.getProvidedInterface()");

	  
	   return provInterfaceMap.get(name);
	}
	
	public void setRequiredInterface(String name, Object adapter){ //System.out.println("Manager.setRequiredInterface()");
		reqInterfaceMap.put(name,adapter);
	}
	
	public Object getRequiredInterface(String name){ //System.out.println("Manager.getRequiredInterface()");
	   return reqInterfaceMap.get(name);
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



