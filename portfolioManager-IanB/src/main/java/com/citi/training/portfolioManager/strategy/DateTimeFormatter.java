package com.citi.training.portfolioManager.strategy;

import org.threeten.extra.YearQuarter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeFormatter {
    public static String formatLocalDate(String month)  {
        SimpleDateFormat monthParse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM dd");
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
    public static Date convertLocalDateToDate(LocalDate localDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }
    public static int getLastQuarterStartMonth(){
        YearQuarter lastQuarter = YearQuarter.now(ZoneId.systemDefault()).minusQuarters(1);
        return lastQuarter.atDay(1).getMonthValue();
    }

    public static void main(String[] args){
       System.out.println(formatLocalDate(LocalDate.now().toString()));
    }
}
