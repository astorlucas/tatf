package uy.com.ces.capacitacion.automation.suites.listener;

import java.io.PrintStream;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import uy.com.ces.capacitacion.automation.suites.context.SuiteContext;

/**
 * @author Dardo De León
 */
public class SuiteListenerPrintStream extends RunListener implements SuiteListener {

	protected static String newLine = "\n";

	protected String suite = "";

	protected String resume = "";

	protected PrintStream out;

	public SuiteListenerPrintStream(SuiteContext c, PrintStream o) {
		this.out = o;
	}

	/**
	 * Llamado cuando una prueba atómica está por comenzar.
	 */
	@Override
	public void testStarted(Description description) {

		print(" [Test] %s", description.getDisplayName());
	}

	/**
	 * Llamado cuando una prueba atómica es ignorada
	 */
	@Override
	public void testIgnored(Description description) throws Exception {
		print("  [Ignorado]");
	}

	/**
	 * Llamado cuando una prueba atómica falla
	 */
	@Override
	public void testFailure(Failure failure) throws Exception {
		print("  [Error] %s %s", failure.getDescription().toString(), failure.getException());
	}

	/**
	 * Llamado cuando todas las pruebas han terminado.
	 */
	@Override
	public void testRunFinished(Result result) {

		String finish = this.format("Fallos: %d. Ignorados: %d. Pruebas ejecutadas: %d. Tiempo: %d ms.",
				result.getFailureCount(), 
				result.getIgnoreCount(), 
				result.getRunCount(),
				result.getRunTime());

		this.resume += this.format("[%s] %s%s", this.suite, finish, newLine);
		
		print("[Result suite] %s - %s", this.suite, finish);
	}
	
	@Override
	public void setSuite(String s) {
		this.suite = s;

		print("[Start suite] %s", this.suite);
	}

	@Override
	public void showResume() {
		this.print("%s-------------------------------%s RESUMEN%s-------------------------------%s%s%s", 
				newLine, newLine, newLine, newLine, this.resume, newLine);
	}

	@Override
	public void saveResume() {
		this.showResume();
	}

	/**
	 * @param format Texto con formato
	 * @param args   Valores a interpolar
	 */
	protected void print(String format, Object... args) {
		this.out.println(this.format(format, args));
	}

	protected String format(String format, Object... args) {
		return String.format(format, args);
	}
}
