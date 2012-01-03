package br.unicamp.ic.sed.mobilemedia.album.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import br.unicamp.ic.sed.mobilemedia.album.spec.prov.IManager;

class Manager implements IManager{

	private Hashtable reqInterfaceMap = new Hashtable();
	
	private IAlbumFacade facade;

	public String[] getProvidedInterfaces(){ //System.out.println("Manager.getProvidedInterfaces()");
	   Vector provInterfaceList = new Vector();
	   provInterfaceList.addElement("IAlbum");
	     
	   
	   return convertListToArray(provInterfaceList.elements());
	}
	
	public String[] getRequiredInterfaces(){ //System.out.println("Manager.getRequiredInterfaces()");
	
		return convertListToArray(reqInterfaceMap.keys());
	}
	
	public Object getProvidedInterface(String name){ //System.out.println("Manager.getProvidedInterface()");

	   if (name.equals("IAlbum")){
		   if( facade == null )
			   facade =  new IAlbumFacade( );
		   return facade;
	   }
	   
	   return null;
	}
	
	public void setRequiredInterface(String name, Object facade){ //System.out.println("Manager.setRequiredInterface()");
		reqInterfaceMap.put(name,facade);
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



