package uy.com.ces.capacitacion.dos.logica;

import java.util.Iterator;
import java.util.LinkedList;
import javax.naming.CommunicationException;

class Carrito implements ICarrito {

	private LinkedList<Item> items;
	// el carrito se compone por una lista de <Producto, cantidad>

	Cliente cliente;
	String nombreCarrito;
	private ISistemaClientes sistCli;
	private ISistemaFacturacion sistFact;

	public Carrito(Cliente c) {
		cliente = c;
		sistCli = null;
		sistFact = null;
		try {
			nombreCarrito = c.getNombre();
		} catch (CommunicationException ce) {
			nombreCarrito = "General";
		}

		this.items = new LinkedList<Item>();
	}

	public void agregarProducto(Producto p, int cant) {
		Item i = this.obtenerItem(p.getNombre());
		if (i != null) {
			i.setCantidad(i.getCantidad() + cant);
		} else {
			items.add(new Item(p, cant));
		}
	}

	public void disminuirProducto(Producto p, int cant) {
		// TODO Auto-generated method stub

	}

	public void eliminarProductos(Producto p) {
		// TODO Auto-generated method stub

	}

	public double obtenerPrecioTotal() {
		double precioTotal = 0;
		for (Item item : items) {
			precioTotal += item.getProducto().getPrecio() * item.getCantidad();
		}

		return precioTotal;
	}

	public double obtenerSubtotal(String s) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int obtenerCantidad(String s) {

		Item i = obtenerItem(s);
		if (i != null) {
			return i.getCantidad();
		} else {
			return -1;
		}
	}

	public void vaciar() {
		items.clear();
	}

	private Item obtenerItem(String s) {
		Iterator<Item> iter = items.iterator();
		while (iter.hasNext()) {
			Item actual = iter.next();
			if (actual.getProducto().getNombre().equals(s)) {
				return actual;
			}
		}
		return null;
	}

	public void pagar() {
		double total = (double) obtenerPrecioTotal();
		double descuento;

		try {
			descuento = sistCli.descuentoCliente(cliente);
		} catch (NoExisteClienteException e) {
			descuento = 0;
		}
		total = total * (1 - descuento);
		sistFact.facturar(total);
	}

	public void configurarSistemaClientes(ISistemaClientes s) {
		this.sistCli = s;
	}

	public void configurarSistemaFacturacion(ISistemaFacturacion s) {
		this.sistFact = s;
	}

}
