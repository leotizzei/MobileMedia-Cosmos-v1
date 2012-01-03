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

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;

/**
 * Abstract implementation of class Manager for composite components. 
 * 
 * @author Leonel Aguilar Gayard (leonel.gayard@gmail.com)
 *
 */
public abstract class AManagerComposite extends AManager implements IManager {
	private Hashtable internalComponents;
	protected AManagerComposite() {
		super(); //System.out.println("AManagerComposite.AManagerComposite()");
		this.internalComponents = new Hashtable();
	}

	public void setInternalComponent(String id, IManager manager) { //System.out.println("AManagerComposite.setInternalComponent()");
		this.internalComponents.put(id, manager);
	}

	public IManager getInternalComponent(String id) { //System.out.println("AManagerComposite.getInternalComponent()");
		//System.out.println("AManageComposite.getInternalComponent("+id+")");
		IManager manager = (IManager) this.internalComponents.get(id);
		if (manager == null) {
			System.err.println("Error! This is not a composite component");
		}
		return manager;
	}
}
