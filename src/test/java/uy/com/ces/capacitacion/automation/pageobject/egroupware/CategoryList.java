package uy.com.ces.capacitacion.automation.pageobject.egroupware;

public interface CategoryList {

	boolean validPage();

	CategoryForm newCategory();

	ProjectList goProjects();

	CategoryList findCategory(String name);
		
	CategoryList delCategory(String categoryName);

	Home logout();
}
