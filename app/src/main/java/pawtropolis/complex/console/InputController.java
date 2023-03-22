package pawtropolis.complex.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
