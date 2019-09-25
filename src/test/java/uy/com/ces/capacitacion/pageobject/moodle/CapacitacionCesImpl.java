package uy.com.ces.capacitacion.pageobject.moodle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class CapacitacionCesImpl extends CapacitacionAbstract implements CesCapacitacion {

	@FindBy(id = "searchform_search")
	protected WebElement inputSearch;
	
	@FindBy(id = "searchform_button")
	protected WebElement btnSearch;

	@FindBy(className = "maincontent")
	protected WebElement textContainer;

	public CapacitacionCesImpl(DriverManager driverManager) {
		super(driverManager);
	}

	@Override
	public void accessACourse(String textLinkCourse) {

		By byCuorseTitle = By.linkText(textLinkCourse);

		WebElement cuorseTitle = driver.findElement(byCuorseTitle);

		cuorseTitle.click();
	}

	@Override
	public void searchOnCoursePage(String textSearch) {

		this.inputSearch.click();
		this.inputSearch.clear();
		this.inputSearch.sendKeys(textSearch);

		this.btnSearch.click();
	}

	@Override
	public String getArticleTextOnCoursePage() {
		return this.textContainer.getText();
	}

}
