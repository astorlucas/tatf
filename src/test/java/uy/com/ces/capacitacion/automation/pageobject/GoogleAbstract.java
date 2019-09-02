package uy.com.ces.capacitacion.automation.pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	protected String selBtnLogin = "gb_70";
	protected String selInputUserName = "identifierId";
	protected String selInputUserCredential = "password";

	protected By byInputUserName = By.id(selInputUserName);
	protected By byInputUserCredential = By.id(selInputUserCredential);
	protected By byBtnLogin = By.id(selBtnLogin);

	public GoogleAbstract(DriverManager driverManager) {
		this.driver = driverManager.factoryDriver();
	}

	public void goHome(String homepage) {
		this.driver.get(homepage);
	}

	public void login(String userName, String userPass) {
		WebElement btnLogin = driver.findElement(byBtnLogin);
		btnLogin.click();

		Wait<WebDriver> fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class);

		WebElement inputUserName = fw.until(ExpectedConditions.elementToBeClickable(byInputUserName));

		Actions actionUser = new Actions(driver);
		actionUser.moveToElement(inputUserName);
		actionUser.sendKeys(userName);
		actionUser.sendKeys(Keys.RETURN);
		actionUser.build().perform();

		WebElement inputPassword = fw.until(ExpectedConditions.elementToBeClickable(byInputUserCredential));
		Actions actionPass = new Actions(driver);
		actionPass.moveToElement(inputPassword);
		actionPass.sendKeys(userPass);
		actionPass.sendKeys(Keys.RETURN);
		actionPass.build().perform();
	}
}
