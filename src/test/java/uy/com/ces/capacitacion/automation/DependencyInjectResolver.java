package uy.com.ces.capacitacion.automation;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * @author Dardo De Le�n
 */
public class DependencyInjectResolver implements ParameterResolver {

	protected static Map<String, Object> cacheInstances = new TreeMap<>();

	/**
	 * Recupera la anotaci�n DependencyInject y retorna true si la anotaci�n posee
	 * alguna clase que pueda ser empleada para resolver el par�metro que debe ser
	 * inyectado.
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		boolean result = false;

		DependencyInject annotation = getAnnotation(parameterContext);

		if (annotation != null) {
			Parameter param = parameterContext.getParameter();

			result = findImplement(param.getType(), annotation.value()) != null;
		}

		return result;
	}

	/**
	 * Recupera la anotaci�n DependencyInject y de ella la primer clase que pueda
	 * resolver el tipo de dato que requiere el par�metro que se esta resolviendo.
	 * 
	 * @throws ParameterResolutionException Si falla la creaci�n de una instancia de
	 *                                      factory; no existe la anotaci�n
	 *                                      DependencyInject o no es posible
	 *                                      recuperar una clase que pueda ser
	 *                                      inyectada, para el tipo de dato
	 *                                      requerido como par�metro.
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		DependencyInject annotation = getAnnotation(parameterContext);

		if (annotation == null) {
			throw new ParameterResolutionException("No se encontr� el decorador @DependencyInject.");
		}

		Parameter param = parameterContext.getParameter();

		Class<?> factory = findImplement(param.getType(), annotation.value());

		return factoryObject(factory, annotation.singleton());
	}

	/**
	 * @param factory   Clase que debe ser instanciada y retornado el objeto
	 *                  generado
	 * @param singleton True indica que la clase factory solo debe ser instanciada
	 *                  una vez y almacenada, para ser reutilizada como respuesta en
	 *                  el futuro
	 * @return Retorna una instancia de Clase
	 * @throws ParameterResolutionException Si falla la creaci�n de una instancia de
	 *                                      factory
	 */
	protected static Object factoryObject(Class<?> factory, boolean singleton) throws ParameterResolutionException {

		if (factory == null) {
			throw new ParameterResolutionException(
					"No se estableci� una referencia a una clase que implemente la interface requerida por el par�metro.");
		}

		Object result = null;

		if (cacheInstances.containsKey(factory.getName()) && singleton) {
			result = cacheInstances.get(factory.getName());
		} else {
			try {
				result = factory.getConstructor().newInstance();

				if (singleton) {
					cacheInstances.put(factory.getName(), result);
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {

				throw new ParameterResolutionException("No fue posible iniciar la instancia de " + factory.getName(),
						e);
			}
		}

		return result;
	}

	/**
	 * @param clase  Interface del par�metro que se debe resolver.
	 * @param clases Array de clases que pueden resolver una instancia de clase.
	 * @return Retorna la primer clase de {@clases} que pueda ser asignada como la
	 *         interface recibida en el par�metro {@clase}
	 */
	protected static Class<?> findImplement(Class<?> clase, Class<?>[] clases) {

		Class<?> result = null;

		int pos = clases.length;
		while (pos > 0 && result == null) {
			pos--;

			if (clase.isAssignableFrom(clases[pos])) {
				result = clases[pos];
			}
		}

		return result;
	}

	/**
	 * @param parameterContext
	 * @return Retorna la anotaci�n DependencyInject que posee el m�todo de prueba
	 *         que requiere resolver una dependencia.
	 */
	protected static DependencyInject getAnnotation(ParameterContext parameterContext) {

		Executable executable = parameterContext.getDeclaringExecutable();

		return executable.getAnnotation(DependencyInject.class);
	}

}
