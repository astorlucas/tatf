package uy.com.ces.capacitacion.automation.selenium;

import org.openqa.selenium.WebDriver;

/**
 * Interface del servicio que provee instancias de WebDriver
 * 
 * @author Dardo De Le�n
 */
public interface DriverManager {
	/**
	 * @param string  indicando el Driver que debe emplearse
	 * @param integer timeout
	 */
	void setDriverType(String type, Integer timeout);

	/**
	 * @return Instancia de WebDriver del tipo asignado por medio de setDriverType
	 */
	WebDriver factoryDriver();

	/**
	 * Cierra la instancia de WebDriver iniciada al llamar el m�todo factoryDriver
	 */
	void destroyDriver();

	/**
	 * @param <T>
	 * @param o   Objeto con atributos decorados con FindBy
	 * @return El objeto recibido
	 */
	<T extends Object> T factoryElements(T o);
}
