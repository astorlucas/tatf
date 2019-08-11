package uy.com.ces.capacitacion.uno;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Dardo De León
 */
public class AppUno {

	public static final String MESSAGE_START = "Enter a cédula or the word `exit` to end.";
	public static final String MESSAGE_END = "Bye";

	public static final String EXIT_WORD = "exit";

	private ValidCedula validator;

	public AppUno(ValidCedula validCedula) {
		this.validator = validCedula;
	}

	public void run(InputStream input, PrintStream output) {
		String testCedula = "";

		Scanner scanner = new Scanner(input);

		while (!EXIT_WORD.equalsIgnoreCase(testCedula)) {

			output.println(MESSAGE_START);

			testCedula = scanner.nextLine();

			if (!EXIT_WORD.equalsIgnoreCase(testCedula)) {
				output.printf("`%s` = %b%n", testCedula, this.validator.valid(testCedula));
			}
		}

		output.print(MESSAGE_END);

		scanner.close();
	}
}
