package br.unicamp.ic.sed.mobilemedia.album_mobilephonemgr.impl;

import java.util.*;

class Manager implements IManager{

	Hashtable reqInterfaceMap;
	Hashtable provInterfaceMap;

	public Manager() {
		this.provInterfaceMap = new Hashtable();
		this.reqInterfaceMap = new Hashtable();
		this.provInterfaceMap.put("IMobilePhone", new IMobilePhoneAdapter(this));
		this.provInterfaceMap.put("IAlbum", new IAlbumAdapter(this));
	}
	
	
	public String[] getProvidedInterfaces(){ 
		Vector provInterfaceList = new Vector();
		provInterfaceList.addElement("IAlbum");
		provInterfaceList.addElement("IMobilePhone");

		return convertListToArray(provInterfaceList.elements());
	}

	public String[] getRequiredInterfaces(){ 

		return convertListToArray(reqInterfaceMap.keys());
	}

	public Object getProvidedInterface(String name){ 
		return this.provInterfaceMap.get(name);
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



