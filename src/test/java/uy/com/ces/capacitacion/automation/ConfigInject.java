package uy.com.ces.capacitacion.automation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que permite indicar el nombre de una variable de configuración que
 * debe ser empleada para inyectar su valor en el parámetro anotado.
 * 
 * @author Dardo De León
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigInject {
	/**
	 * @return Clave de la variable disponible en ConfigInjectResolver
	 */
	String value() default "";
}
