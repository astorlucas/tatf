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

public class ContactFormImpl implements ContactForm {

	protected static final CharSequence MESSAGE_SAVED = "Contact saved";

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(id = "exec[n_fn]")
	protected WebElement inputName;

	@FindBy(id = "exec[n_prefix]")
	protected WebElement inputNamePrefix;

	@FindBy(id = "exec[n_given]")
	protected WebElement inputNameGiven;

	@FindBy(id = "exec[n_middle]")
	protected WebElement inputNameMiddle;

	@FindBy(id = "exec[n_family]")
	protected WebElement inputNameFamily;

	@FindBy(id = "exec[n_suffix]")
	protected WebElement inputNameSuffix;

	@FindBy(xpath = "//*[@id=\"addressbook.edit.general\"]//table[@class=\"editname\"]//*[@id=\"exec[]\"]")
	protected WebElement btnConfirmName;

	@FindBy(id = "exec[title]")
	protected WebElement inputTitle;

	@FindBy(id = "exec[role]")
	protected WebElement inputRole;

	@FindBy(id = "exec[room]")
	protected WebElement inputRoom;

	@FindBy(id = "exec[org_name]")
	protected WebElement inputOrgName;

	@FindBy(id = "exec[org_unit]")
	protected WebElement inputOrgUnit;

	@FindBy(id = "exec[adr_one_street]")
	protected WebElement inputAddrOneStreet;

	@FindBy(id = "exec[adr_one_postalcode]")
	protected WebElement inputAddrOnePostalCode;

	@FindBy(id = "exec[adr_one_countryname]")
	protected WebElement selectAddrOneCountryName;

	@FindBy(id = "exec[tel_work]")
	protected WebElement inputTelWork;

	@FindBy(id = "exec[tel_cell]")
	protected WebElement inputTelCell;

	@FindBy(id = "exec[tel_home]")
	protected WebElement inputTelHome;

	@FindBy(id = "exec[button][save]")
	protected WebElement btnSave;

	protected long timeOut = 30;
	protected long pollingEvery = 5;
	protected Wait<WebDriver> fw;

	/**
	 * eGroupWare [Add ProjectManager category for: USERNAME User]
	 */
	protected String title = "eGroupWare [Addressbook]";

	public ContactFormImpl(DriverManager dm, PageObjectFactory pof) {

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		this.fw = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingEvery))
				.ignoring(NoSuchElementException.class);
	}

	@Override
	public CharSequence getMessageSaved() {
		return MESSAGE_SAVED;
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().equalsIgnoreCase(this.title);
	}

	@Override
	public ContactList createContacto(String namePrefix, String nameGiven, String nameMiddle, String nameFamily,
			String nameSuffix, String title, String role, Integer room, String orgName, String orgUnit,
			String adrOneStreet, String adrOnePostalcode, String adrOneCountryname, Integer telWork, Integer telCell,
			Integer telHome) {

		String winHandle = this.driver.getWindowHandle();

		String popHandle = this.driverManager.getPopHandle(winHandle, this.driver.getWindowHandles());

		this.driver.switchTo().window(popHandle);

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página agregar contacto.");
		}

		this.inputName.click();

		this.inputNamePrefix.clear();
		this.inputNamePrefix.sendKeys(namePrefix);

		this.inputNameGiven.clear();
		this.inputNameGiven.sendKeys(nameGiven);

		this.inputNameMiddle.clear();
		this.inputNameMiddle.sendKeys(nameMiddle);

		this.inputNameFamily.clear();
		this.inputNameFamily.sendKeys(nameFamily);

		this.inputNameSuffix.clear();
		this.inputNameSuffix.sendKeys(nameSuffix);

		this.btnConfirmName.click();

		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);

		this.inputRole.clear();
		this.inputRole.sendKeys(role);

		this.inputRoom.clear();
		this.inputRoom.sendKeys(room.toString());

		this.inputOrgName.clear();
		this.inputOrgName.sendKeys(orgName);

		this.inputOrgUnit.clear();
		this.inputOrgUnit.sendKeys(orgUnit);

		this.inputAddrOneStreet.clear();
		this.inputAddrOneStreet.sendKeys(adrOneStreet);

		this.inputAddrOnePostalCode.clear();
		this.inputAddrOnePostalCode.sendKeys(adrOnePostalcode);

		this.selectAddrOneCountryName = this.fw.until(ExpectedConditions.elementToBeClickable(this.selectAddrOneCountryName));
		new Select(this.selectAddrOneCountryName).selectByVisibleText(adrOneCountryname);
		this.selectAddrOneCountryName.click();

		this.inputTelWork.clear();
		this.inputTelWork.sendKeys(telWork.toString());

		this.inputTelCell.clear();
		this.inputTelCell.sendKeys(telCell.toString());

		this.inputTelHome.clear();
		this.inputTelHome.sendKeys(telHome.toString());

		this.btnSave.click();

		this.driver.switchTo().window(winHandle);

		return this.pageObjectFactory.factoryEgroupwareContactoList(this.driverManager);
	}
}
