package uy.com.ces.capacitacion.pageobject.egroupware;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.selenium.DriverManager;
import uy.com.ces.capacitacion.pageobject.PageObjectFactory;

public class ContactViewImpl implements ContactView {

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(xpath = "//*[@id=\"divAppbox\"]/table/tbody/tr/td/form[@name=\"eTemplate\"]")
	protected WebElement containerView;
	
	@FindBy(xpath = "//div[@id=\"divAppbox\"]//input[@id=\"exec[button][cancel]\"]")
	protected WebElement btnCancel;

	protected String title = "eGroupWare [Addressbook]";

	public ContactViewImpl(DriverManager dm, PageObjectFactory pof) {
	
		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();

		if (!this.validPage()) {
			throw new IllegalStateException("No se encuentra en la página, vista de contacto.");
		}
	}

	@Override
	public boolean validPage() {
		return this.driver.getTitle().equalsIgnoreCase(this.title);
	}

	@Override
	public String getViewText() {
		return this.containerView.getText();
	}

	@Override
	public ContactList goContactos() {	

		this.btnCancel.click();
		
		return this.pageObjectFactory.factoryEgroupwareContactoList(this.driverManager);
	}
}
