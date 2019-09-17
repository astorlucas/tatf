package uy.com.ces.capacitacion.automation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * @author Dardo De Le�n
 */
public class ConfigInjectResolver implements ParameterResolver {

	protected static Map<String, Properties> cacheProperties = new TreeMap<>();
	protected static Map<String, Properties> cacheClassProperties = new TreeMap<>();

	/**
	 * Retorna true si el par�metro fue decorado con la anotaci�n ConfigInject
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		return getAnnotation(parameterContext) != null;
	}

	/**
	 * Retorna el valor que debe inyectarse como par�metro, en base a la
	 * configuraci�n de recursos y clave de configuraci�n proporcionada por las
	 * anotaciones ConfigInjectResources y ConfigInject.
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		ConfigInject annotation = getAnnotation(parameterContext);

		if (annotation == null) {
			throw new ParameterResolutionException("No se encotr� el decorador @ConfigInject.");
		}

		Properties properties = getPropertiesByTest(extensionContext.getRequiredTestClass());

		String value = properties.getProperty(annotation.value());

		if (value == null) {
			throw new ParameterResolutionException("La propiedad " + annotation.value() + " no est� disponible.");
		}

		Object result = null;

		Class<?> type = parameterContext.getParameter().getType();

		if (type.equals(String.class)) {
			result = value;
		} else if (type.equals(Integer.class)) {
			result = Integer.parseInt(value);
		} else {
			throw new ParameterResolutionException(
					"El tipo de par�metro " + type + " no est� soportado por RandomInjectResolver.");
		}

		return result;
	}

	/**
	 * @param test Clase que implementa la prueba en ejecuci�n.
	 * @return Un objeto Properties con las variables cargadas desde los recursos
	 *         se�alados por la anotaci�n de clase ConfigInjectResources
	 * @throws ParameterResolutionException Si no existe la anotaci�n
	 *                                      ConfigInjectResources, o falla la carga
	 *                                      de los recursos con variables.
	 */
	protected static Properties getPropertiesByTest(Class<?> test) throws ParameterResolutionException {

		ConfigInjectResources configResources = test.getAnnotation(ConfigInjectResources.class);

		if (configResources == null) {
			throw new ParameterResolutionException(
					"Debe definir la anotaci�n @ConfigInjectResources con cero o un archivo de configuraci�n.");
		}

		String className = ConfigInjectResources.class.getName();

		if (!cacheClassProperties.containsKey(className)) {

			Properties properties = new Properties();

			for (String resource : configResources.value()) {
				properties.putAll(loadProperties(resource));
			}

			cacheClassProperties.put(className, properties);
		}

		return cacheClassProperties.get(className);
	}

	/**
	 * @param parameterContext contexto de ejecuci�n del par�metro.
	 * @return Retorna la anotaci�n ConfigInject asociada al par�metro recibido en
	 *         el contexto de ejecuci�n.
	 */
	protected static ConfigInject getAnnotation(ParameterContext parameterContext) {

		Parameter param = parameterContext.getParameter();

		return param.getAnnotation(ConfigInject.class);
	}

	/**
	 * @param resource Ubicaci�n de un archivo properties
	 * @return Una instancia de Properties con las propiedades cargadas desde
	 *         resource
	 * @throws ParameterResolutionException Si falla la carga de resource
	 */
	protected static Properties loadProperties(String resource) throws ParameterResolutionException {

		if (!cacheProperties.containsKey(resource)) {

			try (InputStream input = new FileInputStream(resource)) {

				Properties properties = new Properties();
				properties.load(input);

				cacheProperties.put(resource, properties);

			} catch (IOException ex) {
				throw new ParameterResolutionException("No fue posible cargar el archivo de propiedades " + resource,
						ex);
			}
		}

		return cacheProperties.get(resource);
	}
}
