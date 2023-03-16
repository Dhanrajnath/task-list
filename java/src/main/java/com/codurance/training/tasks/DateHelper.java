package com.codurance.training.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String parseDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }
}
