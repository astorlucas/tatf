package uy.com.ces.capacitacion.automation.pageobject.egroupware;

public interface Dashboard {

	boolean validPage();

	ProjectList goProjects();

	ContactList goContactos();
	
	Home logout();
}
