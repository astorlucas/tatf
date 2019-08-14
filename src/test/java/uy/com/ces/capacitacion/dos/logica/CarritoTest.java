package uy.com.ces.capacitacion.dos.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarritoTest {

	ICarrito carrito;
	ICarrito carrito2;
	Producto papa;
	Producto lechuga;

	public CarritoTest() {
	}

	@BeforeAll
	public static void setUpClass() throws Exception {
	}

	@AfterAll
	public static void tearDownClass() throws Exception {
	}

	@BeforeEach
	public void setUp() {
		carrito = FactoryCarrito.getCarrito();
		papa = new Producto(25, "papa");
		lechuga = new Producto(10, "lechuga");
	}

	@AfterEach
	public void tearDown() {
		carrito.vaciar();
	}

	@Test
	public void testAgregarProductoExistente() {

		carrito.agregarProducto(papa, 20);
		assertEquals(20, carrito.obtenerCantidad("papa"));

		carrito.agregarProducto(papa, 2);
		// deberén haber 22 papas
		assertEquals(22, carrito.obtenerCantidad("papa"));

	}

	@Test
	public void testProbarTotal() {

		carrito.agregarProducto(papa, 1);
		carrito.agregarProducto(lechuga, 1);

		assertEquals(25 + 10, (long) carrito.obtenerPrecioTotal());
	}
}