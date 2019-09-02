package uy.com.ces.capacitacion.automation.selenium;

import org.openqa.selenium.WebDriver;

/**
 * Interface del servicio que provee instancias de WebDriver
 * 
 * @author Dardo De León
 */
public interface DriverManager {
	/**
	 * @param string indicando el Driver que debe emplearse
	 */
	void setDriverType(String type);

	/**
	 * @return Instancia de WebDriver del tipo asignado por medio de setDriverType
	 */
	WebDriver factoryDriver();

	/**
	 * Cierra la instancia de WebDriver iniciada al llamar el método factoryDriver
	 */
	void destroyDriver();
}
