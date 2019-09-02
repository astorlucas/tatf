package uy.com.ces.capacitacion.automation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotaci�n que permite indicar el nombre de una variable de configuraci�n que
 * debe ser empleada para inyectar su valor en el par�metro anotado.
 * 
 * @author Dardo De Le�n
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigInject {
	/**
	 * @return Clave de la variable disponible en ConfigInjectResolver
	 */
	String value() default "";
}
