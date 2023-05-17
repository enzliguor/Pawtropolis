package pawtropolis.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomLogger {

    private static final String RED = "\u001B[33m";
    private static final String YELLOW = "\u001B[31m";
    private static final String BLUE = "\u001B[36m";
    private static final String BLANK = "\u001B[0m";

    public static void gameMessage(String message){
        log.info(YELLOW + message + BLANK);
    }

    public static void error(String message){
        log.info(RED + message + BLANK);
    }

    public static void option(String message){
        log.info(BLUE + message + BLANK);
    }
}
