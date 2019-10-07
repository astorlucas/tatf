package uy.com.ces.capacitacion.suites.runner;

import java.io.PrintStream;

import org.junit.runner.JUnitCore;

import uy.com.ces.capacitacion.automation.suites.context.SuiteContext;
import uy.com.ces.capacitacion.automation.suites.context.SuiteContextImpl;
import uy.com.ces.capacitacion.automation.suites.listener.SuiteListenerJSON;
import uy.com.ces.capacitacion.automation.suites.listener.SuiteListenerPrintStream;
import uy.com.ces.capacitacion.suites.AppSuite;
import uy.com.ces.capacitacion.suites.CesAddressBookSuite;
import uy.com.ces.capacitacion.suites.CesMoodleSuite;
import uy.com.ces.capacitacion.suites.CesProjectManagerSuite;
import uy.com.ces.capacitacion.suites.GoogleSuite;

/**
 * @author Dardo De León
 */
public class SuiteRunner {

	public static final PrintStream OUT = System.out;
	
	public static final String PARAM_SUITE = "s";

	/**
	 * @return Suites de pruebas que deben ser ejecutadas
	 */
	protected static final Class<?>[] getSuites() {
		return new Class<?>[] { 
			AppSuite.class, 
			GoogleSuite.class, 
			CesMoodleSuite.class,
			CesAddressBookSuite.class, 
			CesProjectManagerSuite.class
		};
	}

	/**
	 * @param className Nombre de una clase/suite calificado.
	 * @return Clase que debe ser ejecutada.
	 * @throws ClassNotFoundException Si la clase no existe
	 */
	protected static Class<?>[] getSuites(String className) throws ClassNotFoundException {
		return new Class<?>[] { Class.forName(className) };
	}

	/**
	 * @param args Parámetros recibidos por linea de comando
	 * @return Array con la clase recibida por parámetro o el array de Suites que se
	 *         configuraron.
	 * @throws ClassNotFoundException Si falla la recuperación de la clase recibida
	 *                                por argumentos.
	 */
	protected static Class<?>[] getSuites(SuiteContext context) throws ClassNotFoundException {
		String currentSuite = context.getArg(PARAM_SUITE, ""); 

		return currentSuite.isEmpty() ? getSuites() : getSuites(currentSuite);
	}

	public static void main(String[] args) throws ClassNotFoundException {

		SuiteContext context = new SuiteContextImpl(args);
		
		SuiteListenerJSON listenerJson = new SuiteListenerJSON(context, OUT);
		SuiteListenerPrintStream listenerPrintStream = new SuiteListenerPrintStream(context, OUT);
		
		JUnitCore junit = new JUnitCore();

		junit.addListener(listenerJson);
		junit.addListener(listenerPrintStream);
		
		Class<?> suites[] = getSuites(context);

		for (Class<?> suite : suites) {

			listenerJson.setSuite(suite.toString());
			listenerPrintStream.setSuite(suite.toString());

			junit.run(suite);
		}		
		
		listenerJson.saveResume();
		listenerPrintStream.showResume();
	}
}
