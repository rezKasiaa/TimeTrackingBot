package com.example.basictracker.utils;

import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;

public class Utils {
    public static Long generateRandomId(int length) {
        return Long.parseLong(RandomStringUtils.random(length, false, true).toUpperCase(Locale.ROOT));
    }
}
