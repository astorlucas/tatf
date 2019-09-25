package uy.com.ces.capacitacion.pageobject.egroupware;

public interface ProjectView {

	boolean validPage();

	String getViewText();

	ProjectList goProjects();

	String getMembersText();
}
