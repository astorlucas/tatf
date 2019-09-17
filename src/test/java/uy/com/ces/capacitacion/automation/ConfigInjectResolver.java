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
 * @author Dardo De León
 */
public class ConfigInjectResolver implements ParameterResolver {

	protected static Map<String, Properties> cacheProperties = new TreeMap<>();
	protected static Map<String, Properties> cacheClassProperties = new TreeMap<>();

	/**
	 * Retorna true si el parámetro fue decorado con la anotación ConfigInject
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		return getAnnotation(parameterContext) != null;
	}

	/**
	 * Retorna el valor que debe inyectarse como parámetro, en base a la
	 * configuración de recursos y clave de configuración proporcionada por las
	 * anotaciones ConfigInjectResources y ConfigInject.
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		ConfigInject annotation = getAnnotation(parameterContext);

		if (annotation == null) {
			throw new ParameterResolutionException("No se encotró el decorador @ConfigInject.");
		}

		Properties properties = getPropertiesByTest(extensionContext.getRequiredTestClass());

		String value = properties.getProperty(annotation.value());

		if (value == null) {
			throw new ParameterResolutionException("La propiedad " + annotation.value() + " no está disponible.");
		}

		Object result = null;

		Class<?> type = parameterContext.getParameter().getType();

		if (type.equals(String.class)) {
			result = value;
		} else if (type.equals(Integer.class)) {
			result = Integer.parseInt(value);
		} else {
			throw new ParameterResolutionException(
					"El tipo de parámetro " + type + " no está soportado por RandomInjectResolver.");
		}

		return result;
	}

	/**
	 * @param test Clase que implementa la prueba en ejecución.
	 * @return Un objeto Properties con las variables cargadas desde los recursos
	 *         señalados por la anotación de clase ConfigInjectResources
	 * @throws ParameterResolutionException Si no existe la anotación
	 *                                      ConfigInjectResources, o falla la carga
	 *                                      de los recursos con variables.
	 */
	protected static Properties getPropertiesByTest(Class<?> test) throws ParameterResolutionException {

		ConfigInjectResources configResources = test.getAnnotation(ConfigInjectResources.class);

		if (configResources == null) {
			throw new ParameterResolutionException(
					"Debe definir la anotación @ConfigInjectResources con cero o un archivo de configuración.");
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
	 * @param parameterContext contexto de ejecución del parámetro.
	 * @return Retorna la anotación ConfigInject asociada al parámetro recibido en
	 *         el contexto de ejecución.
	 */
	protected static ConfigInject getAnnotation(ParameterContext parameterContext) {

		Parameter param = parameterContext.getParameter();

		return param.getAnnotation(ConfigInject.class);
	}

	/**
	 * @param resource Ubicación de un archivo properties
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
