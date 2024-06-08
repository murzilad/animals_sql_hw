package animals.utils;

import java.util.regex.Pattern;

public class Validation {

    public boolean isNumber(String answer) {

        String num = "^[0-9]+$";
        return Pattern.matches(num, answer);
    }

    public boolean isChars(String answer) {

        String reg = "^[a-zA-Zа-яА-Я]+$";
        return Pattern.matches(reg, answer);
    }
}
