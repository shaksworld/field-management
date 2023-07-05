package com.shaks.fieldmanagementsystem.util;

import java.util.Objects;

public class StringUtill {

    public static boolean doesBothStringMatch(String firstText, String secondText){
        if (Objects.nonNull(firstText) && Objects.nonNull(secondText)) {
            return Objects.equals(firstText, secondText);
        }
        return false;
    }
}
