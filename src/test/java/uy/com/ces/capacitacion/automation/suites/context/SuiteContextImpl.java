package uy.com.ces.capacitacion.automation.suites.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author Dardo De León
 */
public class SuiteContextImpl implements SuiteContext {

	public static final String ARGS_BROWSER = "bws";
	public static final String PROP_BROWSER = "browser";
	public static final String FILE_MANIFEST = "META-INF/MANIFEST.MF";

	protected Properties systemProperties;
	protected Properties manifestProperties;
	protected Map<String, String> argsMap;

	public SuiteContextImpl(String[] args) {
		
		this.prepareArgsMap(args);
		
		this.prepareSystemProperties();
		
		this.prepareManifestProperties(FILE_MANIFEST);

		this.prepareBrowser(ARGS_BROWSER, PROP_BROWSER);
	}

	@Override
	public String getArg(String k, String d) {
		return this.argsMap.containsKey(k) ? this.argsMap.get(k) : d;
	}

	@Override
	public String getSystem(String k, String d) {
		return this.systemProperties.getProperty(k, d);
	}

	@Override
	public String getManifest(String k, String d) {
		return this.manifestProperties.getProperty(k, d);
	}

	protected void prepareArgsMap(String[] args) {
		this.argsMap = (args == null) 
				? new HashMap<>()
				: Arrays.asList(args)
					.stream()
					.map(p -> p.split("="))
					.filter(p -> p.length == 2)
					.collect(Collectors.toMap(x -> x[0].replace("-", ""), x -> x[1]));
	}

	protected void prepareSystemProperties() {
		this.systemProperties = System.getProperties();
	}

	protected void prepareManifestProperties(String manifest) {
		this.manifestProperties = new Properties();

		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(manifest)) {
			this.manifestProperties.load(is);
		} catch (IOException e) {

		}
	}

	/**
	 * @param argsKey   Nombre de la clave recibida por parametros
	 * @param systemKey Nombre de la calve en propiedades del sistema, donde se debe
	 *                  almacenar el valor
	 */
	protected void prepareBrowser(String argsKey, String systemKey) {
		String value = this.getArg(argsKey, System.getProperty(systemKey));
		if (value != null) {
			System.setProperty(systemKey, value);	
		}
	}
}
