package uy.com.ces.capacitacion.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class GoogleSearchImpl extends GoogleAbstract implements GoogleSearch {

	String selInputQuestion = "q";

	By byInputQuestion = By.name(selInputQuestion);

	public GoogleSearchImpl(DriverManager driverManager) {
		super(driverManager);
	}

	public void searcHome(String texto) {
		WebElement inputQuestion = driver.findElement(byInputQuestion);

		inputQuestion.click();
		inputQuestion.clear();
		inputQuestion.sendKeys(texto);
		inputQuestion.sendKeys(Keys.ENTER);
	}

	public String getTitle() {
		return this.driver.getTitle();
	}
}
