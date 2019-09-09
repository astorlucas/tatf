package uy.com.ces.capacitacion.automation.pageobject.google;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class GoogleGmailImpl extends GoogleAbstract implements GoogleGmail {

	protected long timeOutGeneral = 30;
	protected WebDriverWait wdw;

	@FindBy(linkText = "Gmail")
	public WebElement btnGmalApp;

	@FindBy(xpath = "//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a")
	public WebElement btnGmalMenuRightTop;

	@FindBy(partialLinkText = "Cerrar sesión")
	public WebElement btnGmailMenuRightTopBtnLogout;

	public GoogleGmailImpl(DriverManager driverManager) {
		super(driverManager);

		wdw = new WebDriverWait(this.driver, this.timeOutGeneral);
	}

	@Override
	public void goGmailApp() {
		this.btnGmalApp = wdw.until(ExpectedConditions.elementToBeClickable(this.btnGmalApp));
		this.btnGmalApp.click();
	}

	@Override
	public String getTitle() {
		return this.driver.getTitle();
	}

	@Override
	public void logout() {
		
		this.btnGmalMenuRightTop = wdw.until(ExpectedConditions.elementToBeClickable(this.btnGmalMenuRightTop));
		
		this.btnGmalMenuRightTop.click();

		this.btnGmailMenuRightTopBtnLogout.click();
	}
}
