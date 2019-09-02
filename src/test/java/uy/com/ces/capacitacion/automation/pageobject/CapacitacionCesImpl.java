package uy.com.ces.capacitacion.automation.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;

/**
 * @author Dardo De León
 */
public class CapacitacionCesImpl extends CapacitacionAbstract implements CapacitacionCes {

	protected String selBtnSearch = "searchform_button";
	protected String selInputSearch = "searchform_search";
	protected String selTextOnCoursePage = "maincontent";

	protected By byBtnSearch = By.id(selBtnSearch);
	protected By byInputSearch = By.id(selInputSearch);

	protected By byTextOnCoursePage = By.className(selTextOnCoursePage);

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

		WebElement inputSearch = driver.findElement(byInputSearch);
		inputSearch.click();
		inputSearch.clear();
		inputSearch.sendKeys(textSearch);

		WebElement btnSearch = driver.findElement(byBtnSearch);
		btnSearch.click();
	}

	@Override
	public String getArticleTextOnCoursePage() {

		WebElement textContainer = driver.findElement(byTextOnCoursePage);

		return textContainer.getText();
	}

}
