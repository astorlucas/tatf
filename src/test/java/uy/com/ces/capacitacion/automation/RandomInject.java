package uy.com.ces.capacitacion.automation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotaci�n que permite indicar caracter�sticas de un valor random a ser
 * inyectado en un par�metro de m�todo.
 * 
 * @author Dardo De Le�n
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomInject {
	/**
	 * @return Largo m�ximo del valor random
	 */
	int value() default 10;
}
