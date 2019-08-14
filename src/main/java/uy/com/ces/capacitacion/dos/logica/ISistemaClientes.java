package uy.com.ces.capacitacion.dos.logica;

public interface ISistemaClientes {

	/**
	 * devuelve el descuento para ese Cliente, si no existe devuelve una Excepción
	 */
	public double descuentoCliente(Cliente c) throws NoExisteClienteException;

}
