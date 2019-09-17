package uy.com.ces.capacitacion.automation.pageobject.egroupware;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.selenium.DriverManager;

public class ProjectFormImpl implements ProjectForm {

	protected static final CharSequence MESSAGE_SAVED = "Project saved";

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(id = "exec[pm_number]")
	protected WebElement inputNumber;

	@FindBy(id = "exec[pm_title]")
	protected WebElement inputTitle;

	@FindBy(id = "exec[cat_id]")
	protected WebElement selectCategory;

	@FindBy(id = "exec[pm_priority]")
	protected WebElement selectPriority;

	@FindBy(id = "projectmanager.edit.members-tab")
	protected WebElement btnTabMembers;

	@FindBy(id = "eT_accountsel_exec_member_1")
	protected WebElement selectMember;

	@FindBy(id = "exec[availibility][1]")
	protected WebElement inputMemberAvailibility;

	@FindBy(id = "exec[role][1]")
	protected WebElement selectMemberRole;

	@FindBy(id = "exec[add]")
	protected WebElement btnAddMember;

	@FindBy(id = "exec[save]")
	protected WebElement btnSave;

	@FindBy(xpath = "//a[@href=\"/egroupware/projectmanager/index.php\"]")
	protected WebElement linkProjects;

	protected String titleEdit = "eGroupWare [ProjectManager - Edit project]";
	protected String titleCreate = "eGroupWare [ProjectManager - Add project]";

	protected long timeOut = 30;
	protected long pollingEvery = 5;
	protected Wait<WebDriver> fw;

	public ProjectFormImpl(DriverManager dm, PageObjectFactory pof) {

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		this.fw = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class);

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la p�gina de alta de proyecto.");
		}
	}

	@Override
	public CharSequence getMessageSaved() {
		return MESSAGE_SAVED;
	}
	
	@Override
	public boolean validPage() {
		return this.driver.getTitle().equalsIgnoreCase(this.titleCreate)
				|| this.driver.getTitle().equalsIgnoreCase(this.titleEdit);
	}

	@Override
	public ProjectList createProject(String projectNumber, String projectName, String categoryName,
			Integer projectPriority) {

		this.inputNumber.clear();
		this.inputNumber.sendKeys(projectNumber);

		this.inputTitle.clear();
		this.inputTitle.sendKeys(projectName);

		this.selectCategory = this.fw.until(ExpectedConditions.elementToBeClickable(this.selectCategory));
		new Select(this.selectCategory).selectByVisibleText(categoryName);
		this.selectPriority.click();

		this.selectPriority = this.fw.until(ExpectedConditions.elementToBeClickable(this.selectPriority));
		new Select(this.selectPriority).selectByVisibleText(projectPriority.toString());
		this.selectPriority.click();

		return this.save();
	}

	@Override
	public ProjectList addResource(String member, Integer availibility, String role) {

		this.btnTabMembers.click();

		this.selectMember = this.fw.until(ExpectedConditions.elementToBeClickable(this.selectMember));
		new Select(this.selectMember).selectByVisibleText(member);
		this.selectMember.click();

		this.inputMemberAvailibility.clear();
		this.inputMemberAvailibility.sendKeys(availibility.toString());

		this.selectMemberRole = this.fw.until(ExpectedConditions.elementToBeClickable(this.selectMemberRole));
		new Select(this.selectMemberRole).selectByVisibleText(role);
		this.selectMemberRole.click();

		this.btnAddMember.click();

		return this.save();
	}

	@Override
	public ProjectList goProjects() {

		this.linkProjects.click();

		return this.pageObjectFactory.factoryEgroupwareProjectList(this.driverManager);
	}

	public ProjectList save() {

		this.btnSave.click();

		return this.pageObjectFactory.factoryEgroupwareProjectList(this.driverManager);
	}
}
