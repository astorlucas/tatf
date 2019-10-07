package uy.com.ces.capacitacion.pageobject.egroupware;

public interface ContactList {

	boolean validPage();

	ContactForm newContacto();

	ContactList findContacto(String namePrefix);

	ContactView viewContacto(String nameFamily);
	
	ContactList delContacto(String nameFamily);

	String getTextMainForm();

	Home logout();
}
