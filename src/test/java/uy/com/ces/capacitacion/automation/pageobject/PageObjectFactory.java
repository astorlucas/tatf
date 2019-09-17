package uy.com.ces.capacitacion.automation.pageobject;

import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Dashboard;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Home;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectView;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleGmail;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleSearch;
import uy.com.ces.capacitacion.automation.pageobject.moodle.CesCapacitacion;
import uy.com.ces.capacitacion.automation.selenium.DriverManager;

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










