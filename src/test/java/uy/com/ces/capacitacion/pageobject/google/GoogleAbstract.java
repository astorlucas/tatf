package uy.com.ces.capacitacion.pageobject.google;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public abstract class GoogleAbstract {

	protected DriverManager driverManager;
	
	protected WebDriver driver;

	protected Duration timeOutClick = Duration.ofSeconds(30);
	protected Duration pollingClick = Duration.ofSeconds(5);

	@FindBy(id = "gb_70")
	public WebElement btnLogin;

	@FindBy(id = "identifierId")
	public WebElement inputUserName;

	@FindBy(id = "password")
	public WebElement inputPassword;

	public GoogleAbstract(DriverManager dm) {
		this.driverManager = dm;
		
		this.driver = this.driverManager.factoryDriver();
	}

	public void goHome(String homepage) {
		this.driver.get(homepage);
	}

	public void login(String userName, String userPass) {

		this.btnLogin.click();

		this.inputUserName = this.driverManager.fluentWaitToBeClickable(this.inputUserName, this.timeOutClick, this.pollingClick);

		Actions actionUser = new Actions(this.driver);
		actionUser.moveToElement(this.inputUserName);
		actionUser.sendKeys(userName);
		actionUser.sendKeys(Keys.RETURN);
		actionUser.build().perform();

		this.inputPassword = this.driverManager.fluentWaitToBeClickable(this.inputPassword, this.timeOutClick, this.pollingClick);
		
		Actions actionPass = new Actions(this.driver);
		actionPass.moveToElement(this.inputPassword);
		actionPass.sendKeys(userPass);
		actionPass.sendKeys(Keys.RETURN);
		actionPass.build().perform();
	}
}
