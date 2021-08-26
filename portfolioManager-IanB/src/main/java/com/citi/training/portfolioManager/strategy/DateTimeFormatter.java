package com.citi.training.portfolioManager.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeFormatter {
    public static String formatMonth(String month) throws ParseException {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        return monthDisplay.format(monthParse.parse(month));
    }

}
