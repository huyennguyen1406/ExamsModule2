package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final String PHONE_REGEX = "0[3-9]\\d{8}";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public Validate() {
    }

    public boolean validatePhone(String regex) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public boolean validateEmail(String regex) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public boolean validatePhoneOrName(String keyword, String regex) {
        keyword = keyword.toLowerCase();
        String PHONE_NAME_REGEX = ".*" + keyword + ".*";
        Pattern pattern = Pattern.compile(PHONE_NAME_REGEX);
        Matcher matcher = pattern.matcher(regex.toLowerCase());
        return matcher.matches();
    }
}
