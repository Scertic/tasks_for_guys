package com.epam.rd.autocode.assessment.basics.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.fail;

public class Util {
    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    static Date parse(String text) {
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            fail(e);
        }
        return null;
    }
}
