/**
 * 
 */
package uy.com.ces.capacitacion.uno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Dardo De León
 */
class ValidCedulaImplTest {
	private static final String NUM_END_0 = "1234567890";
	private static final String NUM_END_9 = "1123456789";
	private static final String NUM_END_8 = "9012345678";
	private static final String NUM_END_7 = "8901234567";
	private static final String NUM_END_6 = "7890123456";
	private static final String NUM_END_5 = "6789012345";
	private static final String NUM_END_4 = "5678901234";
	private static final String NUM_END_3 = "4567890123";
	private static final String NUM_END_2 = "3456789012";
	private static final String NUM_END_1 = "1234567891";
	private static final String NUM_START_0 = "0123456789";

	public static ValidCedulaImpl validator;

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
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#valid(java.lang.String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			// valores válidos mínimos
			"16", "129", "1236", "12346", "123458", "1234561",
			// cédulas válidas, que comienzan y/o terminan con números desde [0-9]
			"1321853", "2819762", "3417745", "4038970", "5210789", "6010758", "7080211", "8319570", "9857616",
			// poseen más de 6 dígitos
			"16247503", "25212595", "32791437" })
	public final void testValidOk(String cedula) {
		assertTrue(validator.valid(cedula));
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#valid(java.lang.String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			// 0 a 7 cifras
			"0", "1", "12", "123", "1234", "12345", "123456", "1234567", "12345678",
			// 6 cifras con puntuación
			"363.305-0", "343.927-1", "138.723-2", "694.797-3", "165.687-4", "433.988-5", "217.658-6", "128.230-7",
			"548.690-8", "202.460-9", "1882204", "2064467", "3089714", "4187140", "5326361", "6110068", "7205172",
			"8799032",
			// 7 cifras
			"1.401.235-3", "1.571.306-5", "2.062.517-5", "2.474.797-7", "2.623.990-1", "3.089.770-1", "3.260.656-6",
			"20625175", "24747977", "26239901", "30897701", "32606566", "33073626", "34133202", "37513737", "39587137",
			// 8 cifras
			"12.597.663-9", "12.648.059-5", "12.970.848-5", "13.273.852-5", "13.530.390-1", "13.914.894-5", "132738525",
			"135303901", "139148945", "140054444", "142765921", "144788095", "147005555", "148806865",
			// textos inválidos
			"", "texto", "  ", "\n", "\n\r" })
	public final void testValidNOk(String cedula) {
		assertFalse(validator.valid(cedula));
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#valid(java.lang.String)}.
	 */
	@Test
	public final void testValidNull() {
		assertFalse(validator.valid(null));
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#sanitize(String))}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsSanitize")
	public final void testSanitize(String text, String result) {
		assertEquals(validator.sanitize(text), result);
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#hasChanged(String,
	 * String)d}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsHasChanged")
	public final void testHasChanged(String oldCedula, String newCedula, boolean result) {
		assertEquals(validator.hasChanged(oldCedula, newCedula), result);
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#genereValidationDigit(String)}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsGenereValidationDigit")
	public final void testGenereValidationDigit(String cedula, Integer result) {
		assertEquals(validator.genereValidationDigit(cedula), result);
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#reduceValidationDigit(Integer)}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsReduceValidationDigit")
	public final void testReduceValidationDigit(Integer num, Integer result) {
		assertEquals(validator.reduceValidationDigit(num), result);
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#checkValidatonDigit(Integer, String)}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsCheckValidatonDigit")
	public final void testCheckValidatonDigit(Integer value, String cedula, boolean result) {
		assertEquals(validator.checkValidatonDigit(value, cedula), result);
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#getValidatonDigit(String)}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsGetValidatonDigit")
	public final void testGetValidatonDigit(String cedula, Integer result) {
		assertEquals(validator.getValidatonDigit(cedula), result);
	}

	/**
	 * Test method for
	 * {@link uy.com.ces.capacitacion.uno.ValidCedulaImpl#parsePosition(String, Integer)}.
	 */
	@ParameterizedTest
	@MethodSource("argumentsParsePosition")
	public final void testParsePosition(String str, Integer position, Integer result) {
		assertEquals(validator.parsePosition(str, position), result);
	}

	/**
	 * @link https://junit.org/junit5/docs/5.2.0/api/org/junit/jupiter/params/provider/MethodSource.html
	 */
	private static Stream<Arguments> argumentsSanitize() {
		return Stream.of(
				// corrobora que retorna una cadena solo con números
				Arguments.of(null, ""), Arguments.of("", ""), Arguments.of("solotexto", ""),
				Arguments.of("4542659", "4542659"), Arguments.of("números 45 57 en 545 medio", "4557545"),
				Arguments.of("92 númer0s a 10s 1ad0s 845", "9201010845"),
				Arguments.of("3218 con \\n\\s sa1to de line4 \\n 781", "321814781"));
	}

	private static Stream<Arguments> argumentsHasChanged() {
		return Stream.of(
				// resultados válidos
				Arguments.of("", "", true), Arguments.of(NUM_END_0, NUM_END_0, true),

				// resultados inválidos
				Arguments.of("", " ", false), Arguments.of(" ", "", false), Arguments.of("", null, false),
				Arguments.of(null, "", false), Arguments.of(null, null, false),
				Arguments.of(NUM_START_0, NUM_END_0, false), Arguments.of(NUM_END_0, NUM_START_0, false));
	}

	private static Stream<Arguments> argumentsGenereValidationDigit() {
		return Stream.of(
				// verifica que el sistema genera resultados del 0-9
				Arguments.of("41112200", 0), Arguments.of("41112501", 1), Arguments.of("41112222", 2),
				Arguments.of("41112313", 3), Arguments.of("41112244", 4), Arguments.of("41112335", 5),
				Arguments.of("41112216", 6), Arguments.of("41112307", 7), Arguments.of("41112238", 8),
				Arguments.of("41112329", 9),

				// varifica manejo de valores inválidos, por ser null o superar el largo max de
				// cédula
				Arguments.of(null, -1), Arguments.of("411123077", -1));
	}

	private static Stream<Arguments> argumentsReduceValidationDigit() {
		return Stream.of(
				// comportamientos válidos
				Arguments.of(30, 0), Arguments.of(459, 9), Arguments.of(548, 8), Arguments.of(57, 7),
				Arguments.of(246, 6), Arguments.of(65, 5), Arguments.of(944, 4), Arguments.of(53, 3),
				Arguments.of(552, 2), Arguments.of(11, 1), Arguments.of(1, 1), Arguments.of(4, 4),

				// comportamientos inválidos
				Arguments.of(-45, -45), Arguments.of(null, null));
	}

	private static Stream<Arguments> argumentsCheckValidatonDigit() {
		return Stream.of(
				// comportamientos válidos
				Arguments.of(0, NUM_END_0, true), Arguments.of(9, NUM_END_9, true), Arguments.of(8, NUM_END_8, true),
				Arguments.of(7, NUM_END_7, true), Arguments.of(6, NUM_END_6, true), Arguments.of(5, NUM_END_5, true),
				Arguments.of(4, NUM_END_4, true), Arguments.of(3, NUM_END_3, true), Arguments.of(2, NUM_END_2, true),
				Arguments.of(1, NUM_END_1, true),

				// comportamientos inválidos
				Arguments.of(8, NUM_END_0, false), Arguments.of(1, NUM_END_9, false),
				Arguments.of(null, NUM_END_9, false));
	}

	private static Stream<Arguments> argumentsGetValidatonDigit() {
		return Stream.of(
				// comportamientos válidos
				Arguments.of(NUM_END_0, 0), Arguments.of(NUM_END_9, 9), Arguments.of(NUM_END_8, 8),
				Arguments.of(NUM_END_7, 7), Arguments.of(NUM_END_6, 6), Arguments.of(NUM_END_5, 5),
				Arguments.of(NUM_END_4, 4), Arguments.of(NUM_END_3, 3), Arguments.of(NUM_END_2, 2),
				Arguments.of(NUM_END_1, 1),

				// comportamientos inválidos
				Arguments.of("", -1), Arguments.of(null, -1));
	}

	private static Stream<Arguments> argumentsParsePosition() {
		return Stream.of(
				// comportamientos válidos
				Arguments.of(NUM_END_0, 1, 1), Arguments.of(NUM_END_0, 2, 2), Arguments.of(NUM_END_0, 3, 3),
				Arguments.of(NUM_END_0, 4, 4), Arguments.of(NUM_END_0, 5, 5), Arguments.of(NUM_END_0, 6, 6),
				Arguments.of(NUM_END_0, 7, 7), Arguments.of(NUM_END_0, 8, 8), Arguments.of(NUM_END_0, 9, 9),

				// comportamientos inválidos
				Arguments.of(NUM_END_0, -1, -1), Arguments.of(NUM_END_0, 0, -1), Arguments.of(NUM_END_0, 15, -1),
				Arguments.of(null, 4, -1));
	}
}
