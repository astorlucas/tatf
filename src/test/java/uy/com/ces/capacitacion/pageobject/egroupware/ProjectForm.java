package uy.com.ces.capacitacion.pageobject.egroupware;

public interface ProjectForm {

	boolean validPage();

	CharSequence getMessageSaved();

	ProjectList goProjects();

	ProjectList createProject(String projectNumber, String projectName, String categoryName, Integer projectPriority);

	ProjectList addResource(String member, Integer availibility, String role);
}
