package uy.com.ces.capacitacion.automation.pageobject.google;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public abstract class GoogleAbstract {

	protected WebDriver driver;

	protected long timeOut = 30;
	protected long pollingEvery = 5;
	protected Wait<WebDriver> fw;

	@FindBy(id = "gb_70")
	public WebElement btnLogin;

	@FindBy(id = "identifierId")
	public WebElement inputUserName;

	@FindBy(id = "password")
	public WebElement inputPassword;

	public GoogleAbstract(DriverManager driverManager) {
		this.driver = driverManager.factoryDriver();

		this.fw = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingEvery))
				.ignoring(NoSuchElementException.class);
	}

	public void goHome(String homepage) {
		this.driver.get(homepage);
	}

	public void login(String userName, String userPass) {

		this.btnLogin.click();

		this.inputUserName = this.fw.until(ExpectedConditions.elementToBeClickable(this.inputUserName));

		Actions actionUser = new Actions(this.driver);
		actionUser.moveToElement(this.inputUserName);
		actionUser.sendKeys(userName);
		actionUser.sendKeys(Keys.RETURN);
		actionUser.build().perform();

		this.inputPassword = this.fw.until(ExpectedConditions.elementToBeClickable(this.inputPassword));
		
		Actions actionPass = new Actions(this.driver);
		actionPass.moveToElement(this.inputPassword);
		actionPass.sendKeys(userPass);
		actionPass.sendKeys(Keys.RETURN);
		actionPass.build().perform();
	}
}
