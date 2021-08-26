package com.citi.training.portfolioManager.strategy;

import org.threeten.extra.YearQuarter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeFormatter {
    public static String formatMonth(String month)  {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        try {
            return monthDisplay.format(monthParse.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return month;
    }
    public static LocalDate convertDateToLocalDate(Date date){
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    public static int getLastQuarterStartMonth(){
        YearQuarter lastQuarter = YearQuarter.now(ZoneId.systemDefault()).minusQuarters(1);
        Date firstDay = java.sql.Date.valueOf(lastQuarter.atDay(1));
        return convertDateToLocalDate(firstDay).getMonthValue();
    }

}
