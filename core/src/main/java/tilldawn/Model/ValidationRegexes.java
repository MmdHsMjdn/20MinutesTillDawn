package tilldawn.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ValidationRegexes implements Command {
Password("^(?=.*[A-Z].*)(?=.*[@#$%&*()_].*)(?=.*\\d.*)\\S{8,}$");

    private final String pattern;
    ValidationRegexes(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

}
