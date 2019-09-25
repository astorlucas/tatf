package uy.com.ces.capacitacion.pageobject.google;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class GoogleGmailImpl extends GoogleAbstract implements GoogleGmail {

	protected Duration waitPageLoading = Duration.ofSeconds(10);

	@FindBy(linkText = "Gmail")
	public WebElement btnGmalApp;

	@FindBy(xpath = "//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a")
	public WebElement btnGmalMenuRightTop;

	@FindBy(partialLinkText = "Cerrar sesión")
	public WebElement btnGmailMenuRightTopBtnLogout;

	public GoogleGmailImpl(DriverManager driverManager) {
		super(driverManager);

		this.driverManager.stop(waitPageLoading);
	}

	@Override
	public void goGmailApp() {

		this.btnGmalApp = this.driverManager.fluentWaitToBeClickable(
				this.btnGmalApp, 
				this.timeOutClick,
				this.pollingClick);

		this.btnGmalApp.click();
	}

	@Override
	public String getTitle() {

		this.driverManager.stop(waitPageLoading);
		
		return this.driver.getTitle();
	}

	@Override
	public void logout() {

		this.btnGmalMenuRightTop = this.driverManager.fluentWaitToBeClickable(
				this.btnGmalMenuRightTop,
				this.timeOutClick, 
				this.pollingClick);

		this.btnGmalMenuRightTop.click();

		this.btnGmailMenuRightTopBtnLogout.click();
	}
}
