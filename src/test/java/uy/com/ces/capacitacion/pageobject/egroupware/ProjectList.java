package uy.com.ces.capacitacion.pageobject.egroupware;

public interface ProjectList {

	boolean validPage();

	CategoryList goCategories();

	ProjectForm newProject();

	String getTextMainForm();

	void delProject(String projectName);

	ProjectView viewProject(String projectName);

	ProjectForm editProject(String projectName);
}
