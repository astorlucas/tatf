package uy.com.ces.capacitacion.automation.suites.listener;

/**
 * @author Dardo De León
 */
public interface SuiteListener {
	
	/**
	 * Asigna el nombre de la suite que se está ejecutando
	 * 
	 * @param Nombre de la suite
	 */
	public void setSuite(String suite);
	
	/**
	 * Imprime el resumen de la ejecución
	 */
	public abstract void showResume();
	
	/**
	 * Almacena el resumen de la ejecución
	 */
	public abstract void saveResume();
}
