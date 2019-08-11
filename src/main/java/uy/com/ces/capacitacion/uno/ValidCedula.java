package uy.com.ces.capacitacion.uno;

/**
 * @author Dardo De León
 */
public interface ValidCedula {
	/**
	 * @param cedula cadena de texto que posee, solo los números de una cédula de
	 *               identidad Uruguaya.
	 * @return true si la cédula recibida es valida, false si no lo es.
	 */
	public boolean valid(String cedula);
}
