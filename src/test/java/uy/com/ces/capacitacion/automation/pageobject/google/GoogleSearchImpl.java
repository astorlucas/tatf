package uy.com.ces.capacitacion.automation.pageobject.google;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class GoogleSearchImpl extends GoogleAbstract implements GoogleSearch {

	@FindBy(name = "q")
	public WebElement inputQuestion;
	
	protected Duration waitPageLoading = Duration.ofSeconds(5);

	public GoogleSearchImpl(DriverManager driverManager) {
		super(driverManager);
	}

	public void searcHome(String texto) {
		this.inputQuestion.click();
		this.inputQuestion.clear();
		this.inputQuestion.sendKeys(texto);
		this.inputQuestion.sendKeys(Keys.ENTER);
	}

	public String getTitle() {
		
		this.driverManager.stop(waitPageLoading);
		
		return this.driver.getTitle();
	}
}
