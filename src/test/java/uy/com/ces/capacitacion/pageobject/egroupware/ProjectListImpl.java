package uy.com.ces.capacitacion.pageobject.egroupware;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;

public class ProjectListImpl implements ProjectList {

	protected MainMenu mainMenu;

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(xpath = "//a[@href=\"/egroupware/index.php?menuaction=preferences.uicategories.index&cats_app=projectmanager&cats_level=True&global_cats=True\"]")
	protected WebElement linkCategories;

	@FindBy(id = "exec[add]")
	protected WebElement linkNewProject;

	@FindBy(xpath = "//*[@id=\"divAppbox\"]/table/tbody/tr/td/form[@name=\"eTemplate\"]")
	protected WebElement mainForm;

	protected String btnGridPrefix = "//*[@id=\"divAppbox\"]//a/span[text()=\"";
	protected String btnGridSuffixEdit = "\"]/../../../td[11]//img[@title=\"Edit\"]";
	protected String btnGridSuffixView = "\"]/../../../td[11]//img[@title=\"View\"]";
	protected String btnGridSuffixDelete = "\"]/../../../td[11]//img[@title=\"Delete\"]";
	
	protected String title = "eGroupWare [ProjectManager - Projectlist]";

	public ProjectListImpl(MainMenu mm, DriverManager dm, PageObjectFactory pof) {
		this.mainMenu = mm;

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página de proyectos.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().equalsIgnoreCase(this.title);
	}

	@Override
	public CategoryList goCategories() {
		
		this.linkCategories.click();

		return this.pageObjectFactory.factoryEgroupwareCategoryList(this.driverManager);
	}

	@Override
	public ProjectForm newProject() {

		this.linkNewProject.click();

		return this.pageObjectFactory.factoryEgroupwareProjectForm(this.driverManager);
	}

	@Override
	public String getTextMainForm() {
		return this.mainForm.getText();
	}
	
	@Override
	public void delProject(String projectName) {
		
		String xPathDelProject = this.btnGridPrefix + projectName + this.btnGridSuffixDelete;

		driver.findElement(By.xpath(xPathDelProject)).click();

		driver.switchTo().alert().accept();
	}

	@Override
	public ProjectView viewProject(String projectName) {

		String xPathViewProject = this.btnGridPrefix + projectName + this.btnGridSuffixView;
		
		driver.findElement(By.xpath(xPathViewProject)).click();

		return this.pageObjectFactory.factoryEgroupwareProjectView(this.driverManager);
	}

	@Override
	public ProjectForm editProject(String projectName) {

		String xPathEditProject = this.btnGridPrefix + projectName + this.btnGridSuffixEdit;
		
		driver.findElement(By.xpath(xPathEditProject)).click();

		return this.pageObjectFactory.factoryEgroupwareProjectForm(this.driverManager);
	}

}
