package uy.com.ces.capacitacion.automation.suites.listener;

/**
 * @author Dardo De Le�n
 */
public interface SuiteListener {
	
	/**
	 * Asigna el nombre de la suite que se est� ejecutando
	 * 
	 * @param Nombre de la suite
	 */
	public void setSuite(String suite);
	
	/**
	 * Imprime el resumen de la ejecuci�n
	 */
	public abstract void showResume();
	
	/**
	 * Almacena el resumen de la ejecuci�n
	 */
	public abstract void saveResume();
}
