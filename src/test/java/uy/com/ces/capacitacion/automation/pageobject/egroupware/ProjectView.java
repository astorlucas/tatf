package uy.com.ces.capacitacion.automation.pageobject.egroupware;

public interface ProjectView {

	boolean validPage();

	String getViewText();

	ProjectList goProjects();

	String getMembersText();
}
