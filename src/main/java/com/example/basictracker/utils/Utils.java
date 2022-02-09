package com.example.basictracker.utils;

import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static String generateRandomId(int length) {
        return RandomStringUtils.random(length, true, true).toUpperCase(Locale.ROOT);
    }
}
