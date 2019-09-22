package uy.com.ces.capacitacion.suites.listener;

import java.io.PrintStream;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class SuiteListener extends RunListener {

	protected static String newLine = "\n";

	protected String suite = "";

	protected String resume = "";

	protected final PrintStream out;

	public SuiteListener(PrintStream o) {
		this.out = o;
	}

	/**
	 * Asigna el nombre de la suite que se está ejecutando
	 * 
	 * @param Nombre de la suite
	 */
	public void setSuite(String s) {
		this.suite = s;

		print("[Start suite] %s", this.suite);
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

	/**
	 * Imprime el resumen de la ejecución
	 */
	public void showResume() {
		this.print("%s-------------------------------%s RESUMEN%s-------------------------------%s%s%s", 
				newLine, newLine, newLine, newLine, this.resume, newLine);
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
