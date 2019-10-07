package uy.com.ces.capacitacion.pageobject.egroupware;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;

public class ContactListImpl implements ContactList {

	protected MainMenu mainMenu;
	
	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(id = "exec[add]")
	protected WebElement btnAdd;

	@FindBy(xpath = "//*[@id=\"divAppbox\"]/table/tbody/tr/td/form[@name=\"eTemplate\"]")
	protected WebElement mainForm;
	
	@FindBy(xpath = "//table[@class=\"nextmatch_header\"]//input[@id=\"exec[nm][search]\"]")
	protected WebElement inputSearch;

	protected Duration waitPageLoading = Duration.ofSeconds(6);

	/**
	 * Original: `eGroupWare [Addressbook]`
	 * Actual: para ser compatible con `eGroupWare [Addressbook - Search for 'BUSCADO']` 
	 */
	protected String title = "eGroupWare [Addressbook";

	public ContactListImpl(MainMenu mm, DriverManager dm, PageObjectFactory pof) {

		this.mainMenu = mm;

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página lista de contactos.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().startsWith(this.title);
	}

	@Override
	public ContactForm newContacto() {

		this.btnAdd.click();

		return this.pageObjectFactory.factoryEgroupwareContactoForm(this.driverManager);
	}

	@Override
	public String getTextMainForm() {
		
		this.driverManager.stop(waitPageLoading);
		
		return this.mainForm.getText();
	}

	@Override
	public ContactList findContacto(String namePrefix) {
		
		this.inputSearch.clear();
		this.inputSearch.sendKeys(namePrefix);
		this.inputSearch.sendKeys(Keys.RETURN);
		
		return this;
	}

	@Override
	public ContactList delContacto(String nameFamily) {

		String xPathDelContacto = "//*[@id=\"divAppbox\"]//span[contains(text(), \"" + nameFamily + "\")]/../../../../../../td[6]//img[@title=\"Delete\"]";
		
		this.driver.findElement(By.xpath(xPathDelContacto)).click();

		this.driver.switchTo().alert().accept();
		
		return this;
	}

	@Override
	public ContactView viewContacto(String nameFamily) {

		String xPathViewContacto = "//*[@id=\"divAppbox\"]//span[contains(text(), \"" + nameFamily + "\")]/../../../../../../td[6]//img[@title=\"View\"]";
		
		this.driver.findElement(By.xpath(xPathViewContacto)).click();
		
		return this.pageObjectFactory.factoryEgroupwareContactoView(this.driverManager);
	}
	
	@Override
	public Home logout() {

		this.mainMenu.logout();

		return this.pageObjectFactory.factoryEgroupwareHome(this.driverManager);
	}

}
