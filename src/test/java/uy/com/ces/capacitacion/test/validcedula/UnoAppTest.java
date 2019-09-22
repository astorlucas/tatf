package uy.com.ces.capacitacion.test.validcedula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import uy.com.ces.capacitacion.uno.AppUno;
import uy.com.ces.capacitacion.uno.ValidCedula;
import uy.com.ces.capacitacion.uno.ValidCedulaImpl;

/**
 * @author Dardo De León
 */
public class UnoAppTest {

	public static final String CEDULA_OK = "70217948";
	public static final String CEDULA_NOK = "81432153";

	public static final String NEW_LINE_SIMPLE = "\n";
	public static final String NEW_LINE_DOBLE = "\r\n";

	public AppUno app;
	public OutputStream out;
	public static ValidCedula validator;

	/**
	 * Crea la instancia del verificador de cédulas, como no mantiene estados el
	 * objeto puede ser creado antes de iniciar la clase de prueba.
	 */
	@BeforeAll
	static void setUpBeforeClass() {
		validator = new ValidCedulaImpl();
	}

	/**
	 * Se elimina el puntero a la instancia del verificador de cédula
	 */
	@AfterAll
	static void tearDownAfterClass() {
		validator = null;
	}

	/**
	 * Al iniciar cada prueba, se instancia la aplicación que interactúa con el
	 * usuario y la instancia OutputStream que almacena la salida del programa.
	 */
	@BeforeEach
	void setUp() {
		this.app = new AppUno(validator);
		this.out = new ByteArrayOutputStream();
	}

	/**
	 * Al finalizar cada prueba, se elimina la referencia a los objetos empleados en
	 * la prueba, la aplicación y el OutputStream que recibe su salida.
	 */
	@AfterEach
	void tearDown() {
		this.app = null;
		this.out = null;
	}

	@Tag("dev")
	@Test
	final void testCedulaOk() {
		this.app.run(this.buildInput(CEDULA_OK), this.buildOutput(this.out));

		assertEquals(this.buildResult(CEDULA_OK, "true"), this.out.toString(),
				"La validación de cédula válida, no fue correcta");
	}

	@Test
	final void testCedulaNOk() {
		this.app.run(this.buildInput(CEDULA_NOK), this.buildOutput(this.out));

		assertEquals(this.buildResult(CEDULA_NOK, "false"), this.out.toString(),
				"La validación de cédula inválida, no fue correcta");
	}

	/**
	 * @param cédula que el usuario simulado emplea al usar el programa.
	 * @return Retorna el stream que posee una interacción simple del usuario con el
	 *         programa.
	 */
	public InputStream buildInput(String cedula) {
		byte[] in = (cedula + NEW_LINE_SIMPLE + AppUno.EXIT_WORD).getBytes();

		return new ByteArrayInputStream(in);
	}

	/**
	 * @param out OutputStream empleado para almacenar la salida del programa, útil
	 *            para leerlo y confirmar si la salida es valida.
	 * @return Instancia de PrintStream que empleará el programa para escribir su
	 *         salida.
	 */
	public PrintStream buildOutput(OutputStream out) {
		return new PrintStream(out);
	}

	/**
	 * @param cedula número de cédula que se verificará.
	 * @param result true si cédula es valida, false si no lo es.
	 * @return Retorna la salida esperada del programa.
	 */
	public String buildResult(String cedula, String result) {
		return AppUno.MESSAGE_START + NEW_LINE_DOBLE + "`" + cedula + "` = " + result + NEW_LINE_DOBLE
				+ AppUno.MESSAGE_START + NEW_LINE_DOBLE + AppUno.MESSAGE_END;
	}
}
