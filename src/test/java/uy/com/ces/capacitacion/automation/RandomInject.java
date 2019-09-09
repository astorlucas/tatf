package uy.com.ces.capacitacion.automation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación que permite indicar características de un valor random a ser
 * inyectado en un parámetro de método.
 * 
 * @author Dardo De León
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomInject {
	/**
	 * @return Largo máximo del valor random
	 */
	int value() default 10;
}
