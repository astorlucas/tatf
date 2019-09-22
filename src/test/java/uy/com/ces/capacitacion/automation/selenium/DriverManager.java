package uy.com.ces.capacitacion.automation.selenium;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

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

	/**
	 * @param winHandle     Ventana principal
	 * @param windowHandles Lista de ventanas manejadas por el driver
	 * @return Nombre la ventana disponible en windowHandles que no coincide con
	 *         winHandle. Si windowHandles posee m�s de tres nombres, o no posee
	 *         dos, genera una excepci�n.
	 */
	String getPopHandle(String winHandle, Set<String> windowHandles) throws IllegalStateException;

	/**
	 * Detiene la ejecuci�n, durante los segundos que se indiquen en {duration}
	 * 
	 * @param duration Tiempo durante el que se debe detener la ejecuci�n
	 */
	void stop(Duration duration);

	/**
	 * @param expected Elemento que debe ser esperado
	 * @param timeOutSelect Tiempo de espera
	 * @param polling Tiempo m�nimo de b�squeda del elemento
	 * @return WebElement recuperado
	 */
	WebElement fluentWait(ExpectedCondition<WebElement> expected, Duration timeOut, Duration polling);

	/**
	 * @param selectType Elemento que debe ser esperado para realizar un click
	 * @param timeOutSelect Tiempo de espera
	 * @param polling Tiempo m�nimo de b�squeda del elemento
	 * @return WebElement recuperado
	 */
	WebElement fluentWaitToBeClickable(WebElement element, Duration timeOut, Duration polling);
}
