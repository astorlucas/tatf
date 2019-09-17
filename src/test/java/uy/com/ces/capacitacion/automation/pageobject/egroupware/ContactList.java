package uy.com.ces.capacitacion.automation.pageobject.egroupware;

public interface ContactList {

	boolean validPage();

	ContactForm newContacto();

	ContactList findContacto(String namePrefix);
	
	ContactList delContacto(String nameFamily);

	String getTextMainForm();

	Home logout();
}
