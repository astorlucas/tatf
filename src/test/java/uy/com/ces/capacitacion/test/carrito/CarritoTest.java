package uy.com.ces.capacitacion.test.carrito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.dos.logica.FactoryCarrito;
import uy.com.ces.capacitacion.dos.logica.ICarrito;
import uy.com.ces.capacitacion.dos.logica.Producto;

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

	/**
	 * La prueba busca verificar que el carrito retorna un valor cero cuando se le
	 * consulta la cantidad de un producto que no a sido dado de alta.
	 */
	@Test
	public void testAgregarProductoNoExistente() {
		assertEquals(0, carrito.obtenerCantidad(PROD_PAPA));
	}

	@Test
	public void testProbarTotal() {
		int cantPapa = 1;
		int cantLechuga = 1;

		double precioPapa = 25;
		double precioLechuga = 10;

		double resultado = (cantPapa * precioPapa) + (cantLechuga * precioLechuga);

		carrito.agregarProducto(factoryProducto(precioPapa, PROD_PAPA), cantPapa);
		carrito.agregarProducto(factoryProducto(precioLechuga, PROD_LECHUGA), cantLechuga);

		assertEquals(resultado, carrito.obtenerPrecioTotal());
	}

	/**
	 * La prueba busca corroborar que el método obtenerPrecioTotal, retorna la suma
	 * de la multiplicación de cantidad por el precio de cada item, cuando la
	 * cantidad y precio ingresados al carrito es diferente.
	 * 
	 * La prueba busca corroborar que el método que realiza el cálculo del precio
	 * total, no realiza un cálculo erróneo, al emplear valores de variables
	 * asociadas a items diferentes, ej: la cantidad o el precio del item recorrido
	 * en la interación anterior.
	 */
	@Test
	public void testPrecioTotalCantDifProductos() {
		int cantPapa = 5;
		int cantLechuga = 8;

		double precioPapa = 25.50;
		double precioLechuga = 10.99;

		double resultado = (cantPapa * precioPapa) + (cantLechuga * precioLechuga);

		carrito.agregarProducto(factoryProducto(precioPapa, PROD_PAPA), cantPapa);
		carrito.agregarProducto(factoryProducto(precioLechuga, PROD_LECHUGA), cantLechuga);

		assertEquals(resultado, carrito.obtenerPrecioTotal());
	}

	/**
	 * Busca verificar que el carrito no produce una reducción de cantidad de items,
	 * cuando se llama el metodo agregarProducto con una cantidad negativa.
	 */
	@Test
	public void testReducirItemsAlAgregar() {
		int cantInicial = 22;
		int cantPosterior = -13;

		Producto papa = factoryProducto(1, PROD_PAPA);

		carrito.agregarProducto(papa, cantInicial);
		assertEquals(cantInicial, carrito.obtenerCantidad(PROD_PAPA));

		carrito.agregarProducto(papa, cantPosterior);
		assertEquals(cantInicial, carrito.obtenerCantidad(PROD_PAPA));
	}

	/**
	 * Busca verificar que el carrito no almacena valores negativos como cantidad de
	 * items de un producto, cuando se agrega un producto que no existe en el
	 * carrito.
	 */
	@Test
	public void testAgregarItemsNegativos() {
		int cantNegativa = -32;

		Producto papa = factoryProducto(1, PROD_PAPA);

		carrito.agregarProducto(papa, cantNegativa);
		assertEquals(0, carrito.obtenerCantidad(PROD_PAPA));
	}

	private Producto factoryProducto(double precio, String nombre) {
		return new Producto(precio, nombre);
	}
}