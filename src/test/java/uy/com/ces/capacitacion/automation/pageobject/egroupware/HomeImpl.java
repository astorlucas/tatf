package uy.com.ces.capacitacion.automation.pageobject.egroupware;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import uy.com.ces.capacitacion.automation.pageobject.PageObjectFactory;
import uy.com.ces.capacitacion.automation.selenium.DriverManager;

public class HomeImpl implements Home {

	protected WebDriver driver;
	protected DriverManager driverManager;
	protected PageObjectFactory pageObjectFactory;

	@FindBy(name = "login")
	public WebElement inputUserName;

	@FindBy(name = "passwd")
	public WebElement inputPassword;

	@FindBy(name = "submitit")
	public WebElement btnLogin;

	public HomeImpl(DriverManager dm, PageObjectFactory pof) {

		this.driverManager = dm;
		this.pageObjectFactory = pof;

		this.driver = this.driverManager.factoryDriver();
	}

	@Override
	public void goHome(String homepage) {
		this.driver.get(homepage);
	}

	@Override
	public Dashboard login(String user, String pass) {

		this.inputUserName.clear();
		this.inputUserName.sendKeys(user);

		this.inputPassword.clear();
		this.inputPassword.sendKeys(pass);

		this.btnLogin.click();

		return pageObjectFactory.factoryEgroupwareDashboard(this.driverManager);
	}
}
