package uy.com.ces.capacitacion.automation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que permite indicar el nombre de la clase que debe ser iniciada
 * para ser pasada como valor del parámetro anotado.
 *
 * @author Dardo De León
 */
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface DependencyInject {
	/**
	 * @return Clase que debe ser iniciada
	 */
	Class<?>[] value();

	/**
	 * @return true indica que la instancia del objeto generado con la clase
	 *         indicada, debe preservarse para su reutilización.
	 */
	boolean singleton() default true;

}
