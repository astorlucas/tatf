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
	public static void setUp(DriverManager dm, @ConfigInject("web.driver") String driver) {

		driverManager = dm;

		driverManager.setDriverType(driver);
	}

	/**
	 * Despu�s de cada prueba, se elimina la instancia de WebDriver actual
	 */
	@AfterEach
	public void setDown() {
		driverManager.destroyDriver();
	}
}
