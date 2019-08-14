package uy.com.ces.capacitacion.uno;

/**
 * @author Dardo De Le�n
 */
public class ValidCedulaImpl implements ValidCedula {

	/**
	 * N�meros empleados en el c�lculo del d�gito verificador.
	 * 
	 * @see https://es.wikipedia.org/wiki/C%C3%A9dula_de_Identidad_de_Uruguay
	 */
	private int[] validationVector = new int[] { 8, 1, 2, 3, 4, 7, 6 };

	/**
	 * {@link uy.com.ces.capacitacion.uno.ValidCedula#valid(String)}.
	 */
	@Override
	public boolean valid(String cedula) {
		boolean result = false;

		if (cedula != null && cedula.length() > 1) {

			String cedulaSanitized = this.sanitize(cedula);

			if (this.hasChanged(cedula, cedulaSanitized)) {

				Integer validationDigit = this.genereValidationDigit(cedulaSanitized);

				result = this.checkValidatonDigit(validationDigit, cedulaSanitized);
			}
		}

		return result;
	}

	/**
	 * Quita todo car�cter que no se encuentre entre 0-9
	 * 
	 * @param cedula
	 * @return retorna los caracteres de la cadena c�dula que est�n comprendidos
	 *         entre 0-9 [^0-9] y que comienzan con un valores entre 1-9
	 */
	public String sanitize(String cedula) {
		return cedula != null ? cedula.trim().replaceAll("[^0-9]|^0*", "") : "";
	}

	/**
	 * @return true si oldCedula es igual a newCedula
	 */
	public boolean hasChanged(String oldCedula, String newCedula) {
		return oldCedula != null && oldCedula.equals(newCedula);
	}

	/**
	 * @param cedula
	 * @return Retorna el ultimo decimal del resultado de multiplicar n�mero
	 *         contenido en la cadena c�dula, por el n�mero de validaci�n que
	 *         corresponda a su posici�n.
	 */
	public Integer genereValidationDigit(String cedula) {
		Integer result = -1;

		if (cedula != null && cedula.length() <= validationVector.length + 1) {
			result = 0;

			for (int cedulaPos = cedula.length() - 1, vectorPos = validationVector.length - 1; cedulaPos > 0
					&& vectorPos >= 0; cedulaPos--, vectorPos--) {

				result += this.parsePosition(cedula, cedulaPos) * validationVector[vectorPos];
			}
		}

		return this.reduceValidationDigit(result);
	}

	/**
	 * @param value valor mayor a 10
	 * @return si value es mayor a 10, retorna el valor del ultim� n�mero entero en
	 *         value, de lo contrario retorna el valor recibido
	 */
	public Integer reduceValidationDigit(Integer value) {
		if (value != null) {
			while (value > 10) {
				value = value % 10;
			}
		}

		return value;
	}

	/**
	 * @param value valor que debe encontrarse en la posici�n del d�gito
	 *              verificador.
	 * @param str   cadena con los n�meros de una c�dula
	 * @return retorna true si value coincide con el ultimo car�cter de la cadena.
	 */
	public boolean checkValidatonDigit(Integer value, String str) {
		return value != null && value.equals(this.getValidatonDigit(str));
	}

	/**
	 * @param str cadena de texto que debe tener un n�mero en la ultima posici�n.
	 * @return retorna el �ltimo car�cter de la cadena, convertido a n�mero, si la
	 *         cadena es invalida, retorna -1.
	 */
	public Integer getValidatonDigit(String str) {
		return str != null ? this.parsePosition(str, str.length()) : -1;
	}

	/**
	 * @param str      cadena de la que se recuperar� el car�cter ubicado en
	 *                 position
	 * @param position indica la posici�n del car�cter en str que debe ser retornado
	 *                 como Integer
	 * @return Retorna convertido a Integer, el car�cter de str ubicado en position
	 *         recibida, si no es posible recuperar la posici�n retorna -1
	 */
	public Integer parsePosition(String str, Integer position) {
		return str != null && position > 0 && position <= str.length()
				? Integer.parseInt(str.substring(position - 1, position))
				: -1;
	}

}
