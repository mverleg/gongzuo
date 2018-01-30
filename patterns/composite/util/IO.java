package composite.util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handler interaction with the person being questioned.
 * (This would probably be an interface in a more elaborate example).
 */
public class IO {

    final PrintStream send = System.out;
    final Scanner recv = new Scanner(System.in);

    public void show(String text) {
        send.println(text);
    }

    public String ask(String[] options) {
        String optionTxt = Arrays.asList(options).stream().collect(Collectors.joining(" / "));
        send.print(optionTxt + "? ");
        while (true) {
            String response = recv.next();
            for (String option : options) {
                if (response.equals(option)) {
                    return option;
                }
            }
            send.println("Please enter one of: " + optionTxt);
        }
    }
}
