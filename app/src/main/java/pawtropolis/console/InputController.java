package pawtropolis.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InputController {

	public static String readString() {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader inputReader = new BufferedReader(input);
		try {
			return inputReader.readLine();
		} catch (IOException e) {
			CustomLogger.error("Error while reading user input");
			return "";
		}
	}
	public static String readChoice(String message, Collection<String> options) {
		String choice;
		CustomLogger.gameMessage("\n>" + message);
		StringBuilder stringBuilder = new StringBuilder();
		options.forEach(option -> stringBuilder.append("\n- ").append(option));
		do {
			CustomLogger.option(stringBuilder.toString());
			String input = readString().trim();
			choice = options.stream()
					.filter(opt -> opt.equalsIgnoreCase(input))
					.findFirst()
					.orElse(null);
		} while (choice == null);
		return choice;
	}
	public static String evaluateInput(String message, Predicate<String> condition, String errorMessage) {
		String input;
		boolean isValidInput;
		do {
			CustomLogger.gameMessage("\n>" + message);
			input = InputController.readString().trim();
			isValidInput = condition.test(input);
			if (!isValidInput) {
				CustomLogger.error("\n>" + errorMessage);
			}
		} while (!isValidInput);
		return input;
	}

}
