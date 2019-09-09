package uy.com.ces.capacitacion.automation;

import java.lang.reflect.Parameter;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class RandomInjectResolver implements ParameterResolver {
	/**
	 * Retorna true si el parámetro fue decorado con la anotación RandomInject
	 */
	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		return getAnnotation(parameterContext) != null;
	}

	/**
	 * Retorna el valor que debe inyectarse como parámetro, en base al tipo de dato
	 * del parámetro, se genera y retorna un valor random
	 */
	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {

		RandomInject annotation = getAnnotation(parameterContext);

		if (annotation == null) {
			throw new ParameterResolutionException("No se encotró el decorador @RandomInject.");
		}

		Object result = null;

		Class<?> type = parameterContext.getParameter().getType();

		if (type.equals(String.class)) {
			result = RandomStringUtils.randomAlphanumeric(annotation.value());
		} else if (type.equals(Integer.class)) {
			Random random = new Random();
			result = random.nextInt(annotation.value());
		} else {
			throw new ParameterResolutionException(
					"El tipo de parámetro " + type + " no está soportado por RandomInjectResolver.");
		}

		return result;
	}

	/**
	 * @param parameterContext contexto de ejecución del parámetro.
	 * @return Retorna la anotación RandomInject asociada al parámetro recibido en
	 *         el contexto de ejecución.
	 */
	protected static RandomInject getAnnotation(ParameterContext parameterContext) {

		Parameter param = parameterContext.getParameter();

		return param.getAnnotation(RandomInject.class);
	}
}
