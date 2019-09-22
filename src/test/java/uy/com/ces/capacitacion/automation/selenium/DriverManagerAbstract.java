package uy.com.ces.capacitacion.automation.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import uy.com.ces.capacitacion.automation.ConfigInject;
import uy.com.ces.capacitacion.automation.ConfigInjectResolver;
import uy.com.ces.capacitacion.automation.DependencyInject;
import uy.com.ces.capacitacion.automation.DependencyInjectResolver;
import uy.com.ces.capacitacion.automation.RandomInjectResolver;

/**
 * @author Dardo De Le�n
 */
@ExtendWith({ 
	RandomInjectResolver.class, 
	ConfigInjectResolver.class, 
	DependencyInjectResolver.class 
})
public abstract class DriverManagerAbstract {

	public static final String PROP_BROWSER = "browser"; 
	
	public static DriverManager driverManager;

	/**
	 * Asigna la factor�a WebDriver y el tipo de WebDriver que se usar� en las
	 * pruebas
	 * 
	 * @param driverManager
	 * @param driver
	 */
	@BeforeAll
	@DependencyInject({ DriverManagerImpl.class })
	public static void setUp(DriverManager dm, @ConfigInject("web.driver.type") String type,
			@ConfigInject("web.driver.timeout") Integer timeout) {

		type = getBrowserBySystemProperty(PROP_BROWSER, type);
		
		driverManager = dm;

		driverManager.setDriverType(type, timeout);
	}

	/**
	 * Despu�s de cada prueba, se elimina la instancia de WebDriver actual
	 */
	@AfterEach
	public void setDown() {
		driverManager.destroyDriver();
	}
	
	protected static String getBrowserBySystemProperty(String prop, String def)
	{
		String browserType = System.getProperty(prop);
		if (browserType != null) {
			def = browserType;
		}
		
		return def;
	}
}
