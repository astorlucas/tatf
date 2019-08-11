package uy.com.ces.capacitacion.uno;

/**
 * @author Dardo De Le�n
 */
public interface ValidCedula {
	/**
	 * @param cedula cadena de texto que posee, solo los n�meros de una c�dula de
	 *               identidad Uruguaya.
	 * @return true si la c�dula recibida es valida, false si no lo es.
	 */
	public boolean valid(String cedula);
}
