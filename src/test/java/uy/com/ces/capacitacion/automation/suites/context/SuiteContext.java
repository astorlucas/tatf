package uy.com.ces.capacitacion.automation.suites.context;

/**
 * Provee información del contexto de ejecución.
 * 
 * @author Dardo De León
 */
public interface SuiteContext {

	/**
	 * @param k Clave del argumento
	 * @param d Valor por defecto a retornar
	 * @return Retorna el valor almacenado bajo la clave k y si no existe la clave
	 *         k, retorna d.
	 */
	String getArg(String k, String d);

	/**
	 * @param k Clave de la propiedad en System
	 * @param d Valor por defecto a retornar
	 * @return Retorna el valor almacenado bajo la clave k y si no existe la clave
	 *         k, retorna d.
	 */
	String getSystem(String k, String d);

	/**
	 * @param k Clave de la propiedad en el archivo Manifest
	 * @param d Valor por defecto a retornar
	 * @return Retorna el valor almacenado bajo la clave k y si no existe la clave
	 *         k, retorna d.
	 */
	String getManifest(String k, String d);

}