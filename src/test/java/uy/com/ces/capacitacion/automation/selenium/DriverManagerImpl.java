package uy.com.ces.capacitacion.automation.selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Dardo De León
 */
public class DriverManagerImpl implements DriverManager {
	
	private WebDriver driver;

	private DriverManagerType driverType;

	private Integer timeout;

	@Override
	public void setDriverType(String type, Integer to) {
		this.driverType = DriverManagerType.valueOf(type);

		this.timeout = to;

		WebDriverManager.getInstance(this.driverType).setup();
	}

	@Override
	public WebDriver factoryDriver() {

		if (this.driver == null) {
			
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
				 * this.driver = new PhantomJSDriver(); break;
				 */
			case SELENIUM_SERVER_STANDALONE:
			default:
				throw new NotImplementedException("No se encuentra implementado el webdriver %s",
						this.driverType.name());
			}

			if (this.timeout > 0) {
				this.driver.manage().timeouts().implicitlyWait(this.timeout, TimeUnit.SECONDS);
			}
			
			if (this.driver instanceof RemoteWebDriver) {
				Capabilities capabilities = ((RemoteWebDriver) this.driver).getCapabilities();
				System.setProperty("driver.manager.current.browser",
						capabilities.getBrowserName() + " " + capabilities.getVersion());
			}		
		}

		return this.driver;
	}

	@Override
	public <T> T factoryElements(T o) {
		PageFactory.initElements(this.driver, o);

		return o;
	}

	@Override
	public void destroyDriver() {
		if (this.driver != null) {
			this.driver.close();
			this.driver = null;
		}
	}
	
	@Override
	public String getPopHandle(String winHandle, Set<String> winHandles) {

		if (winHandle == null) {
			throw new IllegalStateException("Se debe indicar el nombre de la ventana principal.");
		}
		
		if (winHandles == null) {
			throw new IllegalStateException("Se debe indicar un set de nombres de ventanas.");
		}
		
		List<String> result = new LinkedList<String>();

		Iterator<String> listWinHandles = winHandles.iterator();

		while (listWinHandles.hasNext()) {
			String next = listWinHandles.next();
			if (!next.equalsIgnoreCase(winHandle) && !result.contains(next)) {
				result.add(next);
			}
		}

		if (result.isEmpty()) {
			throw new IllegalStateException("No se encontró un nombre de ventana.");
		} else if (result.size() > 1) {
			throw new IllegalStateException("Se encontraron "+ result.size() +" nombres de ventanas, solo se permite la ubicación de uno.");
		}

		return result.get(0);
	}

	/**
	 * La implementación actual, solicita al driver detener la ejecución a la espera
	 * de que la página posea una URL igual al nombre de la clase
	 */
	@Override
	public void stop(Duration duration) {
		
		try {
			WebDriverWait wt = new WebDriverWait(driver, duration.toSeconds() - 1, 1);

			wt.ignoring(NoSuchElementException.class);
			
			wt.until(ExpectedConditions.urlToBe(this.getClass().getName()));

		} catch (TimeoutException e) {
		}
	}

	@Override
	public WebElement fluentWait(ExpectedCondition<WebElement> expected, Duration timeOut, Duration polling) {
		
		FluentWait<WebDriver> fw = new FluentWait<>(this.driver);
		
		fw.withTimeout(timeOut);
		fw.pollingEvery(polling);
		fw.ignoring(NoSuchElementException.class);
		
		return fw.until(expected);
	}

	@Override
	public WebElement fluentWaitToBeClickable(WebElement element, Duration timeOut,
			Duration pollingEvery) {

		return this.fluentWait(ExpectedConditions.elementToBeClickable(element), timeOut, pollingEvery);
	}
}
