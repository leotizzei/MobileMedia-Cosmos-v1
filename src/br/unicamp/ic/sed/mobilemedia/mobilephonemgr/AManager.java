/*
 *  Copyright 2007 Institute of Computing, UNICAMP, Brazil
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0 
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package br.unicamp.ic.sed.mobilemedia.mobilephonemgr;

import java.util.Hashtable;
import java.util.Vector;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;

/**
 * Map-based implementation of a component Manager. This is used
 * for elementary components.
 * 
 *
 */
public abstract class AManager implements IManager {
	private Hashtable requiredInterfaces, providedInterfaces;
	private Hashtable requiredInterfacesTypes, providedInterfacesTypes;
	private Vector providedInterfacesList,requiredInterfacesList;

	protected AManager() { //System.out.println("AManager.AManager()");
		this.requiredInterfaces = new Hashtable();
		this.providedInterfaces = new Hashtable();

		/*this.providedInterfacesTypes = new Hashtable();
		this.requiredInterfacesTypes  = new Hashtable();*/

		this.providedInterfacesList = new Vector();
		this.requiredInterfacesList = new Vector();

	}


	private void setInterface(String name, Object facade, Hashtable interfaces) { //System.out.println("AManager.setInterface()");
		interfaces.put(name, facade);

	}

	private String[] convertVectorToArray(Vector list){ //System.out.println("AManager.convertVectorToArray()");
		if(list != null){
			String[] stringArray = new String[list.size()];
			for (int i=0; i < list.size(); i++){
				stringArray[i] = (String) list.elementAt(i);
			}
			return stringArray;
		}
		else{
			System.err.println("List is null");
			return null;
		}
	}

	/*It was necessary to replace this method by the convertListToArray(..)
	 * method because there was a problem with the method entrySet, which
	 * could not be compiled*/
	/*private String[] listInterfaces(Hashtable interfaceHashtable) {
		Set entries = interfaceHashtable.entrySet();
		String s[] = new String[entries.size()];
		return (String[]) interfaceHashtable.keySet().toArray(s);
	}*/

	public Object getRequiredInterface(String name) { //System.out.println("AManager.getRequiredInterface()");
		Object obj = this.requiredInterfaces.get(name);
		if (obj == null) {
			String msg = "Class [" + this.getClass().getName()
			+ "] has not been supplied with required interface [" + name + "].";
			System.err.println(msg);
		}
		return obj;
	}

	public void setProvidedInterface(String name, Object facade) { //System.out.println("AManager.setProvidedInterface()");
		//System.out.println("AManage.setProvidedInterface("+name+",facade)");
		this.setInterface(name, facade, this.providedInterfaces);
		this.providedInterfacesList.addElement(name);
	}

	public void setRequiredInterface(String name, Object object) { //System.out.println("AManager.setRequiredInterface()");
		Class clazz = (Class) this.requiredInterfacesTypes.get(name);
		if (clazz != null && ! clazz.isInstance(object)) {
			throw new ClassCastException("Supplied object is of type ["
					+ object.getClass().getName() +	"] and cannot be cast to type ["
					+ clazz.getName() + "] of required interface [" + name + "].");
		}
		this.setInterface(name, object, this.requiredInterfaces);
		this.requiredInterfacesList.addElement(name);
	}

	public Object getProvidedInterface(String name) { //System.out.println("AManager.getProvidedInterface()");
		////System.out.println("AManage.getProvidedInterface("+name+")");
		Object obj = this.providedInterfaces.get(name);
		if (obj == null) {
			String msg = "This component [" + this.getClass().getName()
			+ "] does not provide interface [" + name + "].";
			System.err.println(msg);
		}
		return obj;
	}

	public String[] listProvidedInterfaces() { //System.out.println("AManager.listProvidedInterfaces()");
		return convertVectorToArray(providedInterfacesList);
	}

	public String[] listRequiredInterfaces() { //System.out.println("AManager.listRequiredInterfaces()");
		return convertVectorToArray(requiredInterfacesList);
	}

	protected void setRequiredInterfaceType(String name, Class clazz) { //System.out.println("AManager.setRequiredInterfaceType()");
		this.requiredInterfacesTypes.put(name, clazz);
	}

	protected void setProvidedInterfaceType(String name, Class clazz) { //System.out.println("AManager.setProvidedInterfaceType()");
		this.providedInterfacesTypes.put(name, clazz);
	}

	/*public Hashtable getProvidedInterfaceTypes() {
		Hashtable ht = (Hashtable) Collections.unmodifiableMap(this.providedInterfacesTypes); 
		return ht;
	}

	public Hashtable getRequiredInterfaceTypes() {
		return (Hashtable) Collections.unmodifiableMap(this.requiredInterfacesTypes);
	}*/

	/**
	 * Always throws UnsupportedOperationException, as this is not a composite.
	 */
	public IManager getInternalComponent(String id){ //System.out.println("AManager.getInternalComponent()");
		System.err.println("This is a not a composite component.");
		return null;
		
	}

	/**
	 * Returns false as this is a Manager for an elementary component.
	 */
	public boolean isComposite() { //System.out.println("AManager.isComposite()");
		return false;
	}

	/**
	 * Always throws UnsupportedOperationException, as this is not a composite.
	 */
	public void setInternalComponent(String id, IManager component) { //System.out.println("AManager.setInternalComponent()");
		System.err.println("This is a not a composite component.");
		
	}
}
