package uy.com.ces.capacitacion.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class CapacitacionAbstract {
	protected WebDriver driver;

	protected String selBtnToAccess = "Acceder";
	protected String selInputUserName = "username";
	protected String selInputUserPass = "password";
	protected String selBtnLogIn = "loginbtn";
	protected String selBtnMenu = "(.//*[normalize-space(text()) and normalize-space(.)='Saltar a contenido principal'])[1]/following::span[5]";
	protected String selBtnLogOut = "Salir";

	protected By byBtnToAccess = By.linkText(selBtnToAccess);
	protected By byInputUserName = By.id(selInputUserName);
	protected By byInputUserPass = By.id(selInputUserPass);
	protected By byBtnLoginIn = By.id(selBtnLogIn);
	protected By byBtnMenu = By.xpath(selBtnMenu);
	protected By byBtnLogOut = By.partialLinkText(selBtnLogOut);

	public CapacitacionAbstract(DriverManager driverManager) {
		this.driver = driverManager.factoryDriver();
	}

	public void goHome(String homepage) {
		this.driver.get(homepage);
	}

	public void login(String userName, String userPass) {
		WebElement btnToAccess = driver.findElement(byBtnToAccess);
		btnToAccess.click();

		WebElement inputUserName = driver.findElement(byInputUserName);
		inputUserName.click();
		inputUserName.clear();
		inputUserName.sendKeys(userName);

		WebElement inputUserPass = driver.findElement(byInputUserPass);
		inputUserPass.click();
		inputUserPass.clear();
		inputUserPass.sendKeys(userPass);

		WebElement btnLoginIn = driver.findElement(byBtnLoginIn);
		btnLoginIn.click();
	}

	public void logout() {
		WebElement btnMenu = driver.findElement(byBtnMenu);
		btnMenu.click();

		WebElement btnLogOut = driver.findElement(byBtnLogOut);
		btnLogOut.click();
	}
}
