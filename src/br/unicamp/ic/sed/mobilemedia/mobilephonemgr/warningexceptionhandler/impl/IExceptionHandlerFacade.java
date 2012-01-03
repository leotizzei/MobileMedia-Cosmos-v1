   
package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.impl;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.warningexceptionhandler.spec.prov.IExceptionsHandlerCtr;

class IExceptionHandlerFacade implements IExceptionsHandlerCtr{

		
	public void handle(Exception exception) { //System.out.println("IExceptionHandlerFacade.handle()");
		WarningExceptionHandler handler = new WarningExceptionHandler();
		handler.handle(exception);		
	}
}