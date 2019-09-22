package uy.com.ces.capacitacion.suites.runner;

import java.io.PrintStream;

import org.junit.runner.JUnitCore;

import uy.com.ces.capacitacion.suites.AppSuite;
import uy.com.ces.capacitacion.suites.CesSuite;
import uy.com.ces.capacitacion.suites.GoogleSuite;
import uy.com.ces.capacitacion.suites.listener.SuiteListener;

public class SuiteRunner {

	public static final PrintStream OUT = System.out;

	/**
	 * @return Suites de pruebas que deben ser ejecutadas
	 */
	protected static final Class<?>[] getSuites() {
		return new Class<?>[] { 
			AppSuite.class, 
			CesSuite.class, 
			GoogleSuite.class 
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
	protected static Class<?>[] getSuites(String[] args) throws ClassNotFoundException {
		return (args.length == 1) ? getSuites(args[0]) : getSuites();
	}

	public static void main(String[] args) throws ClassNotFoundException {

		SuiteListener listener = new SuiteListener(OUT);

		JUnitCore junit = new JUnitCore();

		junit.addListener(listener);

		Class<?> suites[] = getSuites(args);

		for (Class<?> suite : suites) {

			listener.setSuite(suite.toString());

			junit.run(suite);
		}

		listener.showResume();
	}
}
