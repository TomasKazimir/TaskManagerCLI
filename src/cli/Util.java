package cli;

import java.util.Optional;

public class Util {
    public static Optional<Integer> safeParseInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
