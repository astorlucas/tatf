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

	static final int STRING = 0;

	static final int COUNTRY_ID = 1;

	static final int COUNTRY_NAME = 2;

	static final int COLOR_HEX = 3;

	/**
	 * @return Indica el tipo de valor random a inyectar
	 */
	int type() default STRING;

	/**
	 * @return Largo máximo del valor random
	 */
	int min() default 0;

	/**
	 * @return Largo máximo del valor random
	 */
	int max() default 10;

	/**
	 * @return Lista de cadenas en base a la cual se realiza la selección de una de
	 *         ellas.
	 */
	String[] options() default {};
}
