package uy.com.ces.capacitacion.automation.selenium;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Dardo De León
 */
public class DriverManagerImpl implements DriverManager {

	private WebDriver driver;

	private DriverManagerType driverType;

	@Override
	public void setDriverType(String type) {
		this.driverType = DriverManagerType.valueOf(type);

		WebDriverManager.getInstance(this.driverType).setup();
	}

	@Override
	public WebDriver factoryDriver() {
		switch (this.driverType) {
		case CHROME:
			this.driver = new ChromeDriver();
			break;
		case FIREFOX:
			this.driver = new FirefoxDriver();
			break;
		case EDGE:
			this.driver = new EdgeDriver();
			break;
		case OPERA:
			this.driver = new OperaDriver();
			break;
		case IEXPLORER:
			this.driver = new InternetExplorerDriver();
			break;
		case PHANTOMJS:
			/**
			 * this.driver = new PhantomJSDriver();
			 * break;
			 */
		case SELENIUM_SERVER_STANDALONE:
		default:
			throw new NotImplementedException("No se encuentra implementado el webdriver %s", this.driverType.name());
		}

		return this.driver;
	}

	@Override
	public void destroyDriver() {
		if (this.driver != null) {
			this.driver.close();
			this.driver = null;
		}
	}
}
