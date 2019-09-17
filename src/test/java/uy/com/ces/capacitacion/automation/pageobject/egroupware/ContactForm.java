package uy.com.ces.capacitacion.automation.pageobject.egroupware;

public interface ContactForm {

	boolean validPage();

	ContactList createContacto(String namePrefix, String nameGiven, String nameMiddle, String nameFamily,
			String nameSuffix, String title, String role, Integer room, String orgName, String orgUnit,
			String adrOneStreet, String adrOnePostalcode, String adrOneCountryname, Integer telWork, Integer telCell,
			Integer telHome) throws IllegalStateException;

	CharSequence getMessageSaved();
}
