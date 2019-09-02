package uy.com.ces.capacitacion.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class GoogleGmailImpl extends GoogleAbstract implements GoogleGmail {

	protected long timeOutGeneral = 30;

	protected String selBtnGmalApp = "Gmail";
	protected String selGmailMenuRightTop = "//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a";
	protected String selGmailMenuRightTopBtnLogout = "Cerrar sesión";

	protected By byBtnGmalApp = By.linkText(selBtnGmalApp);
	protected By byGmailMenuRightTop = By.xpath(selGmailMenuRightTop);
	protected By byGmailMenuRightTopBtnLogout = By.partialLinkText(selGmailMenuRightTopBtnLogout);

	public GoogleGmailImpl(DriverManager driverManager) {
		super(driverManager);
	}

	@Override
	public void goGmailApp() {
		WebDriverWait wdw = new WebDriverWait(driver, timeOutGeneral);

		WebElement btnGmalApp = wdw.until(ExpectedConditions.elementToBeClickable(byBtnGmalApp));
		btnGmalApp.click();
	}

	@Override
	public String getTitle() {
		return this.driver.getTitle();
	}

	@Override
	public void logout() {
		WebDriverWait wdw = new WebDriverWait(driver, timeOutGeneral);

		WebElement btnGmalMenuRightTop = wdw.until(ExpectedConditions.elementToBeClickable(byGmailMenuRightTop));
		btnGmalMenuRightTop.click();

		WebElement btnGmailMenuRightTopBtnLogout = driver.findElement(byGmailMenuRightTopBtnLogout);
		btnGmailMenuRightTopBtnLogout.click();
	}
}
