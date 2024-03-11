package Utility;

import java.util.regex.Pattern;

public class RegexEmail {

    public static final Pattern PATTERN_EMAIL =
            Pattern.compile("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
}
