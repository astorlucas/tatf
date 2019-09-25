package uy.com.ces.capacitacion.pageobject.moodle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class CapacitacionAbstract {
	
	protected WebDriver driver;

	@FindBy(linkText = "Acceder")
	public WebElement btnToAccess;

	@FindBy(id = "username")
	public WebElement inputUserName;

	@FindBy(id = "password")
	public WebElement inputUserPass;

	@FindBy(id = "loginbtn")
	public WebElement btnLoginIn;

	@FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Saltar a contenido principal'])[1]/following::span[5]")
	public WebElement btnMenu;

	@FindBy(partialLinkText = "Salir")
	public WebElement btnLogOut;

	public CapacitacionAbstract(DriverManager driverManager) {
		this.driver = driverManager.factoryDriver();
	}

	public void goHome(String homepage) {
		this.driver.get(homepage);
	}

	public void login(String userName, String userPass) {
		this.btnToAccess.click();

		this.inputUserName.click();
		this.inputUserName.clear();
		this.inputUserName.sendKeys(userName);

		this.inputUserPass.click();
		this.inputUserPass.clear();
		this.inputUserPass.sendKeys(userPass);

		this.btnLoginIn.click();
	}

	public void logout() {

		this.btnMenu.click();

		this.btnLogOut.click();
	}
}
