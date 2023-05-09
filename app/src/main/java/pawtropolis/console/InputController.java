package pawtropolis.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.function.Predicate;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InputController {

	public static String readString() {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader inputReader = new BufferedReader(input);
		try {
			return inputReader.readLine();
		} catch (IOException e) {
			log.info("Error while reading user input");
			return "";
		}
	}
	public static String readChoice(String message, Collection<String> options) {
		String input;
		StringBuilder stringBuilder = new StringBuilder("\n>").append(message);
		for (String option : options) {
			stringBuilder.append("\n- ").append(option);
		}
		do {
			log.info(stringBuilder.toString());
			input = InputController.readString().trim();
		} while (!options.contains(input));
		return input;
	}
	public static String evaluateInput(String message, Predicate<String> condition, String errorMessage) {
		String input;
		boolean isValidInput;
		do {
			log.info("\n>" + message);
			input = InputController.readString().trim();
			isValidInput = condition.test(input);
			if (!isValidInput) {
				log.error("\n>" + errorMessage);
			}
		} while (!isValidInput);
		return input;
	}

}
