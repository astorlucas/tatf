package uy.com.ces.capacitacion.dos.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarritoTest {

	public static final String PROD_PAPA = "PAPA";
	public static final String PROD_LECHUGA = "LECHUGA";

	ICarrito carrito;

	@BeforeEach
	public void setUp() {
		carrito = FactoryCarrito.getCarrito();
	}

	@AfterEach
	public void tearDown() {
		carrito.vaciar();
	}

	@Test
	public void testAgregarProductoExistente() {
		long precio = 25;

		int cantInicial = 20;
		int cantPosterior = 2;

		Producto papa = factoryProducto(precio, PROD_PAPA);

		carrito.agregarProducto(papa, cantInicial);
		assertEquals(cantInicial, carrito.obtenerCantidad(PROD_PAPA));

		carrito.agregarProducto(papa, cantPosterior);
		assertEquals(cantInicial + cantPosterior, carrito.obtenerCantidad(PROD_PAPA));
	}

	@Test
	public void testProbarTotal() {
		int cantPapa = 1;
		int cantLechuga = 1;

		long precioPapa = 25;
		long precioLechuga = 10;

		long resultado = (cantPapa * precioPapa) + (cantLechuga * precioLechuga);

		carrito.agregarProducto(factoryProducto(precioPapa, PROD_PAPA), cantPapa);
		carrito.agregarProducto(factoryProducto(precioLechuga, PROD_LECHUGA), cantLechuga);

		assertEquals(resultado, (long) carrito.obtenerPrecioTotal());
	}

	private Producto factoryProducto(double precio, String nombre) {
		return new Producto(precio, nombre);
	}
}