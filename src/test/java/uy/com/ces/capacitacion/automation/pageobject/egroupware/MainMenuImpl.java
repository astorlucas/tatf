package uy.com.ces.capacitacion.automation.pageobject.egroupware;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

public class MainMenuImpl implements MainMenu {
		
	protected static final By xpathLinkLogout = By.xpath("//*[@id=\"topmenu_items\"]/div//a[@href=\"/egroupware/logout.php\"]");

	protected static final By xpathLinkProjectList = By.xpath("//a[@href=\"/egroupware/index.php?menuaction=projectmanager.uiprojectmanager.index\"]");
	
	protected static final By xpathLinkProjectManagerList = By.xpath("//a[@href=\"/egroupware/projectmanager/index.php\"]");
	
	protected static final By xpathLinkContactList = By.xpath("//*[@id=\"divAppIconBar\"]//a[@href=\"/egroupware/addressbook/index.php\"]");

	protected WebDriver driver;

	public MainMenuImpl(DriverManager dm) {
		this.setDriverManager(dm);
	}

	public void setDriverManager(DriverManager dm) {
		this.driver = dm.factoryDriver();
	}
	
	@Override
	public void goProjects() {
		this.driver.findElement(xpathLinkProjectList).click();
	}

	@Override
	public void goProjectManager() {
		this.driver.findElement(xpathLinkProjectManagerList).click();
	}
	
	@Override
	public void logout() {
		this.driver.findElement(xpathLinkLogout).click();
	}

	@Override
	public void goContactos() {
		this.driver.findElement(xpathLinkContactList).click();
	}
}
