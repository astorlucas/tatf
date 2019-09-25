package uy.com.ces.capacitacion.pageobject;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;
import uy.com.ces.capacitacion.pageobject.egroupware.CategoryForm;
import uy.com.ces.capacitacion.pageobject.egroupware.CategoryList;
import uy.com.ces.capacitacion.pageobject.egroupware.ContactForm;
import uy.com.ces.capacitacion.pageobject.egroupware.ContactList;
import uy.com.ces.capacitacion.pageobject.egroupware.Dashboard;
import uy.com.ces.capacitacion.pageobject.egroupware.Home;
import uy.com.ces.capacitacion.pageobject.egroupware.ProjectForm;
import uy.com.ces.capacitacion.pageobject.egroupware.ProjectList;
import uy.com.ces.capacitacion.pageobject.egroupware.ProjectView;
import uy.com.ces.capacitacion.pageobject.google.GoogleGmail;
import uy.com.ces.capacitacion.pageobject.google.GoogleSearch;
import uy.com.ces.capacitacion.pageobject.moodle.CesCapacitacion;

/**
 * @author Dardo De León
 */
public interface PageObjectFactory {
	
	CesCapacitacion factoryCesCapacitacion(DriverManager driverManager);

	GoogleGmail factoryGoogleGmail(DriverManager driverManager);

	GoogleSearch factoryGoogleSearch(DriverManager driverManager);

	Home factoryEgroupwareHome(DriverManager driverManager);
	
	Dashboard factoryEgroupwareDashboard(DriverManager driverManager);

	CategoryList factoryEgroupwareCategoryList(DriverManager driverManager);

	CategoryForm factoryEgroupwareCategoryForm(DriverManager driverManager);

	ProjectList factoryEgroupwareProjectList(DriverManager driverManager);

	ProjectForm factoryEgroupwareProjectForm(DriverManager driverManager);
	
	ProjectView factoryEgroupwareProjectView(DriverManager driverManager);

	ContactList factoryEgroupwareContactoList(DriverManager driverManager);

	ContactForm factoryEgroupwareContactoForm(DriverManager driverManager);
}
