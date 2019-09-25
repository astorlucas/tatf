package uy.com.ces.capacitacion.pageobject.egroupware;

import org.openqa.selenium.WebDriver;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;

public class DashboardImpl implements Dashboard {

	protected MainMenu mainMenu;

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	protected String title = "eGroupWare [Home]";

	public DashboardImpl(MainMenu mm, DriverManager dm, PageObjectFactory pof) {
		this.mainMenu = mm;

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página dashboard.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().equalsIgnoreCase(this.title);
	}

	@Override
	public ProjectList goProjects() {

		this.mainMenu.goProjectManager();

		return this.pageObjectFactory.factoryEgroupwareProjectList(this.driverManager);
	}

	@Override
	public ContactList goContactos() {
		
		this.mainMenu.goContactos();
		
		return this.pageObjectFactory.factoryEgroupwareContactoList(this.driverManager);
	}

	@Override
	public Home logout() {

		this.mainMenu.logout();

		return this.pageObjectFactory.factoryEgroupwareHome(this.driverManager);
	}
}
