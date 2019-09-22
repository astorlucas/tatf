package uy.com.ces.capacitacion;

import java.io.InputStream;
import java.io.PrintStream;

import uy.com.ces.capacitacion.uno.AppUno;
import uy.com.ces.capacitacion.uno.ValidCedula;
import uy.com.ces.capacitacion.uno.ValidCedulaImpl;

/**
 * @author Dardo De León
 */
public class App {

	public static void main(String[] args) {
		(new App()).runAppUno(System.in, System.out);
	}

	/**
	 * Solución a la propuesta de la semana uno.
	 * 
	 * @param in
	 * @param out
	 */
	public void runAppUno(InputStream in, PrintStream out) {
		ValidCedula validator = new ValidCedulaImpl();

		AppUno app = new AppUno(validator);

		app.run(in, out);
	}
}