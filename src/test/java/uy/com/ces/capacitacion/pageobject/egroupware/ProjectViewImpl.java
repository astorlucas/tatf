package uy.com.ces.capacitacion.pageobject.egroupware;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;

public class ProjectViewImpl implements ProjectView {

	protected MainMenu mainMenu;

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(xpath = "//*[@id=\"divAppbox\"]/table/tbody/tr/td/form")
	protected WebElement containerView;

	@FindBy(xpath = "//*[@id=\"projectmanager.edit.members-tab\"]")
	protected WebElement btnTabMembers;

	@FindBy(id = "projectmanager.edit.members")
	protected WebElement containerMembers;

	protected String title = "eGroupWare [ProjectManager - View project]";

	public ProjectViewImpl(MainMenu mm, DriverManager dm, PageObjectFactory pof) {
		this.mainMenu = mm;

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página, vista de proyecto.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().equalsIgnoreCase(this.title);
	}

	@Override
	public String getViewText() {
		return this.containerView.getText();
	}

	@Override
	public ProjectList goProjects() {

		this.mainMenu.goProjects();

		return this.pageObjectFactory.factoryEgroupwareProjectList(this.driverManager);
	}

	@Override
	public String getMembersText() {

		this.btnTabMembers.click();

		return this.containerMembers.getText();
	}
}
