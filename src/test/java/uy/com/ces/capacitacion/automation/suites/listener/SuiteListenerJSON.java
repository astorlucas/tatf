package uy.com.ces.capacitacion.automation.suites.listener;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import uy.com.ces.capacitacion.automation.suites.context.SuiteContext;

/**
 * @author Dardo De León
 */
public class SuiteListenerJSON extends RunListener implements SuiteListener {

	public static final String ARGS_FILE = "json";
	
	protected static String newLine = "\n";

	protected String suite = "";

	protected SuiteContext context;
	protected PrintStream out;

	protected JsonObject currentTest;
	protected JsonArray currentTests;
	protected JsonArray currentSuites = new JsonArray();

	public SuiteListenerJSON(SuiteContext c, PrintStream o) {
		this.context = c;
		this.out = o;
	}

	/**
	 * Llamado cuando una prueba atómica está por comenzar.
	 */
	@Override
	public void testStarted(Description description) {
		this.addCurrentTest(this.currentTest);

		this.currentTest = new JsonObject();
		this.currentTest.addProperty("description", description.getDisplayName());
		this.currentTest.addProperty("ignored", false);
		this.currentTest.addProperty("failure", "");
	}

	/**
	 * Llamado cuando una prueba atómica es ignorada
	 */
	@Override
	public void testIgnored(Description description) throws Exception {
		this.currentTest.addProperty("ignored", true);
	}

	/**
	 * Llamado cuando una prueba atómica falla
	 */
	@Override
	public void testFailure(Failure failure) throws Exception {
		// failure.getException()
		this.currentTest.addProperty("failure", failure.getMessage());
	}

	/**
	 * Llamado cuando todas las pruebas han terminado.
	 */
	@Override
	public void testRunFinished(Result result) {

		this.addCurrentTest(this.currentTest);

		this.currentTest = null;

		JsonObject element = new JsonObject();
		element.addProperty("name", this.suite);
		element.add("tests", this.currentTests);

		element.addProperty("failure", result.getFailureCount());
		element.addProperty("ignore", result.getIgnoreCount());
		element.addProperty("count", result.getRunCount());
		element.addProperty("time", result.getRunTime());

		this.currentSuites.add(element);

		this.currentTests = null;
	}

	@Override
	public void setSuite(String s) {
		this.suite = s;
	}

	@Override
	public void showResume() {
		this.print("%s-------------------------------%s RESUMEN JSON %s-------------------------------%s%s%s",
				newLine, newLine, newLine, newLine, this.createResume(), newLine);
	}

	@Override
	public void saveResume() {

		String fileName = this.context.getArg(ARGS_FILE, "");

		this.print("%s-------------------------------%s SAVE RESUMEN JSON %s-------------------------------%s%s%s",
				newLine, newLine, newLine, newLine, fileName, newLine);

		if (!fileName.isEmpty()) {
		
			try (FileWriter writer = new FileWriter(fileName); BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(this.createResume());

				this.print("Archivo JSON almacenado correctamente.", fileName);
			} catch (IOException e) {
				this.print("Fallo escritura de archivo JSON, %s.", e.getMessage());
			}
		} else {
			this.print("El nombre de archivo recibido, no es válido.");
		}
	}

	protected void addCurrentTest(JsonObject ct) {
		if (ct != null) {
			if (this.currentTests == null) {
				this.currentTests = new JsonArray();
			}

			this.currentTests.add(ct);
		}
	}
	
	protected String createResume() {
		JsonObject element = new JsonObject();

		element.add("suites", this.currentSuites);

		element.addProperty("executionWebdriver", context.getSystem("driver.manager.current.browser", "ND"));
		element.addProperty("executionTimestamp",
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date()));

		element.addProperty("buildTimestamp", context.getManifest("Build-Timestamp", "ND"));
		element.addProperty("buildJDK", context.getManifest("Build-JDK", "ND"));
		element.addProperty("buildOS", context.getManifest("Build-OS", "ND"));

		element.addProperty("projectTitle", context.getManifest("Project-Title", "ND"));
		element.addProperty("projectVersion", context.getManifest("Project-Version", "ND"));

		element.addProperty("junitVersion", context.getManifest("JUnit-Version", "ND"));
		element.addProperty("seleniumVersion", context.getManifest("Selenium-Version", "ND"));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		return gson.toJson(element);
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
