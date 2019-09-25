package uy.com.ces.capacitacion.pageobject.egroupware;

public interface Dashboard {

	boolean validPage();

	ProjectList goProjects();

	ContactList goContactos();
	
	Home logout();
}
