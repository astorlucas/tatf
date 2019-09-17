package uy.com.ces.capacitacion.automation.pageobject;

import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryFormImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.CategoryListImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactFormImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ContactListImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Dashboard;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.DashboardImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.Home;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.HomeImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.MainMenu;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.MainMenuImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectForm;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectFormImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectList;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectListImpl;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectView;
import uy.com.ces.capacitacion.automation.pageobject.egroupware.ProjectViewImpl;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleGmail;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleGmailImpl;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleSearch;
import uy.com.ces.capacitacion.automation.pageobject.google.GoogleSearchImpl;
import uy.com.ces.capacitacion.automation.pageobject.moodle.CapacitacionCesImpl;
import uy.com.ces.capacitacion.automation.pageobject.moodle.CesCapacitacion;
import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class PageObjectFactoryImpl implements PageObjectFactory {

	protected MainMenuImpl mainMenu = null;

	public MainMenu getMainMenu(DriverManager dm) {

		if (this.mainMenu == null) {
			this.mainMenu = new MainMenuImpl(dm);
		} else {
			this.mainMenu.setDriverManager(dm);
		}

		return this.mainMenu;
	}

	@Override
	public CesCapacitacion factoryCesCapacitacion(DriverManager driverManager) {
		return driverManager.factoryElements(new CapacitacionCesImpl(driverManager));
	}

	@Override
	public GoogleGmail factoryGoogleGmail(DriverManager driverManager) {
		return driverManager.factoryElements(new GoogleGmailImpl(driverManager));
	}

	@Override
	public GoogleSearch factoryGoogleSearch(DriverManager driverManager) {
		return driverManager.factoryElements(new GoogleSearchImpl(driverManager));
	}

	@Override
	public Home factoryEgroupwareHome(DriverManager driverManager) {
		return driverManager.factoryElements(new HomeImpl(driverManager, this));
	}

	@Override
	public Dashboard factoryEgroupwareDashboard(DriverManager driverManager) {
		return driverManager.factoryElements(new DashboardImpl(this.getMainMenu(driverManager), driverManager, this));
	}

	@Override
	public ProjectList factoryEgroupwareProjectList(DriverManager driverManager) {
		return driverManager.factoryElements(new ProjectListImpl(this.getMainMenu(driverManager), driverManager, this));
	}

	@Override
	public CategoryList factoryEgroupwareCategoryList(DriverManager driverManager) {
		return driverManager
				.factoryElements(new CategoryListImpl(this.getMainMenu(driverManager), driverManager, this));
	}

	@Override
	public CategoryForm factoryEgroupwareCategoryForm(DriverManager driverManager) {
		return driverManager.factoryElements(new CategoryFormImpl(driverManager, this));
	}

	@Override
	public ProjectForm factoryEgroupwareProjectForm(DriverManager driverManager) {
		return driverManager.factoryElements(new ProjectFormImpl(driverManager, this));
	}

	@Override
	public ProjectView factoryEgroupwareProjectView(DriverManager driverManager) {
		return driverManager.factoryElements(new ProjectViewImpl(this.getMainMenu(driverManager), driverManager, this));
	}

	@Override
	public ContactList factoryEgroupwareContactoList(DriverManager driverManager) {
		return driverManager
				.factoryElements(new ContactListImpl(this.getMainMenu(driverManager), driverManager, this));
	}

	@Override
	public ContactForm factoryEgroupwareContactoForm(DriverManager driverManager) {
		return driverManager.factoryElements(new ContactFormImpl(driverManager, this));
	}
}
