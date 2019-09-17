package uy.com.ces.capacitacion.automation.pageobject.egroupware;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.selenium.DriverManager;

public class CategoryListImpl implements CategoryList {

	protected MainMenu mainMenu;

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(xpath = "//*[@id=\"divAppbox\"]//form[@action=\"/egroupware/index.php?menuaction=preferences.uicategories.edit&cats_app=projectmanager&extra=&global_cats=True&cats_level=True\"]/input[@type=\"submit\"]")
	protected WebElement btnNewCategory;
	
	@FindBy(name = "query")
	protected WebElement inputSearch;
	
	@FindBy(name = "confirm")
	protected WebElement btnConfirmDelCategory;

	/**
	 * eGroupWare [ProjectManager&nbsp;categories for:&nbsp;USERNAME User]
	 */
	protected String title = "categories for";

	public CategoryListImpl(MainMenu mm, DriverManager dm, PageObjectFactory pof) {
		this.mainMenu = mm;

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página, lista de categorías.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().contains(this.title);
	}

	@Override
	public CategoryForm newCategory() {
		this.btnNewCategory.click();

		return this.pageObjectFactory.factoryEgroupwareCategoryForm(this.driverManager);
	}

	@Override
	public ProjectList goProjects() {

		this.mainMenu.goProjects();

		return this.pageObjectFactory.factoryEgroupwareProjectList(this.driverManager);
	}

	@Override
	public CategoryList findCategory(String name) {
		
		this.inputSearch.clear();
		this.inputSearch.sendKeys(name);
		this.inputSearch.sendKeys(Keys.RETURN);
		
		return this;
	}
	
	@Override
	public CategoryList delCategory(String categoryName) {

		String xPathDelCategory = "//*[@id=\"divAppbox\"]//b[text()=\"" + categoryName + "\"]/../../../td[8]/a";

		this.driver.findElement(By.xpath(xPathDelCategory)).click();

		this.btnConfirmDelCategory.click();
		
		return this;
	}

	@Override
	public Home logout() {

		this.mainMenu.logout();

		return this.pageObjectFactory.factoryEgroupwareHome(this.driverManager);
	}
}
