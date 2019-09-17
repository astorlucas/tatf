package uy.com.ces.capacitacion.automation.pageobject.egroupware;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.selenium.DriverManager;

public class CategoryFormImpl implements CategoryForm {
	
	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(name = "cat_name")
	protected WebElement inputName;

	@FindBy(name = "cat_description")
	protected WebElement textareaDescription;

	@FindBy(id = "cat_data_color_colorpicker")
	protected WebElement inputColor;

	@FindBy(name = "cat_data[icon]")
	protected WebElement selectType;

	@FindBy(name = "save")
	protected WebElement btnSave;

	protected long timeOut = 30;
	protected long pollingEvery = 10;
	protected Wait<WebDriver> fw;

	/**
	 * eGroupWare [Add ProjectManager category for: USERNAME User]
	 */
	protected String title = "eGroupWare [Add ProjectManager category for";

	public CategoryFormImpl(DriverManager dm, PageObjectFactory pof) {

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();
		
		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página agregar categoría.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().contains(this.title);
	}

	@Override
	public CategoryList createCategory(String name, String description, String color, String type) {

		this.driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);

		this.inputName.clear();
		this.inputName.sendKeys(name);

		this.textareaDescription.clear();
		this.textareaDescription.sendKeys(description);

		this.inputColor.clear();
		this.inputColor.sendKeys(color);

		this.fw = new FluentWait<>(this.driver)
				.withTimeout(Duration.ofSeconds(this.timeOut))
				.pollingEvery(Duration.ofSeconds(this.pollingEvery))
				.ignoring(NoSuchElementException.class);
		this.selectType = this.fw.until(ExpectedConditions.elementToBeClickable(this.selectType));
		new Select(this.selectType).selectByVisibleText(type);
		this.selectType.click();

		this.btnSave.click();

		return this.pageObjectFactory.factoryEgroupwareCategoryList(this.driverManager);
	}
}
